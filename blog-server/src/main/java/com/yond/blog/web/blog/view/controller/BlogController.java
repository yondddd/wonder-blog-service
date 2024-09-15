package com.yond.blog.web.blog.view.controller;

import com.yond.blog.service.BlogService;
import com.yond.blog.service.impl.UserServiceImpl;
import com.yond.blog.util.MyStringUtils;
import com.yond.blog.util.jwt.JwtUtil;
import com.yond.blog.util.jwt.PayloadHelper;
import com.yond.blog.web.blog.view.dto.BlogPassword;
import com.yond.blog.web.blog.view.vo.BlogDetail;
import com.yond.blog.web.blog.view.vo.BlogInfo;
import com.yond.blog.web.blog.view.vo.PageResult;
import com.yond.blog.web.blog.view.vo.SearchBlog;
import com.yond.common.annotation.VisitLogger;
import com.yond.common.constant.JwtConstant;
import com.yond.common.enums.VisitBehavior;
import com.yond.common.resp.Response;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @Description: 博客相关
 * @Author: Yond
 */
@RestController
public class BlogController {

    private final BlogService blogService;
    private final UserServiceImpl userService;

    public BlogController(BlogService blogService, UserServiceImpl userService) {
        this.blogService = blogService;
        this.userService = userService;
    }

    /**
     * 按置顶、创建时间排序 分页查询博客简要信息列表
     *
     * @param pageNum 页码
     */
    @VisitLogger(VisitBehavior.INDEX)
    @GetMapping("/view/blogs")
    public Response<PageResult<BlogInfo>> blogs(@RequestParam(defaultValue = "1") Integer pageNum) {
        PageResult<BlogInfo> pageResult = blogService.getBlogInfoListByIsPublished(pageNum);
        return Response.success(pageResult);
    }

    /**
     * 按id获取公开博客详情
     *
     * @param id  博客id
     * @param jwt 密码保护文章的访问Token
     * @return
     */
    @VisitLogger(VisitBehavior.BLOG)
    @GetMapping("/view/blog")
    public Response<BlogDetail> getBlog(@RequestParam Long id,
                                        @RequestHeader(value = JwtConstant.TOKEN_HEADER, defaultValue = "") String jwt) {
        BlogDetail blog = blogService.getBlogByIdAndIsPublished(id);
        if (StringUtils.isBlank(blog.getPassword())) {
            blogService.updateViewsToRedis(id);
            return Response.success(blog);
        }
        if (!JwtUtil.judgeTokenIsExist(jwt)) {
            return Response.custom(403, "此文章受密码保护，请验证密码！");
        }
        //对密码保护的文章校验Token
        try {
            Claims claims = JwtUtil.validateJwt(jwt, JwtConstant.DEFAULT_SECRET);
            String subject = claims.getSubject();
            if (subject.startsWith(JwtConstant.ADMIN_PREFIX)) {
                //博主身份Token
                if (claims.getExpiration().before(new Date())) {
                    return Response.custom(403, "博主身份Token已失效，请重新登录！");
                }
            } else {
                //经密码验证后的Token
                Long tokenBlogId = Long.parseLong(subject);
                //博客id不匹配，验证不通过，可能博客id改变或客户端传递了其它密码保护文章的Token
                if (!tokenBlogId.equals(id)) {
                    return Response.custom(403, "Token不匹配，请重新验证密码！");
                }
            }
        } catch (Exception e) {
            return Response.custom(403, "Token已失效，请重新验证密码！");
        }
        blog.setPassword("");
        blogService.updateViewsToRedis(id);
        return Response.success(blog);
    }

    /**
     * 校验受保护文章密码是否正确，正确则返回jwt
     *
     * @param blogPassword 博客id、密码
     * @return
     */
    @VisitLogger(VisitBehavior.CHECK_PASSWORD)
    @PostMapping("/view/checkBlogPassword")
    public Response<String> checkBlogPassword(@RequestBody BlogPassword blogPassword) {
        String password = blogService.getBlogPassword(blogPassword.getBlogId());
        if (!password.equals(blogPassword.getPassword())) {
            return Response.custom(403, "密码错误");
        }
        // 生成有效时间一个月的Token
        PayloadHelper payloadHelper = new PayloadHelper()
                .setIssuer(JwtConstant.DEFAULT_CLIENT)
                .setIssuedAt(new Date())
                .setSubject(blogPassword.getBlogId().toString())
                .setSecret(JwtConstant.DEFAULT_SECRET)
                .setAdditionalInfo(new HashMap<>());
        String token = JwtUtil.creatToken(payloadHelper, JwtConstant.ONE_MONTH_TIME);

        return Response.success(token);
    }

    /**
     * 按关键字根据文章内容搜索公开且无密码保护的博客文章
     *
     * @param query 关键字字符串
     * @return
     */
    @VisitLogger(VisitBehavior.SEARCH)
    @GetMapping("/view/searchBlog")
    public Response searchBlog(@RequestParam String query) {
        //校验关键字字符串合法性
        if (StringUtils.isBlank(query) || MyStringUtils.hasSpecialChar(query) || query.trim().length() > 20) {
            return Response.failure("参数错误");
        }
        List<SearchBlog> searchBlogs = blogService.searchPublic(query.trim());
        return Response.ok("获取成功", searchBlogs);
    }
}

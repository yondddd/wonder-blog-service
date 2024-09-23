package com.yond.blog.web.blog.view.controller;

import com.yond.blog.cache.redis.BlogViewCache;
import com.yond.blog.service.BlogService;
import com.yond.blog.util.jwt.JwtUtil;
import com.yond.blog.util.jwt.PayloadHelper;
import com.yond.blog.web.blog.view.dto.BlogPassword;
import com.yond.blog.web.blog.view.req.BlogPageReq;
import com.yond.blog.web.blog.view.vo.BlogDetail;
import com.yond.blog.web.blog.view.vo.BlogVO;
import com.yond.blog.web.blog.view.vo.SearchBlog;
import com.yond.common.annotation.VisitLogger;
import com.yond.common.constant.JwtConstant;
import com.yond.common.enums.VisitBehavior;
import com.yond.common.resp.PageResponse;
import com.yond.common.resp.Response;
import io.jsonwebtoken.Claims;
import jakarta.annotation.Resource;
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
@RequestMapping("/view/blog")
public class BlogController {

    @Resource
    private BlogService blogService;
    @Resource
    private BlogViewCache blogViewCache;

    @VisitLogger(VisitBehavior.INDEX)
    @GetMapping("/page")
    public PageResponse<List<BlogVO>> page(@RequestBody BlogPageReq req) {
        // 加个categoryId
//        blogService.viewPageBy();
        return null;
    }


//    private List<BlogInfo> processBlogInfosPassword(List<BlogInfo> blogInfos) {
//        for (BlogInfo blogInfo : blogInfos) {
//            if (!"".equals(blogInfo.getPassword())) {
//                blogInfo.setPrivacy(true);
//                blogInfo.setPassword("");
//                blogInfo.setDescription(BlogConstant.PRIVATE_BLOG_DESCRIPTION);
//            } else {
//                blogInfo.setPrivacy(false);
//                blogInfo.setDescription(MarkdownUtils.markdownToHtmlExtensions(blogInfo.getDescription()));
//            }
//            blogInfo.setTags(blogTagService.listTagsByBlogId(blogInfo.getId()));
//        }
//        return blogInfos;
//    }

    @VisitLogger(VisitBehavior.BLOG)
    @GetMapping("/detail")
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
     * @param req 博客id、密码
     * @return
     */
    @VisitLogger(VisitBehavior.CHECK_PASSWORD)
    @PostMapping("/checkPassword")
    public Response<String> checkBlogPassword(@RequestBody BlogPassword req) {
        String password = blogService.getBlogById(req.getBlogId()).getPassword();
        if (!password.equals(req.getPassword())) {
            return Response.custom(403, "密码错误");
        }
        // 生成有效时间一个月的Token
        PayloadHelper payloadHelper = new PayloadHelper()
                .setIssuer(JwtConstant.DEFAULT_CLIENT)
                .setIssuedAt(new Date())
                .setSubject(req.getBlogId().toString())
                .setSecret(JwtConstant.DEFAULT_SECRET)
                .setAdditionalInfo(new HashMap<>());
        String token = JwtUtil.creatToken(payloadHelper, JwtConstant.ONE_MONTH_TIME);

        return Response.success(token);
    }

    @VisitLogger(VisitBehavior.SEARCH)
    @GetMapping("/search")
    public Response searchBlog(@RequestParam String query) {
        //校验关键字字符串合法性
        if (StringUtils.isBlank(query) || hasSpecialChar(query) || query.trim().length() > 20) {
            return Response.failure("参数错误");
        }
        List<SearchBlog> searchBlogs = blogService.searchPublic(query.trim());
        return Response.ok("获取成功", searchBlogs);
    }

    public boolean hasSpecialChar(String... str) {
        for (String s : str) {
            if (s.contains("%") || s.contains("_") || s.contains("[") || s.contains("#") || s.contains("*")) {
                return true;
            }
        }
        return false;
    }

}

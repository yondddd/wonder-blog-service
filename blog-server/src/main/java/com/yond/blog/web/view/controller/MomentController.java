package com.yond.blog.web.view.controller;

import com.yond.blog.entity.MomentDO;
import com.yond.blog.service.MomentService;
import com.yond.blog.service.impl.UserServiceImpl;
import com.yond.blog.util.jwt.JwtUtil;
import com.yond.blog.web.view.vo.PageResult;
import com.yond.common.annotation.AccessLimit;
import com.yond.common.annotation.VisitLogger;
import com.yond.common.constant.JwtConstant;
import com.yond.common.enums.VisitBehavior;
import com.yond.common.resp.Response;
import io.jsonwebtoken.Claims;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @Description: 动态
 * @Author: Yond
 */
@RestController
@RequestMapping("/view/moment")
public class MomentController {
    
    @Resource
    private MomentService momentService;
    @Resource
    private UserServiceImpl userService;
    
    private static final int pageSize = 5;
    
    @VisitLogger(VisitBehavior.MOMENT)
    @GetMapping("/page")
    public Response<PageResult<MomentDO>> moments(@RequestParam(defaultValue = "1") Integer pageNum,
                                                  @RequestHeader(value = JwtConstant.TOKEN_HEADER, defaultValue = "") String jwt) {
        boolean adminIdentity = false;
        if (JwtUtil.judgeTokenIsExist(jwt)) {
            try {
                Claims claims = JwtUtil.validateJwt(jwt, JwtConstant.DEFAULT_SECRET);
                String subject = claims.getSubject();
                if (subject.startsWith(JwtConstant.ADMIN_PREFIX)) {
                    //博主身份Token
                    if (claims.getExpiration().after(new Date())) {
                        adminIdentity = true;
                    }
                }
            } catch (Exception e) {
                return Response.custom(403, "博主身份Token已失效，请重新登录！");
            }
        }
        Pair<Integer, List<MomentDO>> pair = momentService.page(adminIdentity, true, pageNum, pageSize);
        PageResult<MomentDO> pageResult = new PageResult<>(pair.getLeft(), pair.getRight());
        return Response.success(pageResult);
    }
    
    @AccessLimit(seconds = 86400, maxCount = 1, msg = "不可以重复点赞哦")
    @VisitLogger(VisitBehavior.LIKE_MOMENT)
    @PostMapping("/like/{id}")
    public Response<Boolean> like(@PathVariable Long id) {
        momentService.incrLikeById(id);
        return Response.success();
    }
    
}

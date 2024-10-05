package com.wonder.blog.web.view.controller;

import com.wonder.blog.entity.MomentDO;
import com.wonder.blog.service.MomentService;
import com.wonder.blog.util.jwt.JwtUtil;
import com.wonder.blog.web.view.req.MomentPageReq;
import com.wonder.common.annotation.AccessLimit;
import com.wonder.common.annotation.VisitLogger;
import com.wonder.common.constant.JwtConstant;
import com.wonder.common.enums.VisitBehavior;
import com.wonder.common.resp.PageResponse;
import com.wonder.common.resp.Response;
import io.jsonwebtoken.Claims;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
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

    @VisitLogger(VisitBehavior.MOMENT)
    @PostMapping("/page")
    public PageResponse<List<MomentDO>> moments(@RequestBody MomentPageReq req,
                                                @RequestHeader(value = JwtConstant.TOKEN_HEADER, defaultValue = "") String jwt) {

        Pair<Boolean, String> check = this.check(jwt);
        if (StringUtils.isNotBlank(check.getRight())) {
            return PageResponse.<List<MomentDO>>custom().setCode(403).setMessage(check.getRight());
        }
        Pair<Integer, List<MomentDO>> pair = momentService.page(check.getLeft(), true, req.getPageNo(), req.getPageSize());
        return PageResponse.<List<MomentDO>>custom()
                .setTotal(pair.getLeft()).setData(pair.getRight()).setPageNo(req.getPageNo()).setPageSize(req.getPageSize()).setSuccess();
    }

    @AccessLimit(seconds = 86400, maxCount = 1, msg = "不可以重复点赞哦")
    @VisitLogger(VisitBehavior.LIKE_MOMENT)
    @PostMapping("/like/{id}")
    public Response<Boolean> like(@PathVariable Long id) {
        // todo 浏览量需要一个统一的方案
        momentService.incrLikeById(id);
        return Response.success();
    }

    private Pair<Boolean, String> check(String jwt) {
        boolean adminIdentity = false;
        if (!JwtUtil.judgeTokenIsExist(jwt)) {
            return Pair.of(adminIdentity, null);
        }
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
            return Pair.of(null, "博主身份Token已失效，请重新登录！");
        }
        return Pair.of(adminIdentity, null);
    }

}

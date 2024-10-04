package com.wish.blog.util.jwt;


import com.wish.common.constant.JwtConstant;

import java.util.Date;
import java.util.Map;

public class PayloadHelper {


    /**
     * 发布者
     */
    private String issuer;

    /**
     * 授权用户
     */
    private String subject;

    /**
     * 创建时间
     */
    private Date issuedAt;

    /**
     * secret
     */
    private String secret = JwtConstant.DEFAULT_SECRET;

    /**
     * additional information
     */
    private Map<String, Object> additionalInfo;


    public String getIssuer() {
        return issuer;
    }

    public PayloadHelper setIssuer(String issuer) {
        this.issuer = issuer;
        return this;
    }

    public String getSubject() {
        return subject;
    }

    public PayloadHelper setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public Date getIssuedAt() {
        return issuedAt;
    }

    public PayloadHelper setIssuedAt(Date issuedAt) {
        this.issuedAt = issuedAt;
        return this;
    }

    public String getSecret() {
        return secret;
    }

    public PayloadHelper setSecret(String secret) {
        this.secret = secret;
        return this;
    }

    public Map<String, Object> getAdditionalInfo() {
        return additionalInfo;
    }

    public PayloadHelper setAdditionalInfo(Map<String, Object> additionalInfo) {
        this.additionalInfo = additionalInfo;
        return this;
    }

}
package com.wish.blog.util.web;


import com.wish.common.utils.json.util.JsonUtils;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class WebFilterUtil {

    private final static Logger logger = LoggerFactory.getLogger(WebFilterUtil.class);

    public static void returnFail(HttpServletResponse response, int code, Object msg) {
        if (response.isCommitted()) {
            return;
        }
        response.setCharacterEncoding("UTF-8");
        response.setStatus(code);
        response.setContentType("application/json; charset=utf-8");
        WebFilterUtil.crossHeader(response);
        try (ServletOutputStream out = response.getOutputStream()) {
            String outputMessage;
            if (msg instanceof String) {
                outputMessage = (String) msg;
            } else {
                outputMessage = JsonUtils.toJson(msg);
            }
            out.write(outputMessage.getBytes(StandardCharsets.UTF_8));
            out.flush();
        } catch (IOException e) {
            logger.error("写出失败", e);
        }
    }


    public static void crossHeader(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Headers", "*");
        response.setHeader("Access-Control-Allow-Methods", "*");
    }

}

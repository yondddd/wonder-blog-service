package com.yond.blog.web.handler;

import com.yond.common.exception.NotFoundException;
import com.yond.common.exception.PersistenceException;
import com.yond.common.resp.Response;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Description: 对Controller层全局异常处理
 * @RestControllerAdvice 捕获异常后，返回json数据类型
 * @Author: Naccl
 * @Date: 2020-08-14
 */
@RestControllerAdvice
public class ControllerExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 捕获自定义的404异常
     *
     * @param request 请求
     * @param e       自定义抛出的异常信息
     * @return
     */
    @ExceptionHandler(NotFoundException.class)
    public Response notFoundExceptionHandler(HttpServletRequest request, NotFoundException e) {
        logger.error("Request URL : {}, Exception :", request.getRequestURL(), e);
        return Response.create(HttpStatus.NOT_FOUND.value(), e.getMessage());
    }

    /**
     * 捕获自定义的持久化异常
     *
     * @param request 请求
     * @param e       自定义抛出的异常信息
     * @return
     */
    @ExceptionHandler(PersistenceException.class)
    public Response persistenceExceptionHandler(HttpServletRequest request, PersistenceException e) {
        logger.error("Request URL : {}, Exception :", request.getRequestURL(), e);
        return Response.create(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
    }

    /**
     * 捕获其它异常
     *
     * @param request 请求
     * @param e       异常信息
     * @return
     */
    @ExceptionHandler(Exception.class)
    public Response exceptionHandler(HttpServletRequest request, Exception e) {
        logger.error("Request URL : {}, Exception :", request.getRequestURL(), e);
        return Response.create(HttpStatus.INTERNAL_SERVER_ERROR.value(), "异常错误: " + e.getMessage());
    }
}

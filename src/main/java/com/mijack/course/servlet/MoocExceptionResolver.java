package com.mijack.course.servlet;

import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * WebApplication 统一的异常处理
 *
 * @author Mr.Yuan
 * @since 2016/11/20.
 */
@Component
public class MoocExceptionResolver implements HandlerExceptionResolver, Ordered {

    /**
     * 统一的异常处理
     *
     * @param request
     * @param response
     * @param handler
     * @param e
     * @return
     */
    @Override
    public ModelAndView resolveException(HttpServletRequest request,
                                         HttpServletResponse response, Object handler, Exception e) {
        // 视图显示专门的错误页
        ModelAndView modelAndView = new ModelAndView();
        HttpStatus httpStatus = HttpStatus.valueOf(response.getStatus());
        if (httpStatus.is5xxServerError()) {
            //在服务端发生异常的情况，返回error视图
            modelAndView.setViewName("error");
            if (e.getMessage() == null) {
                modelAndView.addObject("msg", "服务器发生异常");
            } else {
                modelAndView.addObject("msg", e.getMessage());
            }
        } else {
            //其余情况（一般为4XX），返回404视图
            modelAndView.setViewName("404");
        }
        return modelAndView;
    }

    @Override
    public int getOrder() {
        return HIGHEST_PRECEDENCE;
    }
}

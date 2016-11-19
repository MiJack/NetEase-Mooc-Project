package com.mijack.course.aop;

import org.springframework.http.HttpStatus;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Mr.Yuan
 * @since 2016/11/18.
 */
@ControllerAdvice
public class WebAppControllerAdvice {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public String process4XX() {
        return "404";
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Throwable.class)
    public String processInternalServerError(ModelMap modelMap, Exception e) {
        if (e != null) {
            modelMap.addAttribute("msg", e.getMessage());
        } else {
            modelMap.addAttribute("msg", "服务端发生500异常,管理员正在紧急修复中！");
        }
        return "error"; //返回一个逻辑视图名
    }
}

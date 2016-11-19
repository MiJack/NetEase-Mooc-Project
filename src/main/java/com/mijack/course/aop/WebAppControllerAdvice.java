package com.mijack.course.aop;

import com.mijack.course.bean.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;

/**
 * @author Mr.Yuan
 * @since 2016/11/18.
 */
@ControllerAdvice
public class WebAppControllerAdvice {

//    @ExceptionHandler(Exception.class)
//    @ResponseStatus(HttpStatus.UNAUTHORIZED)
//    public String processUnauthenticatedException(NativeWebRequest request, Exception e) {
//        System.out.println("===========应用到所有@RequestMapping注解的方法，在其抛出UnauthenticatedException异常时执行");
//        return "viewName"; //返回一个逻辑视图名
//    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Throwable.class)
    public String processUnauthenticatedException(Exception e) {
        e.printStackTrace();
        return "404"; //返回一个逻辑视图名
    }
}

package com.mijack.course.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * 静态页面的跳转，包括登陆页、退出
 * @author Mr.Yuan
 * @since 2016/11/18.
 */
@Controller
public class DispatcherController {

    /**
     * 响应login请求
     * @return login，表示login视图
     */
    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * 响应logout请求，同时使session失效
     * @return 重定向login，表示login视图
     */
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}

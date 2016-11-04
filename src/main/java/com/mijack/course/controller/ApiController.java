package com.mijack.course.controller;

import com.mijack.course.bean.User;
import com.mijack.course.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by admin on 2016/11/4.
 */
@Controller
@RequestMapping("/api")
public class ApiController {
    @Autowired
    UserDao userDao;

    /**
     * 1. 默认权限为需要登录
     * 2. 返回统一为 json 数据，格式为：{code:xxx,message:xxx,result:xxx}
     * 3. code 为 200，表示请求成功，其他表示异常
     * 4. 当 code 不是 200 时，message 中应说明原因
     *
     * @param request
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(HttpServletRequest request,
                              @RequestParam("userName") String username,
                              @RequestParam("password") String password) {
        List<User> users = userDao.login(username, password);
        int count = users.size();
        if (count == 1) {
            User user = users.get(0);
            HttpSession session = request.getSession(true);
            session.setAttribute("user", user);
            session.setAttribute("username", user.getUsername());
            session.setAttribute("type", String.valueOf(user.getUsertype()));
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("code", (count == 1 ? 200 : 100));
        modelAndView.addObject("message", (count == 1 ? "登陆成功" : "登陆异常"));
        modelAndView.addObject("result", (count == 1));
        return modelAndView;
    }
}

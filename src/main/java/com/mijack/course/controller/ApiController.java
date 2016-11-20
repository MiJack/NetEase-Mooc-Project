package com.mijack.course.controller;

import com.mijack.course.bean.User;
import com.mijack.course.service.ProductService;
import com.mijack.course.service.TrxService;
import com.mijack.course.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Api相关的Controller实现
 *
 * @author Mr.Yuan
 * @since 2016/11/4.
 */
@Controller
@RequestMapping("/api")
public class ApiController {
    @Autowired
    private UserService userService;
    @Autowired
    private TrxService trxService;
    @Autowired
    private ProductService productService;

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
        User user = userService.login(username, password);
        boolean login = user != null;
        if (login) {
            HttpSession session = request.getSession(true);
            session.setAttribute("user", user);
            session.setAttribute("username", user.getUsername());
            session.setAttribute("usertype", String.valueOf(user.getUsertype()));
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("code", (login ? 200 : 100));
        modelAndView.addObject("message", (login ? "登陆成功" : "登陆异常"));
        modelAndView.addObject("result", login);
        return modelAndView;
    }

    /**
     * 购买商品
     *
     * @param id   商品的id
     * @param user 用户的信息
     * @return
     */
    @RequestMapping(value = "/buy", method = RequestMethod.POST)
    public ModelAndView buy(@RequestParam("id") int id,
                            @SessionAttribute("user") User user) {
        boolean buy = trxService.buy(user.getId(), id, System.currentTimeMillis());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("code", (buy ? 200 : 100));
        modelAndView.addObject("message", (buy ? "购买成功" : "购买异常"));
        modelAndView.addObject("result", buy);
        return modelAndView;
    }

    /**
     * 删除商品
     *
     * @param id 商品的id
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ModelAndView delete(@RequestParam("id") int id) {
        boolean result = productService.delete(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("code", (result ? 200 : 100));
        modelAndView.addObject("message", (result ? "删除成功" : "删除失败"));
        modelAndView.addObject("result", result);
        return modelAndView;
    }
}

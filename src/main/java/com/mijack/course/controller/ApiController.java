package com.mijack.course.controller;

import com.mijack.course.bean.Product;
import com.mijack.course.bean.User;
import com.mijack.course.dao.ProductDao;
import com.mijack.course.dao.TrxDao;
import com.mijack.course.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
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
    @Autowired
    TrxDao trxDao;
    @Autowired
    ProductDao productDao;

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

    @RequestMapping(value = "/buy", method = RequestMethod.POST)
    public ModelAndView buy(@RequestParam("id") int id, HttpSession session) {
        User user = (User) session.getAttribute("user");
        // FIXME: 2016/11/11 添加事务处理
        Product product = productDao.get(id);
        int buy = 0;
        if (!trxDao.isSell(product)) {
            buy = trxDao.buy(user.getId(), product, System.currentTimeMillis());
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("code", (buy == 1 ? 200 : 100));
        modelAndView.addObject("message", (buy == 1 ? "购买成功" : "购买异常"));
        modelAndView.addObject("result", (buy == 1));
        return modelAndView;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ModelAndView delete(@RequestParam("id") int id) {
        // FIXME: 2016/11/11 考虑删除后对原有交易的影响
        boolean result = productDao.delete(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("code", (result ? 200 : 100));
        modelAndView.addObject("message", (result ? "购买成功" : "购买异常"));
        modelAndView.addObject("result", result);
        return modelAndView;
    }
}

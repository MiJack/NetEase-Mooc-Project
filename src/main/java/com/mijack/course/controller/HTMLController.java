package com.mijack.course.controller;

import com.mijack.course.bean.BuyProduct;
import com.mijack.course.bean.Product;
import com.mijack.course.bean.User;
import com.mijack.course.dao.ProductDao;
import com.mijack.course.dao.TrxDao;
import com.mijack.course.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.swing.*;
import java.util.List;

/**
 * Created by admin on 2016/11/4.
 */
@Controller
public class HTMLController {
    @Autowired
    ProductDao productDao;
    @Autowired
    TrxDao trxDao;

    /**
     * @param data
     * @param session
     * @return
     */
    @RequestMapping(value = "/publicSubmit", method = RequestMethod.POST)
    public ModelAndView publicSubmit(Product data, HttpSession session) {
        ModelAndView modelAndView = Utils.generateModelAndView("publicSubmit", session);
        productDao.submit(data);
        modelAndView.addObject("product", data);
        return modelAndView;
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    @RequestMapping(value = {"/index", "/"})
    public ModelAndView index(HttpSession session) {
        ModelAndView modelAndView = Utils.generateModelAndView("index", session);
        if (Utils.isLogin(session)) {
            User user = (User) session.getAttribute("user");
            List<Product> list = productDao.listProducts();
            for (int i = 0; i < list.size(); i++) {
                Product p = list.get(i);
                if (user.getUsertype() == 0) {
                    p.setBuy(p.getTrxCount() > 0);
                } else {
                    p.setSell(p.getTrxCount() > 0);
                }
            }
            modelAndView.addObject("productList", list);
            modelAndView.addObject("user", user);
            System.out.println(user);
        }
        return modelAndView;
    }

    @RequestMapping(value = {"/public"})
    public ModelAndView publicSome(HttpSession session) {
        return Utils.generateModelAndView("public", session);
    }

    @RequestMapping(value = {"/show"})
    public ModelAndView show(@RequestParam("id") int id, HttpSession session) {
        ModelAndView modelAndView = Utils.generateModelAndView("show", session);
        Product p = productDao.get(id);
        modelAndView.addObject(p);
        return modelAndView;
    }

    @RequestMapping(value = {"/edit"})
    public ModelAndView edit(@RequestParam("id") int id, HttpSession session) {
        ModelAndView modelAndView = Utils.generateModelAndView("edit", session);
        Product p = productDao.get(id);
        modelAndView.addObject(p);
        return modelAndView;
    }

    @RequestMapping(value = {"/editSubmit"}, method = RequestMethod.POST)
    public ModelAndView editSubmit(Product data, @RequestParam("id") int id, HttpSession session) {
        ModelAndView modelAndView = Utils.generateModelAndView("editSubmit", session);
        if (productDao.update(data)) {
            modelAndView.addObject(data);
        }
        return modelAndView;
    }

    @RequestMapping(value = {"/account.html","/account"})
    public ModelAndView account(HttpSession session, @SessionAttribute("user") User user) {
        ModelAndView modelAndView = Utils.generateModelAndView("account", session);
        List<BuyProduct> buyProducts = trxDao.getBuyList(user.getId());
        modelAndView.addObject("buyList", buyProducts);
        return modelAndView;
    }
}

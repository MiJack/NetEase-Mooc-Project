package com.mijack.course.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;

/**
 * Created by admin on 2016/11/4.
 */
@Controller
public class HTMLController {
    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping(value = {"/index", "/"})
    public ModelAndView index(HttpSession session) {
        ModelAndView modelAndView = generateModelAndView("index", session);
        return modelAndView;
    }

    @RequestMapping(value = {"/public"})
    public ModelAndView publicSome(HttpSession session) {
        return generateModelAndView("public", session);
    }

    private ModelAndView generateModelAndView(String view, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView(view);
        if (session != null) {
            Enumeration<String> names = session.getAttributeNames();
            while (names.hasMoreElements()) {
                String element = names.nextElement();
                modelAndView.addObject(element, session.getAttribute(element));
            }
        }
        return modelAndView;
    }

}

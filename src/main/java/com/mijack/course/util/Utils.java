package com.mijack.course.util;

import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Enumeration;

/**
 * Created by Mr.Yuan on 2016/11/7.
 */
@Deprecated
public class Utils {
    /**
     * 转化成aop或者其他方法
     * @param view
     * @param session
     * @return
     */
    public static ModelAndView generateModelAndView(String view, HttpSession session) {
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


    public static boolean isLogin(HttpSession session) {
        if (session==null){return false;}
        Enumeration<String> names = session.getAttributeNames();
        return names.hasMoreElements();
    }
}

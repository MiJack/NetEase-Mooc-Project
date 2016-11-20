package com.mijack.course.controller;

import com.mijack.course.bean.Product;
import com.mijack.course.bean.User;
import com.mijack.course.service.TrxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;

/**
 * 和交易相关的请求处理
 *
 * @author Mr.Yuan
 * @since 2016/11/20.
 */
@Controller
public class TrxController {
    @Autowired
    private TrxService trxService;

    /**
     * 访问account页面
     *
     * @param modelMap
     * @param user
     * @param userName
     * @param usertype
     * @return
     */
    @RequestMapping(value = {"/account.html", "/account"})
    public String account(ModelMap modelMap,
                          @SessionAttribute(name = "user", required = false) User user,
                          @SessionAttribute(name = "username", required = false) String userName,
                          @SessionAttribute(name = "usertype", required = false) String usertype) {
        if ("1".equals(usertype)) {
            throw new IllegalStateException("卖家无法访问");
        }
        List<Product> buyProducts = trxService.getBuyList(user.getId());
        modelMap.addAttribute("buyList", buyProducts);
        return "account";
    }
}

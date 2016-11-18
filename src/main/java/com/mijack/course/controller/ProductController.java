package com.mijack.course.controller;

import com.mijack.course.bean.Product;
import com.mijack.course.bean.User;
import com.mijack.course.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;

/**
 * 响应和Product相关请求
 *
 * @author Mr.Yuan
 * @since 2016/11/18.
 */
@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * 响应index或者/请求
     *
     * @param modelMap
     * @param user
     * @param userName
     * @param usertype
     * @return
     */
    @RequestMapping(value = {"/index", "/index.html", "/"})
    public String index(ModelMap modelMap,
                        @SessionAttribute(name = "user", required = false) User user,
                        @SessionAttribute(name = "username", required = false) String userName,
                        @SessionAttribute(name = "usertype", required = false) String usertype,
                        @RequestParam(name = "type", required = false) String type) {
        //传入userName和type（是否筛选已购买的商品）获取商品列表
        List<Product> list = productService.listProducts(type, userName);
        modelMap.addAttribute("productList", list);
        return "index";
    }

    /**
     * 展现产品的信息
     *
     * @param modelMap
     * @param user
     * @param userName
     * @param usertype
     * @param id
     * @return
     */
    @RequestMapping(value = {"/show"})
    public String show(ModelMap modelMap,
                       @SessionAttribute(name = "user", required = false) User user,
                       @SessionAttribute(name = "username", required = false) String userName,
                       @SessionAttribute(name = "usertype", required = false) String usertype,
                       @RequestParam("id") int id) {
        Product p = productService.get(id, userName);
        modelMap.addAttribute(p);
        return "show";
    }

    /**
     * 请求发布的界面
     *
     * @param user
     * @param userName
     * @param usertype
     * @return
     */
    @RequestMapping(value = {"/public"})
    public String publicSome(
            @SessionAttribute(name = "user", required = false) User user,
            @SessionAttribute(name = "username", required = false) String userName,
            @SessionAttribute(name = "usertype", required = false) String usertype) {
        return "public";
    }


    /**
     * 请求编辑的界面
     *
     * @param modelMap
     * @param user
     * @param userName
     * @param usertype
     * @param id
     * @return
     */
    @RequestMapping(value = {"/edit"})
    public String edit(ModelMap modelMap,
                       @SessionAttribute(name = "user", required = false) User user,
                       @SessionAttribute(name = "username", required = false) String userName,
                       @SessionAttribute(name = "usertype", required = false) String usertype,
                       @RequestParam("id") int id) {
        // TODO: 2016/11/19   添加判断，卖出的商品无法编辑
        Product p = productService.get(id, userName);
        modelMap.addAttribute(p);
        return "edit";
    }

    /**
     * @param modelMap
     * @param user
     * @param userName
     * @param usertype
     * @param data
     * @return
     */
    @RequestMapping(value = "/publicSubmit", method = RequestMethod.POST)
    public String publicSubmit(ModelMap modelMap,
                               @SessionAttribute(name = "user", required = false) User user,
                               @SessionAttribute(name = "username", required = false) String userName,
                               @SessionAttribute(name = "usertype", required = false) String usertype,
                               Product data) {
        // TODO: 2016/11/19  添加data的检验
        productService.submitProduct(data);
        modelMap.addAttribute("product", data);
        return "publicSubmit";
    }

    @RequestMapping(value = {"/editSubmit"}, method = RequestMethod.POST)
    public String editSubmit(ModelMap modelMap,
                             @SessionAttribute(name = "user", required = false) User user,
                             @SessionAttribute(name = "username", required = false) String userName,
                             @SessionAttribute(name = "usertype", required = false) String usertype,
                             Product data, @RequestParam("id") int id) {
        if (productService.updateProduct(data)) {
            modelMap.addAttribute(data);
        }
        return "editSubmit";
    }
}
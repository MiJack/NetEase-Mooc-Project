package com.mijack.course.service;

import com.mijack.course.bean.Product;

import java.util.List;

/**
 * @author Mr.Yuan
 * @since 2016/11/18.
 */
public interface ProductService {

    /**
     * @param type     type type为1时，筛选用户未购买的商品
     * @param userName
     * @return
     */
    List<Product> listProducts(String type, String userName);

    /**
     * 获取商品的信息
     *
     * @param id 商品的id
     * @return
     */
    Product get(int id);

    /**
     * 获取商品的信息
     *
     * @param id       商品的id
     * @param userName 当前用户的username，用于区分是否有用户登录
     * @return
     */
    Product get(int id, String userName);

    /**
     * 提交发布商品
     *
     * @param data
     */
    void submitProduct(Product data);

    /**
     * 更新prouct的数据
     *
     * @param data
     * @return
     */
    boolean updateProduct(Product data);

    boolean delete(int id);
}

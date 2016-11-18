package com.mijack.course.service;

import com.mijack.course.bean.Product;

import java.util.List;

/**
 * @author Mr.Yuan
 * @since 2016/11/19.
 */
public interface TrxService {

    /**
     * 返回用户id为id的用户购买的商品列表
     * @param userId 用户的id
     * @return
     */
    List<Product> getBuyList(String userId);

    boolean buy(String userId, int product, long currentTimeMillis);
}

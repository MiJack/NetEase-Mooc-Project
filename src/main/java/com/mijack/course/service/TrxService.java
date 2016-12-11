package com.mijack.course.service;

import com.mijack.course.bean.Product;

import java.util.List;

/**
 * 和交易相关服务的定义
 * @author Mr.Yuan
 * @since 2016/11/19.
 */
public interface TrxService {

    /**
     * 返回用户id为id的用户购买的商品列表
     * @param userId 用户的id
     * @return 购买的商品列表
     */
    List<Product> getBuyList(String userId);

    /**
     * 用户购买商品的服务
     * @param userId 用户的id
     * @param productId 首要购买的商品id
     * @param currentTimeMillis 购买的时间
     * @return 是否购买成功
     */
    boolean buy(String userId, int productId, long currentTimeMillis);
}

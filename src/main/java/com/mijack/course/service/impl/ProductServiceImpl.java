package com.mijack.course.service.impl;

import com.mijack.course.bean.Product;
import com.mijack.course.dao.ProductDao;
import com.mijack.course.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;

/**
 * @author Mr.Yuan
 * @since 2016/11/18.
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    /**
     * 展示商品列表
     *
     * @param type     type type为1时，筛选用户未购买的商品
     * @param userName
     * @return
     */
    @Override
    public List<Product> listProducts(String type, String userName) {
        List<Product> products = productDao.listProducts();
        Iterator<Product> iterator = products.iterator();
        while (iterator.hasNext()) {
            Product p = iterator.next();
            //在用户登录的情况下,查看甚至buy和sell字段，显示购买信息
            if (userName != null) {
                p.setBuy(p.getTrxCount() > 0);
                p.setSell(p.getTrxCount() > 0);
                //当type为1时，过滤用户已经购买的商品
                if ("1".equals(type)) {
                    if (p.getIsBuy()) {
                        iterator.remove();
                    }
                }
            }
        }
        return products;
    }

    /**
     * 获取商品的信息
     *
     * @param id 商品的id
     * @return
     */
    @Override
    public Product get(int id) {
        Product p = productDao.get(id);
        return p;
    }

    /**
     * 获取商品的信息
     *
     * @param id       商品的id
     * @param userName 当前用户的username，用于区分是否有用户登录
     * @return
     */
    @Override
    public Product get(int id, String userName) {
        Product p = productDao.get(id);
        //当用户登录时设置buy和sell
        if (p != null && userName != null) {
            p.setBuy(p.getTrxCount() > 0);
            p.setSell(p.getTrxCount() > 0);
        }
        return p;
    }

    /**
     * 提交发布商品
     *
     * @param data 提交的商品信息
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void submitProduct(Product data) {
        productDao.submit(data);
    }

    /**
     * 更新商品的数据
     *
     * @param data 提交的商品信息
     * @return 是否成功
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public boolean updateProduct(Product data) {
        return productDao.update(data);
    }

    /**
     * 删除商品信息
     *
     * @param id 商品id
     * @return 是否成功
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public boolean delete(int id) {
        return productDao.delete(id);
    }


    /**
     * 获取商品的总数
     *
     * @return 商品的总数
     */
    @Override
    public int getCount() {
        return productDao.getCount();
    }
}

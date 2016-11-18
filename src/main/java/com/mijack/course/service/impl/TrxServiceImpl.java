package com.mijack.course.service.impl;

import com.mijack.course.bean.Product;
import com.mijack.course.dao.ProductDao;
import com.mijack.course.dao.TrxDao;
import com.mijack.course.service.TrxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Mr.Yuan
 * @since 2016/11/19.
 */
@Service
public class TrxServiceImpl implements TrxService {
    @Autowired
    private TrxDao trxDao;
    @Autowired
    private ProductDao productDao;

    @Override
    public List<Product> getBuyList(String userId) {
        return trxDao.getBuyList(userId);
    }

    @Override
    public boolean buy(String userId, int productId, long currentTimeMillis) {
        Product product = productDao.get(productId);
        boolean buy = false;
        if (!trxDao.isSell(product)) {
            buy = trxDao.buy(userId, product, System.currentTimeMillis());
        }
        return buy;
    }
}

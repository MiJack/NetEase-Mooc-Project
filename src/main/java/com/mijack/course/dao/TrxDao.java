package com.mijack.course.dao;

import com.mijack.course.bean.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by admin on 2016/11/11.
 */
@Repository
public interface TrxDao {
    @Insert("INSERT INTO trx(contentId, personId, price, time) VALUE (#{product.id},#{id},#{product.price},#{date}) ")
    boolean buy(@Param("id") String id, @Param("product") Product product, @Param("date") long date);

    @Select("select count(id) from trx where contentId=#{id}")
    boolean isSell(Product product);

    @Select("SELECT contentId as id, content.title as title, content.icon as image, trx.price as buyPrice, trx.time as buyTime " +
            "FROM trx  LEFT JOIN content ON trx.contentId = content.id WHERE personId = #{id}")
    List<Product> getBuyList(String id);
}

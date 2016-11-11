package com.mijack.course.dao;

import com.mijack.course.bean.Product;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Mr.Yuan on 2016/11/7.
 */
@Repository
public interface ProductDao {
    @Insert("insert into content(title ,icon,abstract,text,price) value" +
            "(#{title },#{image},#{summary},#{detail},#{price})")
    @Options(useGeneratedKeys = true)
    int submit(Product data);

    @Results(value = {
            @Result(column = "abstract", property = "summary", javaType = String.class, jdbcType = JdbcType.BLOB),
            @Result(column = "text", property = "detail", javaType = String.class, jdbcType = JdbcType.BLOB),
            @Result(column = "icon", property = "image"),
    })
    @Select("SELECT id,abstract,icon,price,text,title FROM content WHERE id = #{id} ")
    Product get(int id);

    @Update("update  content set title=#{title } ,icon=#{image},abstract=#{summary},text=#{detail},price=#{price} where id = #{id}")
    boolean update(Product data);

    @Results(value = {
            @Result(column = "abstract", property = "summary", javaType = String.class, jdbcType = JdbcType.BLOB),
            @Result(column = "text", property = "detail", javaType = String.class, jdbcType = JdbcType.BLOB),
            @Result(column = "icon", property = "image"),
    })
    @Select("SELECT content.*  , (SELECT count(*) FROM trx WHERE content.id = trx.id) AS trxCount  FROM content")
    List<Product> listProducts();

    @Delete("DELETE FROM content WHERE id = #{id}")
    boolean delete(int id);
}

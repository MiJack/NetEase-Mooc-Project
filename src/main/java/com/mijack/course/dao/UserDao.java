package com.mijack.course.dao;

import com.mijack.course.bean.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 和用户相关的DAO操作
 *
 * @author Mr.Yuan
 * @since 2016/11/4.
 */
@Repository
public interface UserDao {
    @Select("SELECT id, userName, nickName, userType,password " +
            "FROM person " +
            "WHERE userName = #{username} AND password = #{password}")
    List<User> login(@Param("username") String username, @Param("password") String password);
}
package com.mijack.course.service;

import com.mijack.course.bean.User;

/**
 * 用户相关服务的定义
 *
 * @author Mr.Yuan
 * @since 2016/11/19.
 */
public interface UserService {
    /**
     * 登录接口
     *
     * @param username 用户名
     * @param password 密码
     * @return 对应的用户对象
     */
    User login(String username, String password);
}

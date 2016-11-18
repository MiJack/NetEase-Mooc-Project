package com.mijack.course.service;

import com.mijack.course.bean.User;

/**
 * @author Mr.Yuan
 * @since 2016/11/19.
 */
public interface UserService {
    /**
     * @param username
     * @param password
     * @return
     */
    User login(String username, String password);
}

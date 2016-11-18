package com.mijack.course.service.impl;

import com.mijack.course.bean.User;
import com.mijack.course.dao.UserDao;
import com.mijack.course.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Mr.Yuan
 * @since 2016/11/19.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    /**
     * @param username
     * @param password
     * @return
     */
    @Override
    public User login(String username, String password) {
        List<User> users = userDao.login(username, password);
        if (users == null && users.size() != 1) {
            return null;
        }
        return users.get(0);
    }
}

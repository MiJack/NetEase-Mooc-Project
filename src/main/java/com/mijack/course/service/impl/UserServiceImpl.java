package com.mijack.course.service.impl;

import com.mijack.course.bean.User;
import com.mijack.course.dao.UserDao;
import com.mijack.course.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户相关服务的实现
 * @author Mr.Yuan
 * @since 2016/11/19.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    /**
     * 登录接口
     * @param username 用户名
     * @param password 密码
     * @return 对应的用户对象
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

package com.pbl.backend.service.admin.impl;

import com.pbl.backend.dao.UserDao;
import com.pbl.backend.entity.User;
import com.pbl.backend.service.admin.IUserManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: 杜东方
 * @date: 2020/6/13
 * @description:
 */
@Service
public class UserManageServiceImpl implements IUserManageService {

    @Autowired
    UserDao userDao;

    @Override
    public boolean addUser(User user) {
        userDao.add(user);
        return true;
    }

    @Override
    public boolean addRole(String userId, int role) {
        userDao.addRole(userId, role);
        return true;
    }

    @Override
    public List<User> getAllUser() {
        List<User> users = userDao.getAllUser();
        for (User user: users){
            user.setRole(userDao.getUserRole(user.getId()));
        }
        return users;
    }

    @Override
    public boolean deleteUserById(String userId) {
        userDao.deleteUserById(userId);
        return true;
    }

    @Override
    public User getUserByName(String userName) {
        User user = userDao.loadUserByUsername(userName);
        user.setRole(userDao.getUserRole(user.getId()));
        return user;
    }

    @Override
    public boolean deleteUserRoleById(String userId) {
        userDao.deleteRole(userId);
        return true;
    }
}

package com.pbl.backend.service.common.impl;

import com.pbl.backend.dao.UserDao;
import com.pbl.backend.entity.JwtUser;
import com.pbl.backend.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author: 杜东方
 * @date: 2020/5/17
 * @description: 用户服务类
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        System.out.println(s+"======================");
        User user = userDao.loadUserByUsername(s);
        System.out.println("]]]]]]]]]]]]]]]]]]");
        if(user == null){
            System.out.println("+++++++++++++++++++++");
            throw new UsernameNotFoundException("用户不存在");
        }
//        System.out.println(user);
        user.setRole(userDao.findRoleByUserId(user.getId()));
        System.out.println("user.getPassword()======================");
        System.out.println(user.getPassword());
        return new JwtUser(user);
    }
}

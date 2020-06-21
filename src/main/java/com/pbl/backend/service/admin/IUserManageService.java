package com.pbl.backend.service.admin;

import com.pbl.backend.entity.User;

import java.util.List;

public interface IUserManageService {

    boolean addUser(User user);

    boolean addRole(String userId, int role);

    List<User> getAllUser();

    boolean deleteUserById(String userId);

    User getUserByName(String userName);

    boolean deleteUserRoleById(String userId);

    Integer getUserRole(String userId);
}

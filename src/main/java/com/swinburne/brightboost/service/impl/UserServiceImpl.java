package com.swinburne.brightboost.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.swinburne.brightboost.dao.UserDao;
import com.swinburne.brightboost.domain.User;
import com.swinburne.brightboost.service.UserService;

import jakarta.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    public UserServiceImpl() {
    }

    public List<User> users() {
        return this.userDao.findAll();
    }

    public Integer userSave(User user) {
        return this.userDao.save(user);
    }

    public User userLogin(User user) {
        return this.userDao.userLogin(user);
    }

    public User getUserById(Long id) {
        return this.userDao.findById(id);
    }

    public void userDelete(Long id) {
        this.userDao.deleteById(id);
    }
}

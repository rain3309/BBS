package com.rain.bbs.service.impl;

import com.rain.bbs.dao.UserDao;
import com.rain.bbs.domain.User;
import com.rain.bbs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao dao;

    @Transactional
    public User save(User bean) {
        return dao.save(bean);
    }

    public User get(Integer id) {
        return dao.findOne(id);
    }

    @Transactional
    public User delete(User bean) {
        return dao.delete(bean);
    }
}

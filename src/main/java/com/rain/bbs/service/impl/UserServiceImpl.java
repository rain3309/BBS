package com.rain.bbs.service.impl;

import com.rain.bbs.security.CredentialsDigest;
import com.rain.bbs.security.Digests;
import com.rain.bbs.dao.UserDao;
import com.rain.bbs.domain.User;
import com.rain.bbs.domain.UserDetail;
import com.rain.bbs.security.Encodes;
import com.rain.bbs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService{

    private static final int BYTE_SIZE = 8;
    @Autowired
    private UserDao dao;

    @Autowired
    private CredentialsDigest credentialsDigest;

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

    public User getByUsernmae(String username) {
        return dao.findByUsername(username);
    }

    @Transactional
    public User register(String ip,String orgCode,String username,String password){

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setType("normal");
        UserDetail detail = new UserDetail();
        detail.applyDefaultValue();
        entryptPassword(user);
        return dao.save(user);
    }


    public void entryptPassword(User user){
        byte[] bytes = Digests.generateSalt(BYTE_SIZE);
        String salt = Encodes.encodehex(bytes);
        user.setSalt(salt);
        String password = user.getPassword();
        String encPassword = credentialsDigest.digest(password, bytes);
        user.setPassword(encPassword);
    }

    @Transactional
    public User save(User user,UserDetail datail,String ip){
        return null;
    }


}

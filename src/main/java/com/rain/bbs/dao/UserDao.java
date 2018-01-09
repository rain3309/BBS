package com.rain.bbs.dao;

import com.rain.bbs.domain.User;
import org.springframework.data.repository.Repository;

public interface UserDao extends Repository<User,Integer>{

    public User save(User bean);

    public User findOne(Integer id);

    public User delete(User bean);

}

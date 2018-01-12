package com.rain.bbs.service;

import com.rain.bbs.domain.User;

public interface UserService {

    public User save(User bean);

    public User get(Integer id);

    public User delete(User bean);

    public User getByUsernmae(String username);

}

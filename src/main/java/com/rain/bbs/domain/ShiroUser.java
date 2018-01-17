package com.rain.bbs.domain;

import java.io.Serializable;

public class ShiroUser implements Serializable {
    private static final long serialVersionUID = 4324352352432641875L;

    private Integer id;
    private String username;

    public ShiroUser(Integer id, String username) {
        this.id = id;
        this.username = username;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

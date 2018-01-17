package com.rain.bbs.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "cms_user_org")
public class UserOrg implements Serializable{
    private static final long serialVersionUID = 8296679891248313348L;

    private Integer id;
    private User user;
    private Org org;

    @Id
    @Column(name = "f_id")
    @TableGenerator(name = "tg_cms_user_org",pkColumnName = "f_table",pkColumnValue = "cms_user_org",table = "t_id_table",valueColumnName = "f_id_value",initialValue = 1,allocationSize = 1)
    @GeneratedValue(generator = "tg_cms_user_org",strategy = GenerationType.TABLE)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "f_user_id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "f_org_id")
    public Org getOrg() {
        return org;
    }

    public void setOrg(Org org) {
        this.org = org;
    }
}

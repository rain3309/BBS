package com.rain.bbs.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "cms_user_role")
public class UserRole implements Serializable{
    private static final long serialVersionUID = -5768528023139298041L;

    private Integer id;
    private User user;
    private Role role;

    @Id
    @Column(name = "f_id")
    @TableGenerator(name = "tg_cms_user",pkColumnName = "f_table",pkColumnValue = "cms_user",table = "t_id_table",valueColumnName = "f_id_value",initialValue = 1,allocationSize = 1)
    @GeneratedValue(generator = "tg_cms_user",strategy = GenerationType.TABLE)
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
    @JoinColumn(name = "f_role_id")
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}

package com.rain.bbs.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "cms_role")
public class Role implements Serializable {
    private static final long serialVersionUID = 5037468489070821303L;

    private Integer id;
    private String name;
    private String description;

    private Set<UserRole> userRoles = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.REMOVE,mappedBy = "role")
    public Set<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    @Id
    @Column(name = "f_id")
    @TableGenerator(name = "tg_cms_role",pkColumnName = "f_table",pkColumnValue = "cms_role",table = "t_id_table",valueColumnName = "f_id_value",initialValue = 1,allocationSize = 1)
    @GeneratedValue(generator = "tg_cms_role",strategy = GenerationType.TABLE)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    @Column(name = "f_name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Column(name = "f_description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

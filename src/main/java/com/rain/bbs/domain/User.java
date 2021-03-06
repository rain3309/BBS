package com.rain.bbs.domain;

import com.rain.bbs.security.Encodes;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 *
 */
@Entity
@Table(name="cms_user")
public class User extends BaseModel implements Serializable{

    private static final long serialVersionUID = -4310057677554850349L;
    private Integer id;
    private String username;
    private String password;
    private String salt;
    private String realname;
    private String gender;
    private Date birthDay;
    private String phone;
    private String email;
    private String status;
    private String rank;
    private String type;


    private Set<UserDetail> details = new HashSet<>(0);
    private Set<UserOrg> userOrgs = new HashSet<>(0);
    private Set<UserRole> userRoles = new HashSet<>(0);

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "user",cascade = CascadeType.REMOVE)
    public Set<UserDetail> getDetails() {
        return details;
    }

    public void setDetails(Set<UserDetail> details) {
        this.details = details;
    }

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "user",cascade = CascadeType.REMOVE)
    public Set<UserOrg> getUserOrgs() {
        return userOrgs;
    }

    public void setUserOrgs(Set<UserOrg> userOrgs) {
        this.userOrgs = userOrgs;
    }

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "user",cascade = CascadeType.REMOVE)
    public Set<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    @Transient
    public byte[] getByteSalt(){
        String salt = getSalt();
        if(StringUtils.isNoneBlank(salt)){
            return Encodes.decodehex(salt);
        }else{
            return null;
        }

    }

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
    @Column(name = "f_username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    @Column(name = "f_password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @Column(name = "f_salt")
    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
    @Column(name = "f_realname")
    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }
    @Column(name = "f_gender")
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "f_birthday")
    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }
    @Column(name = "f_phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    @Column(name = "f_email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    @Column(name = "f_status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    @Column(name = "f_rank")
    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }
    @Column(name = "f_type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

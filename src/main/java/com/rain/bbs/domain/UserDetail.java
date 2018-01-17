package com.rain.bbs.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "cms_user_detail")
public class UserDetail implements Serializable {
    private static final long serialVersionUID = -9149631880278308003L;
    private static final long LOGIN_ERROR_MILLIS = 30 * 60 * 1000;

    @Transient
    public boolean isCaptchaRequire(){
        if(getLoginErrorCount() > 3 && System.currentTimeMillis() - getLoginErrorDate().getTime() < LOGIN_ERROR_MILLIS){
            return true;
        }else{
            return false;
        }
    }

    @Transient
    public void applyDefaultValue(){
        if(getCreationIp() == null){
            setCreationIp("127.0.0.1");
        }
        if(getCreationDate() == null){
            setCreationDate(new Date());
        }
        if(getLoginErrorCount() == null){
            setLoginErrorCount(0);
        }
        if(getLogins() == null){
            setLogins(0);
        }
    }
    private Integer id;
    private User user;
    private Date loginErrorDate;
    private Integer loginErrorCount;
    private Date prevLoginDate;
    private String prevLoginIp;
    private Date lastLoginDate;
    private String lastLoginIp;
    private Date creationDate;
    private String creationIp;
    private Integer logins;

    @Id
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @MapsId
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="f_user_id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "f_loginerror_date")
    public Date getLoginErrorDate() {
        return loginErrorDate;
    }

    public void setLoginErrorDate(Date loginErrorDate) {
        this.loginErrorDate = loginErrorDate;
    }
    @Column(name = "f_loginerror_count")
    public Integer getLoginErrorCount() {
        return loginErrorCount;
    }

    public void setLoginErrorCount(Integer loginErrorCount) {
        this.loginErrorCount = loginErrorCount;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "f_prelogin_date")
    public Date getPrevLoginDate() {
        return prevLoginDate;
    }

    public void setPrevLoginDate(Date prevLoginDate) {
        this.prevLoginDate = prevLoginDate;
    }
    @Column(name = "f_prelogin_ip")
    public String getPrevLoginIp() {
        return prevLoginIp;
    }

    public void setPrevLoginIp(String prevLoginIp) {
        this.prevLoginIp = prevLoginIp;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "f_lastlogin_date")
    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }
    @Column(name = "f_lastlogin_ip")
    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "f_creation_date")
    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
    @Column(name = "f_creation_ip")
    public String getCreationIp() {
        return creationIp;
    }

    public void setCreationIp(String creationIp) {
        this.creationIp = creationIp;
    }
    @Column(name = "f_logins")
    public Integer getLogins() {
        return logins;
    }

    public void setLogins(Integer logins) {
        this.logins = logins;
    }
}

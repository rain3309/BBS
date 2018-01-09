package com.rain.bbs.domain;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class BaseModel implements Serializable{
    private static final long serialVersionUID = 1L;

    public static final String YES = "YES";
    public static final String NO = "NO";

    private Date createTime;
    private Date modifyTime;
    private String operator;
    private String enable;
    private int version;

    @PreUpdate
    public void preUpdate(){
        setModifyTime(new Date());
    }

    @PrePersist
    public void prePersist(){
        setCreateTime(new Date());
        setEnable("YES");
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="f_create_time")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="f_modify_time")
    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    @Column(name="f_operator")
    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    @Pattern(regexp = "YES|NO")
    @Column(name = "f_enable")
    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable;
    }

    @Version
    @Column(name="f_version")
    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}

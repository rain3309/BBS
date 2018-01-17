package com.rain.bbs.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "cms_org")
public class Org implements Serializable{

    private static final long serialVersionUID = 8254937561724968683L;

    private Integer id;
    private String name;
    private String fullName;
    private String description;
    private String phone;
    private String fax;
    private String address;
    private String province;
    private String city;
    private String area;
    private String orgCode;

    private Set<UserOrg> userOrgs = new HashSet<>();

    @OneToMany(mappedBy = "org",cascade = CascadeType.REMOVE,fetch = FetchType.LAZY)
    public Set<UserOrg> getUserOrgs() {
        return userOrgs;
    }

    public void setUserOrgs(Set<UserOrg> userOrgs) {
        this.userOrgs = userOrgs;
    }

    @Id
    @Column(name = "f_id")
    @TableGenerator(name = "tg_cms_org",pkColumnName = "f_table",pkColumnValue = "cms_org",table = "t_id_table",valueColumnName = "f_id_value",initialValue = 1,allocationSize = 1)
    @GeneratedValue(generator = "tg_cms_org",strategy = GenerationType.TABLE)
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
    @Column(name = "f_full_name")
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    @Column(name = "f_description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    @Column(name = "f_phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    @Column(name = "f_fax")
    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }
    @Column(name = "f_address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    @Column(name = "f_province")
    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }
    @Column(name = "f_city")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    @Column(name = "f_area")
    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
    @Column(name = "f_orgcode")
    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }
}

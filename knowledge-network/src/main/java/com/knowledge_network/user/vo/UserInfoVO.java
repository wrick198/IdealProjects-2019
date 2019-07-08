package com.knowledge_network.user.vo;

import com.knowledge_network.user.entity.User;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by pentonbin on 17-12-14
 * <p>
 * 个人信息页面数据对象
 */
public class UserInfoVO {

    private Integer id;
    private String username;
    private String email;
    private String phone;
    private Timestamp registerDatetime;
    private String realName;
    private Integer sex;
    private String imageUrl;
    private Date birthday;
    private String description;
    private String address;
    private String userRole;
    private Integer enable;

    public UserInfoVO() {
    }

    public UserInfoVO(User user) {
        if (user != null) {
            id = user.getId();
            username = user.getUsername();
            email = user.getEmail();
            phone = user.getPhone();
            registerDatetime = user.getRegisterDatetime();
            realName = user.getRealName();
            sex = user.getSex();
            imageUrl = user.getImageUrl();
            birthday = user.getBirthday();
            description = user.getDescription();
            address = user.getAddress();
            userRole = user.getInitialRole().getRole();
            enable = user.getEnable();
        }
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Timestamp getRegisterDatetime() {
        return registerDatetime;
    }

    public void setRegisterDatetime(Timestamp registerDatetime) {
        this.registerDatetime = registerDatetime;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }
}

package com.aqfun.p2p.model.user;

import java.io.Serializable;
import java.util.Date;
/**
 * 用户
 * ClassName:BidInfo
 * @date 2018/9/5
 * @author aq
 */
public class User implements Serializable{
    private static final long serialVersionUID = -3437057640913901875L;
    /**
     * 用户标识
     */
    private Integer id;
    /**
     * 用户手机号
     */
    private String phone;
    /**
     * 用户密码
     */
    private String loginPassword;
    /**
     * 用户真实姓名:通过实名认证后才有
     */
    private String name;
    /**
     * 用户身份证:通过实名认证后才有
     */
    private String idCard;
    /**
     * 注册时间
     */
    private Date addTime;
    /**
     * 最近一次登录时间
     */
    private Date lastLoginTime;
    /**
     * 用户头像:用户头像的路径
     */
    private String headerImage;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword == null ? null : loginPassword.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard == null ? null : idCard.trim();
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getHeaderImage() {
        return headerImage;
    }

    public void setHeaderImage(String headerImage) {
        this.headerImage = headerImage == null ? null : headerImage.trim();
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", phone='" + phone + '\'' +
                ", loginPassword='" + loginPassword + '\'' +
                ", name='" + name + '\'' +
                ", idCard='" + idCard + '\'' +
                ", addTime=" + addTime +
                ", lastLoginTime=" + lastLoginTime +
                ", headerImage='" + headerImage + '\'' +
                '}';
    }
}
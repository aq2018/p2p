package com.aqfun.p2p.model.vo;

import java.io.Serializable;

/**
 * 投资排行榜实体bean
 * Package : com.aqfun.p2p.model.vo
 * ClassName : TopUser
 *
 * @author aq
 * @date 2018/9/10
 */
public class TopUser implements Serializable{

    private static final long serialVersionUID = 5082877722486877329L;
    /**
     * 手机
     */
    private String phone;
    /**
     * 累计投资金额
     */
    private double totalMoney;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }

    @Override
    public String toString() {
        return "TopUser{" +
                "phone='" + phone + '\'' +
                ", totalMoney=" + totalMoney +
                '}';
    }
}

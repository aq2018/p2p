package com.aqfun.p2p.model.user;

import java.io.Serializable;
/**
 * 用户账户资金
 * ClassName:BidInfo
 * @date 2018/9/5
 * @author aq
 */
public class FinanceAccount implements Serializable{
    private static final long serialVersionUID = -1402081863472096872L;
    /**
     * 账户标识
     */
    private Integer id;
    /**
     * 用户标识
     */
    private Integer uid;
    /**
     * 账户可用金额
     */
    private Double availableMoney;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Double getAvailableMoney() {
        return availableMoney;
    }

    public void setAvailableMoney(Double availableMoney) {
        this.availableMoney = availableMoney;
    }

    @Override
    public String toString() {
        return "FinanceAccount{" +
                "id=" + id +
                ", uid=" + uid +
                ", availableMoney=" + availableMoney +
                '}';
    }
}
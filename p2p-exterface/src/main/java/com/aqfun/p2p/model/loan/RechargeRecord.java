package com.aqfun.p2p.model.loan;

import java.io.Serializable;
import java.util.Date;
/**
 * 充值记录
 * ClassName:BidInfo
 * @date 2018/9/5
 * @author aq
 */
public class RechargeRecord implements Serializable{
    private static final long serialVersionUID = 3177204716904033893L;
    /**
     * 充值记录标识
     */
    private Integer id;
    /**
     * 用户标识
     */
    private Integer uid;
    /**
     * 充值订单号:使用redis生成一个全局唯一书籍
     */
    private String rechargeNo;
    /**
     * 充值记录状态:0充值中, 1充值成功, 2充值失败
     */
    private String rechargeStatus;
    /**
     * 充值金额
     */
    private Double rechargeMoney;
    /**
     * 充值时间
     */
    private Date rechargeTime;
    /**
     * 充值备注
     */
    private String rechargeDesc;

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

    public String getRechargeNo() {
        return rechargeNo;
    }

    public void setRechargeNo(String rechargeNo) {
        this.rechargeNo = rechargeNo == null ? null : rechargeNo.trim();
    }

    public String getRechargeStatus() {
        return rechargeStatus;
    }

    public void setRechargeStatus(String rechargeStatus) {
        this.rechargeStatus = rechargeStatus == null ? null : rechargeStatus.trim();
    }

    public Double getRechargeMoney() {
        return rechargeMoney;
    }

    public void setRechargeMoney(Double rechargeMoney) {
        this.rechargeMoney = rechargeMoney;
    }

    public Date getRechargeTime() {
        return rechargeTime;
    }

    public void setRechargeTime(Date rechargeTime) {
        this.rechargeTime = rechargeTime;
    }

    public String getRechargeDesc() {
        return rechargeDesc;
    }

    public void setRechargeDesc(String rechargeDesc) {
        this.rechargeDesc = rechargeDesc == null ? null : rechargeDesc.trim();
    }

    @Override
    public String toString() {
        return "RechargeRecord{" +
                "id=" + id +
                ", uid=" + uid +
                ", rechargeNo='" + rechargeNo + '\'' +
                ", rechargeStatus='" + rechargeStatus + '\'' +
                ", rechargeMoney=" + rechargeMoney +
                ", rechargeTime=" + rechargeTime +
                ", rechargeDesc='" + rechargeDesc + '\'' +
                '}';
    }
}
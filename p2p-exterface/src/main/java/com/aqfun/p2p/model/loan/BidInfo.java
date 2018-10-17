package com.aqfun.p2p.model.loan;

import com.aqfun.p2p.model.user.User;

import java.io.Serializable;
import java.util.Date;

/**
 * 投资记录
 * ClassName:BidInfo
 * @author aq
 * @date 2018/9/5
 */
public class BidInfo implements Serializable{
    private static final long serialVersionUID = -8932932968923774919L;
    //投标记录ID
    private Integer id;
    //产品ID
    private Integer loanId;
    //用户ID
    private Integer uid;
    //投标金额
    private Double bidMoney;
    //投标时间
    private Date bidTime;
    /**
     * 投资状态:0失败,1成功
     */
    private Integer bidStatus;

    /**
     * 用户
     */
    private User user;

    /**
     * 用户投资的产品
     * @return
     */
    private LoanInfo loanInfo;

    public LoanInfo getLoanInfo() {
        return loanInfo;
    }

    public void setLoanInfo(LoanInfo loanInfo) {
        this.loanInfo = loanInfo;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLoanId() {
        return loanId;
    }

    public void setLoanId(Integer loanId) {
        this.loanId = loanId;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Double getBidMoney() {
        return bidMoney;
    }

    public void setBidMoney(Double bidMoney) {
        this.bidMoney = bidMoney;
    }

    public Date getBidTime() {
        return bidTime;
    }

    public void setBidTime(Date bidTime) {
        this.bidTime = bidTime;
    }

    public Integer getBidStatus() {
        return bidStatus;
    }

    public void setBidStatus(Integer bidStatus) {
        this.bidStatus = bidStatus;
    }

    @Override
    public String toString() {
        return "BidInfo{" +
                "id=" + id +
                ", loanId=" + loanId +
                ", uid=" + uid +
                ", bidMoney=" + bidMoney +
                ", bidTime=" + bidTime +
                ", bidStatus=" + bidStatus +
                ", user=" + user +
                ", loanInfo=" + loanInfo +
                '}';
    }
}
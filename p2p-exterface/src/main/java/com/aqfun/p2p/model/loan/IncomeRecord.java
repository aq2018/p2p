package com.aqfun.p2p.model.loan;

import java.io.Serializable;
import java.util.Date;

/**
 * 产品
 * ClassName:BidInfo
 * @date 2018/9/5
 * @author aq
 */
public class IncomeRecord implements Serializable {
    private static final long serialVersionUID = -2022009375318297665L;
    //用户收益记录标识
    private Integer id;
    //用户标识
    private Integer uid;
    //产品标识
    private Integer loanId;
    //投资记录标识
    private Integer bidId;
    //投资金额
    private Double bidMoney;
    //收益时间
    private Date incomeDate;
    //收益金额
    private Double incomeMoney;
    //收益状态: 0未返,1已返
    private Integer incomeStatus;

    /**
     * 收益关联的产品
     * @return
     */
    private  LoanInfo loanInfo;

    public LoanInfo getLoanInfo() {
        return loanInfo;
    }

    public void setLoanInfo(LoanInfo loanInfo) {
        this.loanInfo = loanInfo;
    }

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

    public Integer getLoanId() {
        return loanId;
    }

    public void setLoanId(Integer loanId) {
        this.loanId = loanId;
    }

    public Integer getBidId() {
        return bidId;
    }

    public void setBidId(Integer bidId) {
        this.bidId = bidId;
    }

    public Double getBidMoney() {
        return bidMoney;
    }

    public void setBidMoney(Double bidMoney) {
        this.bidMoney = bidMoney;
    }

    public Date getIncomeDate() {
        return incomeDate;
    }

    public void setIncomeDate(Date incomeDate) {
        this.incomeDate = incomeDate;
    }

    public Double getIncomeMoney() {
        return incomeMoney;
    }

    public void setIncomeMoney(Double incomeMoney) {
        this.incomeMoney = incomeMoney;
    }

    public Integer getIncomeStatus() {
        return incomeStatus;
    }

    public void setIncomeStatus(Integer incomeStatus) {
        this.incomeStatus = incomeStatus;
    }

    @Override
    public String toString() {
        return "IncomeRecord{" +
                "id=" + id +
                ", uid=" + uid +
                ", loanId=" + loanId +
                ", bidId=" + bidId +
                ", bidMoney=" + bidMoney +
                ", incomeDate=" + incomeDate +
                ", incomeMoney=" + incomeMoney +
                ", incomeStatus=" + incomeStatus +
                ", loanInfo=" + loanInfo +
                '}';
    }
}
package com.aqfun.p2p.model.loan;

import java.io.Serializable;
import java.util.Date;
/**
 * 投资产品信息
 * ClassName:LoanInfo
 * @author aq
 * @date 2018/9/5
 */
public class LoanInfo implements Serializable {
    private static final long serialVersionUID = 243149065929783265L;
    /**
     * 产品标识
     */
    private Integer id;
    /**
     * 产品名称
     */
    private String productName;
    /**
     * 产品利率
     */
    private Double rate;
    /**
     * 产品周期
     */
    private Integer cycle;
    /**
     * 产品发布时间
     */
    private Date releaseTime;
    /**
     * 产品类型: 0新手宝, 1优选产品, 2散标产品
     */
    private Integer productType;
    /**
     * 产品编号
     */
    private String productNo;
    /**
     * 产品募集资金(产品金额)
     */
    private Double productMoney;
    /**
     * 产品剩余可投金额
     */
    private Double leftProductMoney;
    /**
     * 起投金额(最低投资金额)
     */
    private Double bidMinLimit;
    /**
     * 最高投资金额
     */
    private Double bidMaxLimit;
    /**
     * 产品状态:0未满标, 1已满标, 2满标已生成收益计划
     */
    private Integer productStatus;
    /**
     * 产品投资满标时间
     */
    private Date productFullTime;
    /**
     * 版本号
     */
    private Integer version;
    /**
     * 产品描述
     */
    private String productDesc;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Integer getCycle() {
        return cycle;
    }

    public void setCycle(Integer cycle) {
        this.cycle = cycle;
    }

    public Date getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }

    public Integer getProductType() {
        return productType;
    }

    public void setProductType(Integer productType) {
        this.productType = productType;
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo == null ? null : productNo.trim();
    }

    public Double getProductMoney() {
        return productMoney;
    }

    public void setProductMoney(Double productMoney) {
        this.productMoney = productMoney;
    }

    public Double getLeftProductMoney() {
        return leftProductMoney;
    }

    public void setLeftProductMoney(Double leftProductMoney) {
        this.leftProductMoney = leftProductMoney;
    }

    public Double getBidMinLimit() {
        return bidMinLimit;
    }

    public void setBidMinLimit(Double bidMinLimit) {
        this.bidMinLimit = bidMinLimit;
    }

    public Double getBidMaxLimit() {
        return bidMaxLimit;
    }

    public void setBidMaxLimit(Double bidMaxLimit) {
        this.bidMaxLimit = bidMaxLimit;
    }

    public Integer getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(Integer productStatus) {
        this.productStatus = productStatus;
    }

    public Date getProductFullTime() {
        return productFullTime;
    }

    public void setProductFullTime(Date productFullTime) {
        this.productFullTime = productFullTime;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc == null ? null : productDesc.trim();
    }

    @Override
    public String toString() {
        return "LoanInfo{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", rate=" + rate +
                ", cycle=" + cycle +
                ", releaseTime=" + releaseTime +
                ", productType=" + productType +
                ", productNo='" + productNo + '\'' +
                ", productMoney=" + productMoney +
                ", leftProductMoney=" + leftProductMoney +
                ", bidMinLimit=" + bidMinLimit +
                ", bidMaxLimit=" + bidMaxLimit +
                ", productStatus=" + productStatus +
                ", productFullTime=" + productFullTime +
                ", version=" + version +
                ", productDesc='" + productDesc + '\'' +
                '}';
    }
}
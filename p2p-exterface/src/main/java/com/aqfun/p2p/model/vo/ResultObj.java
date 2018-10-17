package com.aqfun.p2p.model.vo;

import java.io.Serializable;

/**
 * ClassName : ResultObj
 *
 * @author aq
 * @date 2018/9/7
 */
public class ResultObj implements Serializable{
    private static final long serialVersionUID = 7799676946097547766L;
    /**
     * 状态码:0失败,1成功
     */
    private String errCode;
    /**
     * 消息
     */
    private String errMessage;

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrMessage() {
        return errMessage;
    }

    public void setErrMessage (String errMessage) {
        this.errMessage = errMessage;
    }

    @Override
    public String toString() {
        return "ResultObj {" +
                "errCode='" + errCode + '\'' +
                ", errMessage='" + errMessage + '\'' +
                '}';
    }
}

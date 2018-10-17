package com.aqfun.p2p.config;

import java.io.Serializable;

/**
 * 实名认证api配置
 * ClassName : Config
 *
 * @author aq
 * @date 2018/9/7
 */
public class Config implements Serializable{
    private static final long serialVersionUID = 2524683574670035130L;
    /**
     * 实名认证apiKey
     */
    private String realNameAppKey;
    /**
     * 实名认证apiurl
     */
    private String realNameUrl;

    public String getRealNameAppKey() {
        return realNameAppKey;
    }

    public void setRealNameAppKey(String realNameAppKey) {
        this.realNameAppKey = realNameAppKey;
    }

    public String getRealNameUrl() {
        return realNameUrl;
    }

    public void setRealNameUrl(String realNameUrl) {
        this.realNameUrl = realNameUrl;
    }

    @Override
    public String toString() {
        return "Config{" +
                "realNameAppKey='" + realNameAppKey + '\'' +
                ", realNameUrl='" + realNameUrl + '\'' +
                '}';
    }
}

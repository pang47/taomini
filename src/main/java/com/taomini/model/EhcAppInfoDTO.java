package com.taomini.model;

import java.io.Serializable;

/**
 *
 * @author chentao
 * @create 2020-1-8
 * @since 1.0.0
 */
public class EhcAppInfoDTO implements Serializable {

    private String area;
    private String appId;
    private String appKey;
    private String termId;
    private String gatewayUrl;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getTermId() {
        return termId;
    }

    public void setTermId(String termId) {
        this.termId = termId;
    }

    public String getGatewayUrl() {
        return gatewayUrl;
    }

    public void setGatewayUrl(String gatewayUrl) {
        this.gatewayUrl = gatewayUrl;
    }
}
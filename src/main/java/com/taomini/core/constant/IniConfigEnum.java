package com.taomini.core.constant;

public enum IniConfigEnum {
    TRANSTYPE("1001", "01"),
    TRANSTYPEIMAGE("1001", "02");

    IniConfigEnum(String iniType, String iniClass){
        this.iniType = iniType;
        this.iniClass = iniClass;
    }

    private String iniType;
    private String iniClass;

    public String getIniType() {
        return iniType;
    }

    public void setIniType(String iniType) {
        this.iniType = iniType;
    }

    public String getIniClass() {
        return iniClass;
    }

    public void setIniClass(String iniClass) {
        this.iniClass = iniClass;
    }
}

package com.tvyanqiu.bean;

public class VersionBean {

    /**
     * appAddress : https://yanqiu12.github.io/apks/tv_yanqiu_1.0.apk
     * usedApp : true
     * usedVersionCode : 1,2,3
     * versionCode : 1
     */

    private String appAddress;
    private boolean usedApp;
    private String usedVersionCode;
    private int versionCode;

    public String getAppAddress() {
        return appAddress;
    }

    public void setAppAddress(String appAddress) {
        this.appAddress = appAddress;
    }

    public boolean isUsedApp() {
        return usedApp;
    }

    public void setUsedApp(boolean usedApp) {
        this.usedApp = usedApp;
    }

    public String getUsedVersionCode() {
        return usedVersionCode;
    }

    public void setUsedVersionCode(String usedVersionCode) {
        this.usedVersionCode = usedVersionCode;
    }

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    @Override
    public String toString() {
        return "VersionBean{" +
                "appAddress='" + appAddress + '\'' +
                ", usedApp=" + usedApp +
                ", usedVersionCode='" + usedVersionCode + '\'' +
                ", versionCode=" + versionCode +
                '}';
    }
}

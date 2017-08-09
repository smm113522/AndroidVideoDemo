package com.smm.andfixdemo;

/**
 * Created by Administrator on 2017/8/7 0007.
 */

public class Version {


    /**
     * downloadAddress : xxx
     * plugInGackage : xxx
     * versionCode : 1
     * versionName : 1.1
     * timeUpload : 2017-08-07 10:00
     */

    private String downloadAddress;
    private String plugInGackage;
    private int versionCode;
    private String versionName;
    private String timeUpload;

    public String getDownloadAddress() {
        return downloadAddress;
    }

    public void setDownloadAddress(String downloadAddress) {
        this.downloadAddress = downloadAddress;
    }

    public String getPlugInGackage() {
        return plugInGackage;
    }

    public void setPlugInGackage(String plugInGackage) {
        this.plugInGackage = plugInGackage;
    }

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getTimeUpload() {
        return timeUpload;
    }

    public void setTimeUpload(String timeUpload) {
        this.timeUpload = timeUpload;
    }
}

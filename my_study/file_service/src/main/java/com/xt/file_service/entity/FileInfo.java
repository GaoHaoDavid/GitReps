package com.xt.file_service.entity;


public class FileInfo {

    long fSize;
    String fName;

    public FileInfo(long fSize, String fName) {
        this.fSize = fSize;
        this.fName = fName;
    }

    public long getfSize() {
        return fSize;
    }

    public String getfName() {
        return fName;
    }

    public void setfSize(long fSize) {
        this.fSize = fSize;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }
}

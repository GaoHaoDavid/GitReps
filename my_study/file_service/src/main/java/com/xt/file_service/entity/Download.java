package com.xt.file_service.entity;

import com.xt.file_service.client.DownloadClient;

public class Download implements Runnable {

    DownloadClient client=new DownloadClient();

    long start;
    long end;
    long page;
    String fName;

    public Download(long start, long end, long page, String fName) {
        this.start = start;
        this.end = end;
        this.page = page;
        this.fName = fName;
    }

    @Override
    public void run() {
        try {
            FileInfo info = client.download( start, end, page, fName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

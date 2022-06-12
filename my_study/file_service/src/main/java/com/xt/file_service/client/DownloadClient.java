package com.xt.file_service.client;

import com.xt.file_service.entity.Download;
import com.xt.file_service.entity.FileInfo;
import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URLDecoder;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
public class DownloadClient {

    //每个分片大小
    private final static long PAGE_SIZE=1024l*1024l*50l;
    private final static String DOWN_PATH="D:\\fileStorage";
    ExecutorService pool= Executors.newFixedThreadPool(10);

    @RequestMapping("/downloadFile")
    public String downloadFile() throws Exception{
        //探测
        FileInfo fileInfo = download( 0, 10, -1, null);
        //总分片数
        long pages=fileInfo.getfSize()/PAGE_SIZE;

        //多线程分片下载
        for (long i = 0; i <= pages; i++) {
            pool.submit(new Download(i*PAGE_SIZE,(i+1)*PAGE_SIZE-1,i,fileInfo.getfName()));
        }

        return "success";
    }


    /**
     * 分片下载
     * @param start
     * @param end
     * @param page
     * @param fName
     * @return
     * @throws Exception
     */
    public FileInfo download(long start, long end, long page, String fName) throws Exception{
        File file=new File(DOWN_PATH,page+"-"+fName);

        //判断文件是否是完整的
        if(file.exists()&&page!=-1&&file.length()==PAGE_SIZE){
            return null;
        }

        //从请求中读取文件信息
        HttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://127.0.0.1:8080/file/download");
        httpGet.setHeader("Range","bytes="+start+"-"+end);
        HttpResponse response = httpClient.execute(httpGet);
        HttpEntity entity = response.getEntity();
        InputStream is = entity.getContent();
        String fSize = response.getFirstHeader("fSize").getValue();
        fName = URLDecoder.decode(response.getFirstHeader("fName").getValue(),"utf-8");

        FileOutputStream fos=new FileOutputStream(file);
        byte[] buf=new byte[1024];
        int len=0;
        while ((len=is.read(buf))!=-1){
            fos.write(buf,0,len);
        }
        is.close();
        fos.flush();
        fos.close();

        //最后一个分片
        if(end - Long.valueOf(fSize) >= 0){
            mergeFile(fName,page);
        }

        return new FileInfo(Long.valueOf(fSize),fName);
    }


    /**
     * 分片合并
     * @param fName
     * @param page
     * @throws Exception
     */
    public void mergeFile(String fName, long page) throws Exception {
        File file=new File(DOWN_PATH,fName);
        BufferedOutputStream bos=new BufferedOutputStream(new FileOutputStream(file));

        for (long i = 0; i <= page; i++) {
            File tempFile = new File(DOWN_PATH,i+"-"+fName);
            //多线程环境下，分片不存在到或者分片未写完，休眠等待
            while (!file.exists()||(i != page && tempFile.length() < PAGE_SIZE)){
                Thread.sleep(100);
            }
            byte[] bytes = FileUtils.readFileToByteArray(tempFile);
            bos.write(bytes);
            bos.flush();
            tempFile.delete();
        }

        //删除探测文件
        File f = new File(DOWN_PATH,-1+"-null");
        f.delete();
        bos.flush();
        bos.close();
    }
}

package com.xt.file_service.controller;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;

@RestController
@RequestMapping("/file")
public class FileController {

    private static String UTF8="utf-8";


    /**
     * 文件上传(分片上传/断点续传)
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("/upload")
    public void upload(HttpServletRequest request, HttpServletResponse response) throws Exception{

        //1.获取请求中的文件参数
        response.setCharacterEncoding(UTF8);
        //当前分片数
        Integer chunk=null;
        //总的分片数
        Integer chunks=null;
        //文件名称
        String fileName=null;
        //上传文件目录
        String uploadPath="D:\\fileStorage";
        BufferedOutputStream bos=null;

        try{
            //1.新建文件处理工厂
            DiskFileItemFactory factory=new DiskFileItemFactory();
            //2.设置缓存区大小
            factory.setSizeThreshold(1024);
            //3.设置临时目录
            factory.setRepository(new File(uploadPath));
            //4.解析请求携带的文件信息
            ServletFileUpload upload=new ServletFileUpload(factory);
                //设置每个分片最大大小
            upload.setFileSizeMax(5l*1024l*1024l*1024l);
                //设置文件最大大小
            upload.setSizeMax(10l*1024l*1024l*1024l);
            List<FileItem> fileItems = upload.parseRequest(request);
            //5.获取传递的文件参数（分片信息和文件名称）
            for (FileItem item : fileItems) {
                //如果是域对象就获取该参数
                if(item.isFormField()){
                    if("chunk".equals(item.getFieldName())){
                        chunk=Integer.parseInt(item.getString(UTF8));
                    }
                    if("chunks".equals(item.getFieldName())){
                        chunks=Integer.parseInt(item.getString(UTF8));
                    }
                    if("name".equals(item.getFieldName())){
                        fileName=item.getString(UTF8);
                    }
                }
            }

            //6.断点续传
            for (FileItem item : fileItems) {
                //临时文件名
                String tempFileName=fileName;
                //如果是文件信息
                if(!item.isFormField()){
                    if(fileName!=null){
                        if(chunk!=null){
                            tempFileName=chunk+"_"+fileName;
                        }
                        //判断该文件分片不存在，直接写入
                        File tempFile=new File(uploadPath,tempFileName);
                        if(!tempFile.exists()){
                            item.write(tempFile)  ;
                        }
                    }
                }
            }

            //7.分片合并:当传递的是最后一个分片时，进行文件合并
            if(chunk!=null&&chunks!=null&&chunk.intValue()==chunks.intValue()-1){
                File file=new File(uploadPath,fileName);
                bos=new BufferedOutputStream(new FileOutputStream(file));

                for (int i = 0; i < chunks; i++) {
                    //依次读取每个分片的字节流，写入合并的文件中后，删除该分片
                    File tempFile=new File(uploadPath,i+"_"+fileName);
                    while (!tempFile.exists()){
                        Thread.sleep(100);
                    }
                    byte[] bytes = FileUtils.readFileToByteArray(tempFile);
                    bos.write(bytes);
                    bos.flush();
                    tempFile.delete();
                }
                bos.flush();
            }

            //8.响应信息
            response.getWriter().write("文件:"+fileName+"上传成功！");
        }finally {
            //关闭流
            try {
                if(bos!=null) bos.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }

    }

    @RequestMapping("/download")
    public void download(HttpServletRequest request,HttpServletResponse response) throws Exception{

        response.setCharacterEncoding(UTF8);
        String filePath="D:\\C视频剪辑\\英雄联盟\\【S10】TES VS JDG\\剪辑\\【2020 LPL夏季赛】决赛 TES vs JDG 第四局.mp4";
        File file=new File(filePath);
        InputStream is=null;
        OutputStream os=null;
        try {
            long fSize=file.length();
            //1.设置请求头
            String fileName= URLEncoder.encode(file.getName(),UTF8);
            response.setContentType("application/x-download");
            response.setHeader("Content-Disposition","attachment;filename="+fileName);
            response.setHeader("Accept-Range","bytes");
            response.setHeader("fSize", String.valueOf(fSize));
            response.setHeader("fName", fileName);
            //2.获取文件参数
            long pos=0,last=fSize-1,sum=0;
            if(null!=request.getHeader("Range")){
                response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);
                String range = request.getHeader("Range").replaceAll("bytes=", "");
                String[] strRange = range.split("-");
                if(strRange.length==2){
                    pos = Long.parseLong(strRange[0].trim());
                    last = Long.parseLong(strRange[1].trim());
                    if(last>fSize-1) last=fSize-1;
                }else {
                    pos = Long.parseLong(range.replaceAll("-", "").trim());
                }
            }

            //剩余文件长度
            long rangeLength=last-pos+1;
            String contentRange=new StringBuffer("bytes ").
                    append(pos).
                    append("-").
                    append(last).
                    append("/").
                    append(fSize).toString();
            response.setHeader("Content-Range",contentRange);
            response.setHeader("Content-Length", String.valueOf(rangeLength));

            //3.读取文件
            os=new BufferedOutputStream(response.getOutputStream());
            is=new BufferedInputStream(new FileInputStream(file));
            is.skip(pos);
            byte[] buf=new byte[1024];
            int len=0;
            while (sum<rangeLength){
                len=is.read(buf,0, rangeLength-sum<=buf.length? (int) (rangeLength - sum) :buf.length);
                sum=sum+len;
                os.write(buf,0,len);
            }
            System.out.println("文件下载完成！");

        }finally {
            if(is!=null) is.close();
            if(os!=null) os.close();
        }

    }
}

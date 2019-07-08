package com.knowledge_network.support.utils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wshish000 on 17-12-12
 */
public class DownloadUtil {
    public static Map download(HttpServletRequest request, HttpServletResponse response, String path){
        //定义返回类型，成功或失败，若失败返回错误信息
        Map<String, String> map = new HashMap<>();
        //得到要下载的文件名
        String fileName = request.getParameter("filename");
        try{
            fileName = new String(fileName.getBytes("ISO8859-1"), "UTF-8");
            ServletContext sc = request.getSession().getServletContext();
            //上传的文件都是保存在/upload目录下的子目录当中
            String fileSaveRootPath = sc.getRealPath(path);
            //得到要下载的文件
            File file = new File(fileSaveRootPath +  "/" + fileName);
            //如果文件不存在
            if(!file.exists()){
                map.put("error", "您要下载的资源已被删除！");
                request.setAttribute("message", "您要下载的资源已被删除！");
                request.getRequestDispatcher("/message.jsp").forward(request, response);
                System.out.println("您要下载的资源已被删除！");
                return map;
            }
            //处理文件名
            String realname = fileName.substring(fileName.indexOf("__") + 2);
            //设置响应头，控制浏览器下载该文件
            response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(realname, "UTF-8"));
            //读取要下载的文件，保存到文件输入流
            FileInputStream fis = new FileInputStream(fileSaveRootPath + "/" + fileName);
            //创建输出流
            OutputStream out = response.getOutputStream();
            //创建缓冲区
            byte buffer[] = new byte[1024];
            int len = 0;
            //循环将输入流中的内容读取到缓冲区中
            while((len = fis.read(buffer)) > 0){
                //输出缓冲区的内容到浏览器，实现文件下载
                out.write(buffer, 0, len);
            }
            //关闭文件输入流
            fis.close();
            //关闭输出流
            out.close();
            map.put("success", "下载成功");
        }
        catch (Exception e){
            map.put("error", "资源下载失败");
            e.printStackTrace();
        }
        return map;
    }
}

package com.knowledge_network.support.utils;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

/**
 * Created by wshish000 on 17-12-11
 */
public class UploadUtil {

    /**
     * 文件上传成功
     */
    public static final int UPLOAD_SUCCEED = 0;
    /**
     * 文件上传失败：文件类型不支持
     */
    public static final int UPLOAD_TYPE_ERROR = 1;
    /**
     * 文件上传失败：空文件
     */
    public static final int UPLOAD_EMPTY_ERROR = 2;
    /**
     * 上传其他错误
     */
    public static final int UPLOAD_UNKNOWN_ERROR = 3;
    /**
     * 系统允许上传的文件扩展名
     */
    public static final String ALL_FILE_TYPE = "gif,jpg,jpeg,png,bmp,swf,flv,mp3,mp4,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb,doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2";

    /**
     * @param request  网络请求
     * @param folder   指定上传文件的保存目录
     * @param username 保存目录下要保存到上传者的目录下
     * @param list     返回的所上传资源的链接集合
     * @return 上传成功或失败的反馈，若失败返回反馈信息
     */
    public static int upload(HttpServletRequest request, String folder, String username, List<String> list) {
        //后台路径
        String path = request.getSession().getServletContext().getRealPath("/");
        //某个资源对应的后台链接
        String fileUrl = "";
        //创建一个通用的多部分解析器
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        //判断request是否有文件上传，即多部分请求
        if (multipartResolver.isMultipart(request)) {
            //转换成多部分request
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
            //取得request中的文件名
            Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
            while (iterator.hasNext()) {
                //取得上传文件
                MultipartFile file = multipartHttpServletRequest.getFile(iterator.next());
                //经测试，即使所选文件为空，file也不为空
                if (file != null) {
                    //虽然file不为空，但如果所选文件为空，则fileName为空
                    String fileName = file.getOriginalFilename();
                    //如果名称不为空，说明该文件存在，否则说明该文件不存在
                    if (fileName.trim().length() != 0) {
                        //某些浏览器的文件名还会包含路径，此处进行处理，将文件名前的路径去掉
                        if (fileName.indexOf("\\") > 0) {
                            fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);
                        } else {
                            fileName = fileName.substring(fileName.lastIndexOf("/") + 1);
                        }
                        //获取文件名后缀
                        String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
                        //检查后缀，若不符合规定，则抛出异常
                        if (!ALL_FILE_TYPE.contains(fileExt)) {
                            for (int i = 0; i < list.size(); i++) {
                                File deletefile = new File(path + "/" + list.get(i));
                                deletefile.delete();
                            }
                            list.clear();
                            return UPLOAD_TYPE_ERROR;
                        }
                        //唯一标识上传的资源名，防止文件覆盖的现象发生
                        String newFileName = UUID.randomUUID() + "__" + fileName;
                        //定义上传路径
                        String uploadPath = path + "/" + folder + "/" + username;
                        File localFile = new File(uploadPath, newFileName);
                        //判断文件是否存在
                        if (!localFile.exists()) {
                            localFile.mkdirs();
                        }
                        //进行文件拷贝
                        try {
                            file.transferTo(localFile);
                            fileUrl = username + "/" + newFileName;
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        list.add(fileUrl);
                    }
                }

            }
            if (list.size() == 0) {
                return UPLOAD_EMPTY_ERROR;
            }
        }
        return UPLOAD_SUCCEED;
    }

}

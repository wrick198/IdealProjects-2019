package com.knowledge_network.knowledge_network.controller;

import com.knowledge_network.auth.user.LoginUserHolder;
import com.knowledge_network.knowledge_network.entity.Resource;
import com.knowledge_network.knowledge_network.service.ResourceService;
import com.knowledge_network.knowledge_network.vo.ResourceInfoVO;
import com.knowledge_network.knowledge_network.vo.web.DownloadVO;
import com.knowledge_network.support.common.ResponseErrorEnum;
import com.knowledge_network.support.common.ResponseResult;
import com.knowledge_network.support.utils.Asserts;
import com.knowledge_network.support.utils.IOUtils;
import com.knowledge_network.support.utils.MapUtils;
import com.knowledge_network.support.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wshish000 on 17-12-8
 */
@RestController
@RequestMapping("/resource")
public class ResourceController {

    public static final String UPLOAD_FOLDER = "upload";
    /**
     * 系统允许上传的文件扩展名
     */
    public static final String ALL_FILE_TYPE = "gif,jpg,jpeg,png,bmp,swf,flv,mp3,mp4,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb,doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2,pdf";

    @Autowired
    private ResourceService resourceService;

    /**
     * Created by pentonbin on 18-04-18
     * <p>
     * 用户上传资源文件
     * 1. 随机生成文件名
     * 2. 保存位置：/target/knowledge-network/upload/用户名
     *
     * @param request
     * @param uploadFile
     * @return
     */
    @RequestMapping(value = "/upload", headers = "Content-Type=multipart/*", method = RequestMethod.POST)
    public ResponseResult<String> upload(HttpServletRequest request, @RequestParam("file") MultipartFile uploadFile) {
        if (!uploadFile.isEmpty()) {
            // 原文件名
            String originalFilename = uploadFile.getOriginalFilename();
            String fileExt = originalFilename.substring(originalFilename.lastIndexOf(".") + 1).toLowerCase();
            if (!ALL_FILE_TYPE.contains(fileExt)) {
                return ResponseResult.newErrorInstance(ResponseErrorEnum.RESOURCE_FILE_TYPE_ERROR, null);
            }

            String username = LoginUserHolder.getInstance().getCurrentLoginUser().getUsername();
            // 项目目录
            String projectFolder = request.getSession().getServletContext().getRealPath("/");
            // 最终文件名
            String destFileName = null;
            try {
                destFileName = StringUtils.getRandomString() + "." + fileExt;
            } catch (Exception e) {
                destFileName = originalFilename;
            }
            // 文件路径
            String destPath = UPLOAD_FOLDER + File.separator // 上传目录
                    + username + File.separator // 用户目录
                    + destFileName; // 文件名
            // 最终文件
            File destFile = new File(projectFolder + File.separator + destPath);
            try {
                if (!destFile.exists()) {
                    destFile.mkdirs();
                    destFile.createNewFile();
                }
                uploadFile.transferTo(destFile);
            } catch (IOException e) {
                return ResponseResult.newErrorInstance(ResponseErrorEnum.RESOURCE_FILE_UNKNOWN_ERROR, null);
            }
            return ResponseResult.newSucceedInstance("File upload succeed", destPath);
        }
        return ResponseResult.newErrorInstance(ResponseErrorEnum.RESOURCE_FILE_EMPTY, null);
    }

    /**
     * Created by pentonbin on 18-04-18
     * <p>
     * 用户上传资源
     *
     * @param resourceInfo
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseResult<Integer> save(@RequestBody ResourceInfoVO resourceInfo) {
        Asserts.hasText(resourceInfo.getName(), ResponseErrorEnum.RESOURCE_FILE_NAME_EMPTY);
        Asserts.notNull(resourceInfo.getGrade(), ResponseErrorEnum.RESOURCE_FILE_GRADE_EMPTY);
        Asserts.notNull(resourceInfo.getSubject(), ResponseErrorEnum.RESOURCE_FILE_SUBJECT_EMPTY);
        Asserts.hasText(resourceInfo.getUrl(), ResponseErrorEnum.RESOURCE_FILE_URL_EMPTY);
        if (!resourceInfo.getUrl().contains(LoginUserHolder.getInstance().getCurrentLoginUser().getUsername())) {
            return ResponseResult.newErrorInstance(ResponseErrorEnum.RESOURCE_ILLEGAL_URL, null);
        }
        Resource resource = resourceService.createResource(resourceInfo);

        return ResponseResult.newSucceedInstance("Resource upload succeed", resource.getId());
    }

    @RequestMapping(value = "/showResource", method = RequestMethod.GET)
    //显示资源信息
    public String showResource(HttpServletRequest request, HttpServletResponse response) {
        ServletContext sc = request.getSession().getServletContext();
        //获取上传文件的目录
        String uploadFilePath = sc.getRealPath("/upload") + "/";
        //存储要下载的文件名
        Map<String, String> fileNameMap = new HashMap<>();
        //递归遍历目录下的所有文件和目录，将文件的文件名存储到map集合中
        listFile(new File(uploadFilePath), fileNameMap);
        //将Map集合发送到showResource页面进行显示
        request.setAttribute("fileNameMap", fileNameMap);
        return "showResource";

    }

    @RequestMapping(value = "/searchResource", method = RequestMethod.POST)
    //搜索资源
    // TODO: 17-12-9 此处只能按描述搜索，后期还要实现其他功能
    public ResponseResult<List<ResourceInfoVO>> searchResource(HttpServletRequest request, ModelMap model) {
        Map<String, String[]> params = request.getParameterMap();
        Map<String, String> conditions = MapUtils.transferMap(params);
        List<Resource> resourceList = resourceService.getResourceByConditions(conditions);
        List<ResourceInfoVO> resourceInfoVOList = new ArrayList<>();
        for (int i = 0; i < resourceList.size(); i++) {
            resourceInfoVOList.add(new ResourceInfoVO(resourceList.get(i)));
        }
        ResponseResult<List<ResourceInfoVO>> response = ResponseResult.newSucceedInstance(null, resourceInfoVOList);
        return response;
//        model.addAttribute("resourceList", resourceList);
//        return "searchResource";

    }

    /**
     * Created by pentonbin on 18-04-18
     * <p>
     * 根据资源对应URL下载资源
     *
     * @param downloadVO
     * @return
     */
    @RequestMapping(value = "/download", method = RequestMethod.POST)
    public ResponseResult<byte[]> download(HttpServletRequest request, @RequestBody DownloadVO downloadVO) {
        Asserts.hasText(downloadVO.getUrl(), ResponseErrorEnum.RESOURCE_DOWNLOAD_URL_EMPTY);

        String url = downloadVO.getUrl();

        // TODO 根据是否付费、用户是否购买判断
        String projectFolder = request.getSession().getServletContext().getRealPath("/");
        String fileName = projectFolder + File.separator + url;
        File targetFile = new File(fileName);
        if (!targetFile.exists()) {
            return ResponseResult.newErrorInstance(ResponseErrorEnum.RESOURCE_NOT_EXISTS, null);
        }
        try {
            return ResponseResult.newSucceedInstance(targetFile.getName(), IOUtils.readDataFromFile(targetFile));
        } catch (IOException e) {
            return ResponseResult.newErrorInstance(ResponseErrorEnum.INTERNAL_SERVER_ERROR, null);
        }
    }

    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public ResponseResult<Object> modify(HttpServletRequest request, ModelMap model) throws IOException {
        // TODO: 17-12-14 此处需要限制params哪些项可以为空，哪些不能为空
        Map<String, String[]> params = request.getParameterMap();
        //定义上传后的反馈值
        Map<String, String> map = new HashMap<>();
        //定义上传资源列表返回的资源路径
        List<String> list = new ArrayList<>();
        //定义反馈结果
        String message = "";
        //定义服务器的保存目录
        // TODO: 17-12-8 日后会放在WEB-INF目录下，不允许外界直接访问，保证上传文件的安全
        String folder = "upload";
        //定义上传者的用户名
        String username = params.get("username")[0];
        //定义想修改资源的id
        String id = params.get("id")[0];
        //获取想要修改的资源
        Resource resource = resourceService.getResourceById(Integer.parseInt(id));
        //获取此资源的原有链接
        String path = resource.getUrl();

//        map = UploadUtil.upload(request, folder, username, list);
        if (map.get("success") != null) {
            params.put("url", new String[]{list.get(0)});
            resourceService.modifyResource(resource, params);
            //model.addAttribute("message", map.get("success"));
        } else if ((message = map.get("error")) == "您没有上传文件") {
            resourceService.modifyResource(resource, params);
            //model.addAttribute("message", message);
        } else {
            message = map.get("error");
            //model.addAttribute("message", message);
            //return "modify";
            ResponseResult<Object> response = ResponseResult.newErrorInstance(ResponseErrorEnum.RESOURCE_SUFFIX_ERROR, null);
            return response;
        }

        //message = map.get("success");
        //model.addAttribute("fileList", list);

        //return "modify";
        ResponseResult<Object> response = ResponseResult.newSucceedInstance("修改成功", null);
        return response;
    }

    private void listFile(File file, Map<String, String> fileNameMap) {
        if (file == null) {
            return;
        }
        //如果file代表的不是一个文件，而是一个目录
        if (!file.isFile()) {
            //列出该目录下的所有文件和目录
            File files[] = file.listFiles();
            if (files == null) {
                return;
            }
            //遍历files[]数组
            for (File f : files) {
                //递归
                listFile(f, fileNameMap);
            }
        } else {
            String realName = file.getName().substring(file.getName().indexOf("__") + 2);
            //file.getName()得到的是文件的原始名字，这个名字是唯一的，因此可以作为key，realName是处理过后的名称，有可能会重复
            fileNameMap.put(file.getPath().substring(file.getPath().lastIndexOf("upload") + 7), realName);
        }
    }
}

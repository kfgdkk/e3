package com.e3.web.item.controller;

import com.e3.util.common.FastDFSClient;
import com.e3.util.common.JsonUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@Controller
public class PictureController {
    private String IMAGE_SERVER_URL="http://192.168.40.129/";
    @RequestMapping("/pic/upload")
    @ResponseBody
    public String  fileUpload(MultipartFile uploadFile){
        try {
            String originalFilename = uploadFile.getOriginalFilename();
            String extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
            FastDFSClient fastDFSClient = new
                    FastDFSClient("classpath:client.conf");
            String s = fastDFSClient.uploadFile(uploadFile.getBytes(), extName);
            String url = IMAGE_SERVER_URL+s;
            Map map = new HashMap();
            map.put("error",0);
            map.put("url",url);
            String json = JsonUtils.objectToJson(map);
            return json;
        }catch (Exception e){
            e.printStackTrace();
            Map map = new HashMap();
            map.put("error",1);
            map.put("message","图片上传错误");
            String json = JsonUtils.objectToJson(map);
            return json;
        }
    }
}

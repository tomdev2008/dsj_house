package com.dsj.data.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsj.data.service.UploadService;
import com.dsj.data.web.utils.upload.ImageFileUploadUtil;

/**
 * Created by liu on 2016/11/8.
 */
@Service
public class UploadServiceImpl implements UploadService {


    @Autowired
    private ImageFileUploadUtil fileUtil;

    /**
     * 上传文件
     * @param bytes
     * @return 返回阿里oss访问key，  返回null为失败，
     */
    @Override
    public String uploadImg(byte[] bytes,String extName){

        return fileUtil.saveImg(bytes,extName);
    }


    /**
     * 获取图片的浏览地址
     * @param key
     * @return
     */
    @Override
    public String getImgUrl(String key) {
        return fileUtil.getImgUrl(key);
    }


}

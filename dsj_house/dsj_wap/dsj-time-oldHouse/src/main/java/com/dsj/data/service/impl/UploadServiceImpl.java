package com.dsj.data.service.impl;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsj.common.utils.spring.ConfigUtils;
import com.dsj.data.maitian.biz.impl.MaitianBizImpl;
import com.dsj.data.service.UploadService;
import com.dsj.data.utils.upload.ImageFileUploadUtil;

/**
 * Created by liu on 2016/11/8.
 */
@Service
public class UploadServiceImpl implements UploadService {
	private final static Logger LOGGER = LoggerFactory.getLogger(UploadServiceImpl.class);
    @Autowired
    private ImageFileUploadUtil fileUtil;

    /**
     * 上传文件
     * @param bytes
     * @return 返回阿里oss访问key，  返回null为失败，
     */
    @Override
    public String uploadImg(File file,String extName){

        return fileUtil.saveImg(file,extName);
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

	
	/**
	 * 上传图片
	 * @param path 绝对路径
	 * @return 返回图片url
	 */
    @Override
	public String uploadImg(String path) {
		File fs = new File(path);
		String extName = path.substring(path.lastIndexOf("."));
		String key = null;
		try {
			// 阿里云
			key = uploadImg(fs, extName);
			if (StringUtils.isNotBlank(key)) {
				key = ConfigUtils.instance.getOssAccessUrl() + key;
			}
			LOGGER.info("上传图片完成");
		} catch (Exception e) {
			LOGGER.error("上传出错：", e.getMessage(), e);
		}
		return key;
	}
    

	/**
	 * 下载图片
	 * @param path	保存路径
	 * @param destUrl 目标url
	 */
    @Override
	public String downloadImg(String path ,String destUrl) {
		FileOutputStream fos = null;
		BufferedInputStream bis = null;
		HttpURLConnection httpUrl = null;
		String fileName = destUrl.substring(destUrl.lastIndexOf("/")+1);
		if (!new File(path).exists()) {
			new File(path).mkdirs();
		}
		byte[] buf = new byte[1024];
		int size = 0;
		try {
			URL url = new URL(destUrl);
			httpUrl = (HttpURLConnection) url.openConnection();
			httpUrl.connect();
			bis = new BufferedInputStream(httpUrl.getInputStream());
			fos = new FileOutputStream(path+File.separator+fileName);
			while ((size = bis.read(buf)) != -1) {
				fos.write(buf, 0, size);
			}
			fos.flush();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (ClassCastException e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				fos.close();
				bis.close();
				httpUrl.disconnect();
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			} catch (NullPointerException e) {
				e.printStackTrace();
				return null;
			}
		}
		return fileName;
	}
	
}

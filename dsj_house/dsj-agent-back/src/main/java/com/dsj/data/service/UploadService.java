package com.dsj.data.service;

import java.io.InputStream;

public interface UploadService {

	/**
	 * 上传图片，返回上传图片KEY
	 * @param bytes
	 * @return
	 */
	public String uploadImg(byte[] bytes, String extName);

    /**
     * 根据KEY返回图片URL地址。
     * @param key
     * @return
     */
	public String getImgUrl(String key);

	public String saveImg(InputStream is,String imgType);

	String uploadHeadProtrait(String base64);

	
}
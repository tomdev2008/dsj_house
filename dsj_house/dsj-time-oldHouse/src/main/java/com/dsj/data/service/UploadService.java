package com.dsj.data.service;

import java.io.File;

public interface UploadService {

	/**
	 * 上传图片，返回上传图片KEY
	 * @param bytes
	 * @return
	 */
	public String uploadImg(File file, String extName);

    /**
     * 根据KEY返回图片URL地址。
     * @param key
     * @return
     */
	public String getImgUrl(String key);

	/**
	 * 下载
	 * @param path
	 * @return
	 */
	String uploadImg(String path);
	
	/**
	 * 上传
	 * @param path
	 * @param destUrl
	 * @return
	 */
	String downloadImg(String path, String destUrl);
	
}
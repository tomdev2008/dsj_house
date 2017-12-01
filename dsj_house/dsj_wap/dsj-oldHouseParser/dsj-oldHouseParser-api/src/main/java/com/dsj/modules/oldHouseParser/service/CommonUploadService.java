package com.dsj.modules.oldHouseParser.service;

import java.io.File;

public interface CommonUploadService {

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
	String downloadImg( String destUrl);

	boolean deleteImg(String fileName);

	
	String downloadOrUpImg(String path);
	
}
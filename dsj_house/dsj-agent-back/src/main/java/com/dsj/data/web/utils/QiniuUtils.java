package com.dsj.data.web.utils;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dsj.common.utils.spring.ConfigUtils;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;

public class QiniuUtils {
	
	public static String uploadToken;

	public static Auth auth;

	public static String bucket =ConfigUtils.instance.getBucket();

	public static UploadManager uploadManager 
		= new UploadManager(new Configuration(Zone.zone1()));

	public static void execute() {
		String ak =ConfigUtils.instance.getAk();
		String sk = ConfigUtils.instance.getSk();
		auth = Auth.create(ak, sk);
		
		QiniuEntity qiniu = QiniuEntity.getInstance();
		if (StringUtils.isEmpty(uploadToken)) {
			uploadToken = auth.uploadToken(bucket);
		}
		qiniu.setUptoken(uploadToken);
		qiniu.setDomain(ConfigUtils.instance.getQiniuDomain());
	}

	/***
	 * 获取文件上传的token
	 * 
	 * @return
	 */
	public static String getToken() {
		if (null == bucket || null == auth) {
			bucket = ConfigUtils.instance.getBucket();
			String ak =ConfigUtils.instance.getAk();
			String sk = ConfigUtils.instance.getSk();
			auth = Auth.create(ak, sk);
			auth = Auth.create(ak, sk);
		}
		String token = auth.uploadToken(bucket);
		return token;
	}

	/**
	 * 
	 * @param file
	 * @param key
	 * @return 文件路径 http://<domain>/key
	 */
	public static String upload(File file, String key) {
		String fileUrl = null;
		try {
			final String expectKey = "hanagm";
			StringMap params = new StringMap().put("x:foo", "foo_val");
			if (null == bucket || null == auth) {
				bucket = ConfigUtils.instance.getBucket();
				String ak =ConfigUtils.instance.getAk();
				String sk = ConfigUtils.instance.getSk();
				auth = Auth.create(ak, sk);
			}
			String token = auth.uploadToken(bucket);
			Response res = uploadManager.put(file, key, token, params, null,
					true);
			// System.out.println(res.toString());
			fileUrl = "http://" + ConfigUtils.instance.getQiniuDomain()
					+ "/" + key;
		} catch (QiniuException e) {
			e.printStackTrace();
			Response r = e.response;
			try {
				System.out.println(r.bodyString());
			} catch (QiniuException e1) {
				e1.printStackTrace();
			}
		}

		return fileUrl;
	}

	public static String upload(byte[] fileByte, String key) {
		String fileUrl = null;
		try {
			final String expectKey = "hanagm";
			StringMap params = new StringMap().put("x:foo", "foo_val");
			if (null == bucket || null == auth) {
				bucket = ConfigUtils.instance.getBucket();
				String ak =ConfigUtils.instance.getAk();
				String sk = ConfigUtils.instance.getSk();
				auth = Auth.create(ak, sk);
			}
			String token = auth.uploadToken(bucket);
			Response res = uploadManager.put(fileByte, key, token, params,
					null, true);
			fileUrl = "http://" + ConfigUtils.instance.getQiniuDomain()
					+ "/" + key;
		} catch (QiniuException e) {
			e.printStackTrace();
			Response r = e.response;
			try {
				System.out.println(r.bodyString());
			} catch (QiniuException e1) {
				e1.printStackTrace();
			}
		}

		return fileUrl;
	}
	
	public static String upload(File file) {
		String fileUrl = null;
		try {
			String token = QiniuEntity.getInstance().getUptoken();
			if (StringUtils.isEmpty(token)) {
				execute();
				token = QiniuEntity.getInstance().getUptoken();
			}
			
			String key = (new QETag()).calcETag(file);
			if (StringUtils.isEmpty(key)) {
				throw new Exception("key is null");
			}
			String fileName = fileReName(file.getName(), key);
			Response res = uploadManager.put(file, fileName, token);
			fileUrl = "http://" + QiniuEntity.getInstance().getDomain() + "/" 
					+ fileName;
		} catch (QiniuException e) {
			e.printStackTrace();
			Response r = e.response;
			try {
				System.out.println(r.bodyString());
			} catch (QiniuException e1) {
				e1.printStackTrace();
			}
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return fileUrl;
	}
	
	/***
	 * 重命名
	 * @param fileName
	 * @param key
	 * @return
	 */
	public static String fileReName(String fileName, String key){
		String extend = getExtend(fileName); // 获取文件扩展名
		String  myfilename = key + "." + extend; //自定义文件名称
		return myfilename;
	}
	
	/**
	 * 获取文件扩展名
	 * 
	 * @param filename
	 * @return
	 */
	public static String getExtend(String filename) {
		return getExtend(filename, "");
	}

	/**
	 * 获取文件扩展名
	 * 
	 * @param filename
	 * @return
	 */
	public static String getExtend(String filename, String defExt) {
		if ((filename != null) && (filename.length() > 0)) {
			int i = filename.lastIndexOf('.');
			if ((i > 0) && (i < (filename.length() - 1))) {
				return (filename.substring(i+1)).toLowerCase();
			}
		}
		return defExt.toLowerCase();
	}

}

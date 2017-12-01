package com.baidu.ueditor.upload;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.aliyun.oss.OSSClient;
import com.baidu.ueditor.define.AppInfo;
import com.baidu.ueditor.define.BaseState;
import com.baidu.ueditor.define.State;
import com.dsj.common.utils.spring.ConfigUtils;
import com.dsj.data.web.utils.upload.FileTypeUtil;
import com.dsj.data.web.utils.upload.OSSUnit;
import com.dsj.data.web.utils.upload.Utils;

public class BinaryUploader {

	public static final State save(HttpServletRequest request,
			Map<String, Object> conf) {
		// 上传的文件数据
		InputStream inputStream = null;
		String fileRoot="upload";// 上传文件路径根路径
		String imagePathFileName;// 上传文件名称
		String imageInfoPath="ueditor";//获取配置文件systemConfig.properties中文件保存详细划分路径
		// 原始文件名
		String originalName = "";
		
		ServletFileUpload upload = new ServletFileUpload(
				new DiskFileItemFactory());
		String syspath="";
		syspath=System.getProperty("catalina.base");
		
		OSSClient client = OSSUnit.getOSSClient(ConfigUtils.instance.getOssEndPoint(),ConfigUtils.instance.getAccessId(),ConfigUtils.instance.getAccessKey());  
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (!isMultipart) {
			return new BaseState(false, AppInfo.NOT_MULTIPART_CONTENT);
		}
		FileItemStream fileStream = null;
		try {
			FileItemIterator iterator = upload.getItemIterator(request);

			while (iterator.hasNext()) {
				fileStream = iterator.next();
				// 只保留一个
				if (inputStream == null) {
					inputStream = fileStream.openStream();
					originalName = fileStream.getName();
				}

				if (!fileStream.isFormField())
					break;
				fileStream = null;
			}

			if (fileStream == null) {
				return new BaseState(false, AppInfo.NOTFOUND_UPLOAD_DATA);
			}
		} catch (Exception e) {
			return new BaseState(false, AppInfo.IO_ERROR);
		}
		
		try {
			String fileName = fileStream.getName();
			String newName = fileName.substring(fileName.lastIndexOf("."));
			String randomCode=Utils.getRandomCode();
			//原始图片文件编译后的新名称
			newName = randomCode + newName;
			//定义新文件保存实际路径+配置文件systemConfig.properties中定义的文件生成目录规则：年月日
			String newImagePath = fileRoot + "/"+ imageInfoPath + "/"+ FileTypeUtil.getSerial(new Date(),"yyyyMMdd") + "/" ;
	
			imagePathFileName = newImagePath + newName;
			
		//	File file = new File("d:/58881479260277721.png");
			
			//System.out.println(file);
			
			File savefile = new File(syspath+"/temp/"+newImagePath);
	
			if (!savefile.exists()) {
				savefile.mkdirs();
			}
		//	System.out.println(applicationBase.getPropertie("ACCESS_KEY_SECRET"));
			
		//	File file = new File("D:\\tmp\\"+imagePathFileName);
		//	System.out.println(file);
			FileOutputStream fos = new FileOutputStream(syspath+"/temp/"+imagePathFileName);
			BufferedInputStream bis = new BufferedInputStream(inputStream);
			byte[] buff = new byte[128];
			int count = -1;
			while ((count = bis.read(buff)) != -1) {
				fos.write(buff, 0, count);
			}
			bis.close();
			fos.close();
			File uploadedFile = new File(syspath+"/temp/"+imagePathFileName);
			OSSUnit.uploadObject2OSS(client,uploadedFile,ConfigUtils.instance.getOssBucket(), imagePathFileName);
		
			State storageState = new BaseState(true, AppInfo.SUCCESS);
			if (storageState.isSuccess()) {
				storageState.putInfo("url", ConfigUtils.instance.getOssAccessUrl()+ imagePathFileName);
				storageState.putInfo("type", newName);
				storageState.putInfo("original", originalName);
				storageState.putInfo("title", "大搜家");
			}
			return storageState;
		} catch (Exception e) {
			return new BaseState(false, AppInfo.PARSE_REQUEST_ERROR);
		}
	}
	/*	
	private static boolean validType(String type, String[] allowTypes) {
		List<String> list = Arrays.asList(allowTypes);

		return list.contains(type);
	}
	
	public static final State save(HttpServletRequest request,
			Map<String, Object> conf) {
		FileItemStream fileStream = null;
		boolean isAjaxUpload = request.getHeader( "X_Requested_With" ) != null;

		if (!ServletFileUpload.isMultipartContent(request)) {
			return new BaseState(false, AppInfo.NOT_MULTIPART_CONTENT);
		}

		ServletFileUpload upload = new ServletFileUpload(
				new DiskFileItemFactory());

        if ( isAjaxUpload ) {
            upload.setHeaderEncoding( "UTF-8" );
        }

		try {
			FileItemIterator iterator = upload.getItemIterator(request);

			while (iterator.hasNext()) {
				fileStream = iterator.next();

				if (!fileStream.isFormField())
					break;
				fileStream = null;
			}

			if (fileStream == null) {
				return new BaseState(false, AppInfo.NOTFOUND_UPLOAD_DATA);
			}

			String savePath = (String) conf.get("savePath");
			String originFileName = fileStream.getName();
			String suffix = FileType.getSuffixByFilename(originFileName);

			originFileName = originFileName.substring(0,
					originFileName.length() - suffix.length());
			savePath = savePath + suffix;

			long maxSize = ((Long) conf.get("maxSize")).longValue();

			if (!validType(suffix, (String[]) conf.get("allowFiles"))) {
				return new BaseState(false, AppInfo.NOT_ALLOW_FILE_TYPE);
			}

			savePath = PathFormat.parse(savePath, originFileName);

			String physicalPath = (String) conf.get("rootPath") + savePath;

			InputStream is = fileStream.openStream();
			State storageState = StorageManager.saveFileByInputStream(is,
					physicalPath, maxSize);
			is.close();

			if (storageState.isSuccess()) {
				storageState.putInfo("url", PathFormat.format(savePath));
				storageState.putInfo("type", suffix);
				storageState.putInfo("original", originFileName + suffix);
			}

			return storageState;
		} catch (FileUploadException e) {
			return new BaseState(false, AppInfo.PARSE_REQUEST_ERROR);
		} catch (IOException e) {
		}
		return new BaseState(false, AppInfo.IO_ERROR);
	}*/
}

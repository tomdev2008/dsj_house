package  com.dsj.data.web.utils;


import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import com.aliyun.oss.OSSClient;
import com.dsj.common.utils.spring.ConfigUtils;
import com.dsj.data.web.utils.upload.FileTypeUtil;

/**
 * UEditor文件上传辅助类
 * 
 */
//@Component
public class Uploader {
	
	private  final Logger LOGGER = LoggerFactory.getLogger(Uploader.class);

	
	// 文件大小常量, 单位kb
	private static final int MAX_SIZE = 500 * 1024;
	// 输出文件地址
	private String url = "";
	// 上传文件名
	private String fileName = "";
	// 状态
	private String state = "";
	// 文件类型
	private String type = "";
	// 原始文件名
	private String originalName = "";
	// 文件大小
	private String size = "";
	
	/**
	 * 
	 */
	private HttpServletRequest request = null;
	private String title = "";
	private String fileRoot="upload";// 上传文件路径根路径
	private String imagePathFileName;// 上传文件名称
	private String imageInfoPath="ueditor";//获取配置文件systemConfig.properties中文件保存详细划分路径

	/*OSS_ENDPOINT=http://vpc100-oss-cn-beijing.aliyuncs.com
		OSS_IMGURL=http://img.slb.12366.com/
		oss.bucket=slb12366
		ACCESS_KEY_ID=MFV0xdXTWzRk0upR
		ACCESS_KEY_SECRET=Bsypnfu3gZoKP0vLFZOi5ZGZymSr2z
		OSS_ENDPOINT_OUT=http://oss-cn-beijing.aliyuncs.com
		image_customerCredit=image/customerCredit*/
//	@Value("${oss.bucket}")
//	private String ossBucket="slb12366";
//	@Value("${OSS_ENDPOINT_OUT}")
//	private String OSS_ENDPOINT_OUT="http://oss-cn-beijing.aliyuncs.com";
//	@Value("${ACCESS_KEY_SECRET}")
//	private String ACCESS_KEY_SECRET="Bsypnfu3gZoKP0vLFZOi5ZGZymSr2z";
//
//	@Value("${ACCESS_KEY_ID}")
//	private String ACCESS_KEY_ID="MFV0xdXTWzRk0upR";
//	
//	private String fileRule="yyyyMMdd";
//	@Value("${OSS_ENDPOINT}")
//	
//	private String OSS_ENDPOINT="http://vpc100-oss-cn-beijing.aliyuncs.com";
	
	//"http://oss-cn-beijing.aliyuncs.com";
	// 保存路径
//	private String savePath = "upload";
	// 文件允许格式
	private String[] allowFiles = { ".rar", ".doc", ".docx", ".zip", ".pdf",
			".txt", ".swf", ".wmv", ".gif", ".png", ".jpg", ".jpeg", ".bmp" };
	// 文件大小限制，单位Byte
	private long maxSize = 0;

	private HashMap<String, String> errorInfo = new HashMap<String, String>();
	private Map<String, String> params = null;
	// 上传的文件数据
	private InputStream inputStream = null;

	public static final String ENCODEING = System.getProperties().getProperty(
			"file.encoding");

	public Uploader(HttpServletRequest request) {
		this.request = request;
		this.params = new HashMap<String, String>();

		this.setMaxSize(Uploader.MAX_SIZE);

		HashMap<String, String> tmp = this.errorInfo;
		tmp.put("SUCCESS", "SUCCESS"); // 默认成功
		// 未包含文件上传域
		tmp.put("NOFILE",
				"未包含文件上传域");
		// 不允许的文件格式
		tmp.put("TYPE",
				"不允许的文件格式");
		// 文件大小超出限制
		tmp.put("SIZE",
				"文件大小超出限制");
		// 请求类型错误
		tmp.put("ENTYPE", "请求类型错误");
		// 上传请求异常
		tmp.put("REQUEST", "上传请求异常");
		// 未找到上传文件
		tmp.put("FILE", "未找到上传文件");
		// IO异常
		tmp.put("IO", "IO异常");
		// 目录创建失败
		tmp.put("DIR", "目录创建失败");
		// 未知错误
		tmp.put("UNKNOWN", "未知错误");

		this.parseParams();

	}

	public void upload() throws Exception {
		String syspath="";
		syspath=System.getProperty("catalina.base");
	//	ApplicationBase applicationBase=new ApplicationBase();
		
		OSSClient client = OSSUnit.getOSSClient(ConfigUtils.instance.getOssEndPoint(),ConfigUtils.instance.getAccessId(),ConfigUtils.instance.getAccessKey());  
		boolean isMultipart = ServletFileUpload.isMultipartContent(this.request);
		// ApplicationBase applicationBase=new ApplicationBase();
		if (!isMultipart) {
			this.state = this.errorInfo.get("NOFILE");
			return;
		}
		if (this.inputStream == null) {
			this.state = this.errorInfo.get("FILE");
			return;
		}
		// 存储title
		this.title = this.getParameter("pictitle");
		try {
//			String savePath = this.getFolder(this.savePath);
			if (!this.checkFileType(this.originalName)) {
				this.state = this.errorInfo.get("TYPE");
				return;
			}
			this.fileName = this.getName(this.originalName);
		
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
			BufferedInputStream bis = new BufferedInputStream(this.inputStream);
			byte[] buff = new byte[128];
			int count = -1;
			while ((count = bis.read(buff)) != -1) {
				fos.write(buff, 0, count);
			}
			bis.close();
			fos.close();
			File uploadedFile = new File(syspath+"/temp/"+imagePathFileName);
			OSSUnit.uploadObject2OSS(client,uploadedFile,ConfigUtils.instance.getOssBucket(), imagePathFileName);
//			this.url = Paths.getStaticAccessTempUrl(this.url);// modify
		
			this.url =ConfigUtils.instance.getOssAccessUrl()+ imagePathFileName;
			this.state = this.errorInfo.get("SUCCESS");
		} catch (Exception e) {
			LOGGER.error("上传出错了：",e);
			this.state = this.errorInfo.get("IO");
		}

	}

	public String getParameter(String name) {
		return this.params.get(name);
	}

	/**
	 * 文件类型判断
	 * 
	 * @param fileName
	 * @return
	 */
	private boolean checkFileType(String fileName) {
		Iterator<String> type = Arrays.asList(this.allowFiles).iterator();
		while (type.hasNext()) {
			String ext = type.next();
			if (fileName.toLowerCase().endsWith(ext)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 获取文件扩展名
	 * 
	 * @return string
	 */
	private String getFileExt(String fileName) {
		return fileName.substring(fileName.lastIndexOf("."));
	}

	private void parseParams() {
		DiskFileItemFactory dff = new DiskFileItemFactory();
		try {
			ServletFileUpload sfu = new ServletFileUpload(dff);
			sfu.setSizeMax(this.maxSize);
			sfu.setHeaderEncoding(Uploader.ENCODEING);
			FileItemIterator fii = sfu.getItemIterator(this.request);
			while (fii.hasNext()) {
				FileItemStream item = fii.next();
				// 普通参数存储
				if (item.isFormField()) {
					this.params.put(item.getFieldName(),
							this.getParameterValue(item.openStream()));
				} else {
					// 只保留一个
					if (this.inputStream == null) {
						this.inputStream = item.openStream();
						this.originalName = item.getName();
						return;
					}
				}
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			this.state = this.errorInfo.get("UNKNOWN");
		}
	}

	/**
	 * 依据原始文件名生成新文件名
	 * 
	 * @return
	 */
	private String getName(String fileName) {
		Random random = new Random();
		return this.fileName = "" + random.nextInt(10000)
				+ System.currentTimeMillis() + this.getFileExt(fileName);
	}

	/**
	 * 从输入流中获取字符串数据
	 * 
	 * @param in
	 *            给定的输入流， 结果字符串将从该流中读取
	 * @return 从流中读取到的字符串
	 */
	private String getParameterValue(InputStream in) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		String result = "";
		String tmpString = null;
		try {
			while ((tmpString = reader.readLine()) != null) {
				result += tmpString;
			}
		} catch (Exception e) {
			// do nothing
		}
		return result;
	}

//	public void setSavePath(String savePath) {
//		this.savePath = savePath;
//	}

	public void setAllowFiles(String[] allowFiles) {
		this.allowFiles = allowFiles;
	}

	public void setMaxSize(long size) {
		this.maxSize = size * 1024;
	}

	public String getSize() {
		return this.size;
	}

	public String getUrl() {
		return this.url;
	}

	public String getFileName() {
		return this.fileName;
	}

	public String getState() {
		return this.state;
	}

	public String getTitle() {
		return this.title;
	}

	public String getType() {
		return this.type;
	}

	public String getOriginalName() {
		return this.originalName;
	}
}
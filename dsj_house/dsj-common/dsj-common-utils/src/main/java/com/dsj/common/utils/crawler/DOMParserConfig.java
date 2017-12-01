package com.dsj.common.utils.crawler;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


import org.apache.xerces.xni.parser.XMLParserConfiguration;
import org.cyberneko.html.HTMLConfiguration;
import org.cyberneko.html.parsers.DOMParser;
import org.dom4j.Document;
import org.dom4j.io.DOMReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;

public class DOMParserConfig {
	private static final Logger logger = LoggerFactory.getLogger(DOMParserConfig.class);
	 public static Document getDocByUrl(String strUrl) throws IOException, SAXException{  
		 	logger.info("解析url:" + strUrl+"开始");
			XMLParserConfiguration config = new HTMLConfiguration();
			config.setFeature("http://cyberneko.org/html/features/augmentations", true);
	        config.setProperty("http://cyberneko.org/html/properties/names/elems", "match");
	        DOMParser parser = new DOMParser();
	        parser.setFeature("http://cyberneko.org/html/features/augmentations", true);
	        parser.setProperty("http://cyberneko.org/html/properties/names/elems", "match");
	        URL url = new URL(strUrl);
	        URLConnection connection = url.openConnection();
	        parser.parse(new InputSource(connection.getInputStream()));
	        org.w3c.dom.Document document = parser.getDocument();
	        DOMReader reader = new DOMReader();
	        Document doc = reader.read(document);
	        return doc;
	 }
	 
	public static void main(String[] args) {
		String url="https://bj.fang.anjuke.com/loupan/fangyuan-248715.html?from=loupan_tab";
		System.out.println(url.split("-")[1].split(".html")[0]);
		
	}
}

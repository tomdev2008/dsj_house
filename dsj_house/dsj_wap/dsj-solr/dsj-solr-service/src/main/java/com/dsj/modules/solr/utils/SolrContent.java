package com.dsj.modules.solr.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.CloudSolrClient;

import com.mysql.fabric.xmlrpc.base.Array;

public class SolrContent {
	private   String solrUrl;
	public static   String solrCroeHome;  
	
	private  List<String> zkHosts=  new ArrayList<String>();  
	
	public  SolrClient getSolrClient(){  
		
	     
	         CloudSolrClient cloudSolrClient = new CloudSolrClient.Builder().withZkHost(zkHosts).build();  
		 cloudSolrClient.setDefaultCollection("ershoufang");  
		// HttpSolrClient client = new HttpSolrClient.Builder(solrUrl+"/"+solrCroeHome).build();
		 cloudSolrClient.setZkClientTimeout(1000);  
	     cloudSolrClient.setZkConnectTimeout(1000);  
	     return cloudSolrClient;
	 }
	 
	
	 public  SolrClient getNewHouseSolrClient(){  
		 
	        CloudSolrClient cloudSolrClient = new CloudSolrClient.Builder().withZkHost(zkHosts).build();  
		 cloudSolrClient.setDefaultCollection("new_house");  
		 cloudSolrClient.setZkClientTimeout(1000);  
	     cloudSolrClient.setZkConnectTimeout(1000); 
	     return cloudSolrClient;
	 }
	 
	 public  SolrClient getCommonClient(){  
		 
	        CloudSolrClient cloudSolrClient = new CloudSolrClient.Builder().withZkHost(zkHosts).build();  
		 cloudSolrClient.setDefaultCollection("new_solr");  
		// HttpSolrClient client = new HttpSolrClient.Builder(solrUrl+"/"+solrCroeHome).build();
		 cloudSolrClient.setZkClientTimeout(1000);  
	     cloudSolrClient.setZkConnectTimeout(1000);  
	     return cloudSolrClient;
	 }
	 
	 public  SolrClient getNewHouseCommonClient(){  
		
	        CloudSolrClient cloudSolrClient = new CloudSolrClient.Builder().withZkHost(zkHosts).build();  
		 cloudSolrClient.setDefaultCollection("new_house_search_solr");  
		 cloudSolrClient.setZkClientTimeout(1000);  
	     cloudSolrClient.setZkConnectTimeout(1000);  
	     return cloudSolrClient;
	 }
	 
	 public  SolrClient getSolrRentClient(){  
		
	        CloudSolrClient cloudSolrClient = new CloudSolrClient.Builder().withZkHost(zkHosts).build();  
		 cloudSolrClient.setDefaultCollection("rent_house");  
		// HttpSolrClient client = new HttpSolrClient.Builder(solrUrl+"/"+solrCroeHome).build();
		 cloudSolrClient.setZkClientTimeout(1000);  
	     cloudSolrClient.setZkConnectTimeout(1000);  
	     return cloudSolrClient;
	 }



	public  void setSolrUrl(String solrUrl) {
		this.solrUrl = solrUrl;
		if(StringUtils.isNotBlank(solrUrl)){
			String[] urls=solrUrl.split(",");
			zkHosts=Arrays.asList(urls);
		}
	}

	public  String getSolrCroeHome() {
		return solrCroeHome;
	}

	public  void setSolrCroeHome(String solrCroeHome) {
		this.solrCroeHome = solrCroeHome;
	}  
	 
	 
}

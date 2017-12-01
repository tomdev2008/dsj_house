package dubbo.test;

import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.client.solrj.request.QueryRequest;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.junit.Test;

import com.dsj.modules.solr.utils.SolrContent;

public class SolrTest   {
	
	  @Test
	  public void testDistributedJoin() throws Exception{
	 //   log.info("Testing distributed join across cluster!");
	    final String fromQ = "*:*";
	  
	    CloudSolrClient client = new CloudSolrClient("211.159.180.106:2181");
	    
	  /*  indexDoc("", 1001, "a", null, "b");
	    //Using Integer.MAX ensures that document is on shard2
	    indexDoc(shardedToColl, Integer.MAX_VALUE,  "a", null, "b");
	    
	    //Using Integer.MAX ensures that document is on shard2
	    indexDoc(shardedFromColl, Integer.MAX_VALUE,"a", null, "b");
	    indexDoc(shardedFromColl, 2001, "a", null, "b");*/
	    
	    final String joinQ = "{!join from=join_s fromIndex=ershoufang to=join_s}" + fromQ;
	    // so the commits fire
	    Thread.sleep(2000); 
	    
	    //Join query with distrib=true so the query goes on all shards
	    QueryRequest qr = new QueryRequest();
	    
	    //QueryResponse rsp = new QueryResponse(cloudClient.request(qr), cloudClient);
	    final QueryResponse  rsp = new QueryResponse(client.request(qr), client);
	    
	    SolrDocumentList hits = rsp.getResults();
	    
	 //   assertTrue("Expected 2 doc from 2 different shards, got "+hits, hits.getNumFound() == 2);
	  }

}

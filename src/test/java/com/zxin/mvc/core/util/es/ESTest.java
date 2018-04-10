package com.zxin.mvc.core.util.es;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.cluster.node.DiscoveryNode;
import org.elasticsearch.common.collect.ImmutableList;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Before;
import org.junit.Test;

public class ESTest {

	TransportClient transportClient;  
    //索引库名  
    String index = "event20171012";  
    //类型名称  
    String type = "event"; 
	
	@Before  
    public void before()  
    {  
        /** 
         * 1:通过 setting对象来指定集群配置信息 
         */  
        Settings setting = ImmutableSettings.settingsBuilder()  
            .put("cluster.name", "cupid-es")//指定集群名称  
            .put("client.transport.sniff", true)//启动嗅探功能  
            .build();  
          
        /** 
         * 2：创建客户端 
         * 通过setting来创建，若不指定则默认链接的集群名为elasticsearch 
         * 链接使用tcp协议即9300 
         */  
        transportClient = new TransportClient(setting);                          
        TransportAddress transportAddress = new InetSocketTransportAddress("192.168.12.227", 8300);  
        transportClient.addTransportAddresses(transportAddress);  
          
        /** 
         * 3：查看集群信息 
         * 注意我的集群结构是： 
         *   131的elasticsearch.yml中指定为主节点不能存储数据， 
         *   128的elasticsearch.yml中指定不为主节点只能存储数据。 
         * 所有控制台只打印了192.168.79.128,只能获取数据节点 
         *  
         */  
        ImmutableList<DiscoveryNode> connectedNodes = transportClient.connectedNodes();  
        for(DiscoveryNode node : connectedNodes)  
        {  
            System.out.println(node.getHostAddress());  
        }  
          
    }  
	
	
	 @Test  
	    public void testSearch()  
	    {  
	        SearchResponse searchResponse = transportClient.prepareSearch("event20171012","event20171011","event20171013")  
	                .setTypes(type)  
	                .setQuery(QueryBuilders.matchAllQuery()) //查询所有  
	                //.setQuery(QueryBuilders.matchQuery("name", "tom").operator(Operator.AND)) //根据tom分词查询name,默认or  
	                //.setQuery(QueryBuilders.multiMatchQuery("tom", "name", "age")) //指定查询的字段  
	                //.setQuery(QueryBuilders.queryString("name:to* AND age:[0 TO 19]")) //根据条件查询,支持通配符大于等于0小于等于19  
	                //.setQuery(QueryBuilders.termQuery("name", "tom"))//查询时不分词  
	                .setSearchType(SearchType.QUERY_THEN_FETCH)  
	                .setFrom(50).setSize(10)//分页  
	                .addSort("lid", SortOrder.DESC)//排序  
	                .get();  
	          
	        SearchHits hits = searchResponse.getHits();  
	        long total = hits.getTotalHits();  
	        System.out.println(total);  
	        SearchHit[] searchHits = hits.hits();  
	        for(SearchHit s : searchHits)  
	        {  
	            System.out.println(s.getSourceAsString());  
	        }  
	    }
	
}

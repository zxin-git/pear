package com.zxin.hadoop.hbase;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HConnection;
import org.apache.hadoop.hbase.client.HConnectionManager;
import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HbaseTest {

	private static Logger logger = LoggerFactory.getLogger(HbaseTest.class);
	
	public static void main(String[] args) {
		try {
			PropertiesConfiguration config = new PropertiesConfiguration("test.properties");  
	//      //对于一般属性直接获取就行  
		    String ip=config.getString("clientPort");     
		    int port=config.getInt("port"); 
			Configuration conf = HBaseConfiguration.create();
//			conf.set("hbase.zookeeper.property.clientPort", config.getString("clientPort"));
//			conf.set("hbase.client.scanner.timeout.period", config.getString("period"));
//			conf.set("hbase.client.retries.number", config.getString("retriesNumber"));
	
			conf.set("hbase.zookeeper.quorum", config.getString("192.168.1.6:2181"));
		
			HConnection connection = HConnectionManager.createConnection(conf);
			HTableInterface table = connection.getTable("emp");
			Scan scan = new Scan();
			scan.setStartRow(Bytes.toBytes(1));
			scan.setStopRow(Bytes.toBytes(2));
			scan.setCacheBlocks(false);
			ResultScanner rs = table.getScanner(scan);
			
			Map<String,String> map = new HashMap<String,String>();
			for (Result result : rs) {
				for (Cell cell : result.rawCells()) {
										
					String key = new String(CellUtil.cloneRow(cell));
					String cloneValue = new String(CellUtil.cloneValue(cell));
					String col = new String(CellUtil.cloneQualifier(cell));
					String family = new String (CellUtil.cloneFamily(cell));
					StringBuilder sb = new StringBuilder().append(key).append(col).append(family);

					map.put(sb.toString(), cloneValue);
				}
			}
			logger.info("结果集大小： {}",map.size());
		} catch (Exception e) {
			logger.error("hbase connect error :", e);
		}
		
		
	}
}


package com.zxin.hadoop.hbase;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HConnection;
import org.apache.hadoop.hbase.client.HConnectionManager;
import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;

public class HbaseTest2 {
    public static Configuration configuration;
    public static HConnection connection;

    public static void main(String[] args) throws IOException {
        //createTable("t2", new String[] { "cf1", "cf2" });
//        listTables();
    	init();
        /*
         * insterRow("t2", "rw1", "cf1", "q1", "val1"); getData("t2", "rw1",
         * "cf1", "q1"); scanData("t2", "rw1", "rw2");
         * deleRow("t2","rw1","cf1","q1"); deleteTable("t2");
         */
    }

    // 初始化链接
    public static void init() {
        configuration = HBaseConfiguration.create();
        /*
         * configuration.set("hbase.zookeeper.quorum",
         * "10.10.3.181,10.10.3.182,10.10.3.183");
         * configuration.set("hbase.zookeeper.property.clientPort","2181");
         * configuration.set("zookeeper.znode.parent","/hbase");
         */
        // 设置连接参数：HBase数据库所在的主机IP
        configuration.set("hbase.zookeeper.quorum", "10.10.67.48");
        // 设置连接参数：HBase数据库使用的端口
        configuration.set("hbase.zookeeper.property.clientPort", "2181");
        configuration.set("hbase.client.scanner.timeout.period", "2000");
        configuration.set("hbase.client.retries.number", "3");
        try {
			connection = HConnectionManager.createConnection(configuration);
			Scan scan = new Scan();
			scan.setStartRow(Bytes.toBytes("1"));
			scan.setStopRow(Bytes.toBytes("100"));
			scan.setCacheBlocks(false);
			
			Map<String, String> map = new HashMap<String, String>();
			HTableInterface table = null;
			ResultScanner rs = null;
			table = connection.getTable("yxt_mobile_all_v2");
			rs = table.getScanner(scan);
		} catch (IOException e) {
		}
//        configuration.set("hbase.zookeeper.property.clientPort", "2181");
//        configuration.set("hbase.zookeeper.quorum", "101.236.39.141,101.236.46.114,101.236.46.113");
////        configuration.set("hbase.master", "101.236.39.141:60000");
//        File workaround = new File(".");
//        System.getProperties().put("hadoop.home.dir",
//                workaround.getAbsolutePath());
//        new File("./bin").mkdirs();
//        try {
//            new File("./bin/winutils.exe").createNewFile();
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        try {
//            connection = ConnectionFactory.createConnection(configuration);
//            admin = connection.getAdmin();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

//    // 关闭连接
//    public static void close() {
//        try {
//            if (null != admin)
//                admin.close();
//            if (null != connection)
//                connection.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    // 建表
//    public static void createTable(String tableNmae, String[] cols) throws IOException {
//
//        init();
//        TableName tableName = TableName.valueOf(tableNmae);
//
//        if (admin.tableExists(tableName)) {
//            System.out.println("talbe is exists!");
//        } else {
//            HTableDescriptor hTableDescriptor = new HTableDescriptor(tableName);
//            for (String col : cols) {
//                HColumnDescriptor hColumnDescriptor = new HColumnDescriptor(col);
//                hTableDescriptor.addFamily(hColumnDescriptor);
//            }
//            admin.createTable(hTableDescriptor);
//        }
//        close();
//    }
//
//    // 删表
//    public static void deleteTable(String tableName) throws IOException {
//        init();
//        TableName tn = TableName.valueOf(tableName);
//        if (admin.tableExists(tn)) {
//            admin.disableTable(tn);
//            admin.deleteTable(tn);
//        }
//        close();
//    }
//
//    // 查看已有表
//    public static void listTables() throws IOException {
//        init();
//        HTableDescriptor hTableDescriptors[] = admin.listTables();
//        for (HTableDescriptor hTableDescriptor : hTableDescriptors) {
//            System.out.println(hTableDescriptor.getNameAsString());
//        }
//        close();
//    }
//
//    // 插入数据
//    public static void insterRow(String tableName, String rowkey, String colFamily, String col, String val)
//            throws IOException {
//        init();
//        Table table = connection.getTable(TableName.valueOf(tableName));
//        Put put = new Put(Bytes.toBytes(rowkey));
//        put.addColumn(Bytes.toBytes(colFamily), Bytes.toBytes(col), Bytes.toBytes(val));
//        table.put(put);
//
//        // 批量插入
//        /*
//         * List<Put> putList = new ArrayList<Put>(); puts.add(put);
//         * table.put(putList);
//         */
//        table.close();
//        close();
//    }
//
//    // 删除数据
//    public static void deleRow(String tableName, String rowkey, String colFamily, String col) throws IOException {
//        init();
//        Table table = connection.getTable(TableName.valueOf(tableName));
//        Delete delete = new Delete(Bytes.toBytes(rowkey));
//        // 删除指定列族
//        // delete.addFamily(Bytes.toBytes(colFamily));
//        // 删除指定列
//        // delete.addColumn(Bytes.toBytes(colFamily),Bytes.toBytes(col));
//        table.delete(delete);
//        // 批量删除
//        /*
//         * List<Delete> deleteList = new ArrayList<Delete>();
//         * deleteList.add(delete); table.delete(deleteList);
//         */
//        table.close();
//        close();
//    }
//
//    // 根据rowkey查找数据
//    public static void getData(String tableName, String rowkey, String colFamily, String col) throws IOException {
//        init();
//        Table table = connection.getTable(TableName.valueOf(tableName));
//        Get get = new Get(Bytes.toBytes(rowkey));
//        // 获取指定列族数据
//        // get.addFamily(Bytes.toBytes(colFamily));
//        // 获取指定列数据
//        // get.addColumn(Bytes.toBytes(colFamily),Bytes.toBytes(col));
//        Result result = table.get(get);
//
//        showCell(result);
//        table.close();
//        close();
//    }
//
//    // 格式化输出
//    public static void showCell(Result result) {
//        Cell[] cells = result.rawCells();
//        for (Cell cell : cells) {
//            System.out.println("RowName:" + new String(CellUtil.cloneRow(cell)) + " ");
//            System.out.println("Timetamp:" + cell.getTimestamp() + " ");
//            System.out.println("column Family:" + new String(CellUtil.cloneFamily(cell)) + " ");
//            System.out.println("row Name:" + new String(CellUtil.cloneQualifier(cell)) + " ");
//            System.out.println("value:" + new String(CellUtil.cloneValue(cell)) + " ");
//        }
//    }
//
//    // 批量查找数据
//    public static void scanData(String tableName, String startRow, String stopRow) throws IOException {
//        init();
//        Table table = connection.getTable(TableName.valueOf(tableName));
//        Scan scan = new Scan();
//        // scan.setStartRow(Bytes.toBytes(startRow));
//        // scan.setStopRow(Bytes.toBytes(stopRow));
//        ResultScanner resultScanner = table.getScanner(scan);
//        for (Result result : resultScanner) {
//            showCell(result);
//        }
//        table.close();
//        close();
//    }

}
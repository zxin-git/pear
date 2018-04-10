package com.zxin.lucene;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystems;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public class Lucene6Test {
//	public static void main(String[] args) {
////		writer();
//		search("java");
//	}
//	public static void writer(){
//		IndexWriter indexWriter = null;
//		try {
//			Directory dir = FSDirectory.open(FileSystems.getDefault().getPath("D:\\developer\\temp\\java\\dd"));
//			Analyzer analyzer = new StandardAnalyzer();
//            IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);
//            indexWriter = new IndexWriter(dir, indexWriterConfig);
//            indexWriter.deleteAll();//清除以前的index
//            //要搜索的File路径
//            File dFile = new File("D:\\developer\\temp\\java\\txt");
//            File[] files = dFile.listFiles();
//            for (File file : files) {
//                // 3、创建Document对象
//                Document document = new Document();
//                // 4、为Document添加Field
//                // 第三个参数是FieldType 但是定义在TextField中作为静态变量，看API也不好知道怎么写
//                document.add(new Field("content", new FileReader(file), TextField.TYPE_NOT_STORED));
//                document.add(new Field("filename", file.getName(), TextField.TYPE_STORED));
//                document.add(new Field("filepath", file.getAbsolutePath(), TextField.TYPE_STORED));
//
//                // 5、通过IndexWriter添加文档到索引中
//                indexWriter.addDocument(document);
//            }
//            indexWriter.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//	
//	 public static void search(String keyWord) {  
//	        DirectoryReader directoryReader = null;  
//	        try {  
//	            // 1、创建Directory  
//	            Directory directory = FSDirectory.open(FileSystems.getDefault().getPath("D:\\developer\\temp\\java\\dd"));
//	            // 2、创建IndexReader  
//	            directoryReader = DirectoryReader.open(directory);  
//	            // 3、根据IndexReader创建IndexSearch  
//	            IndexSearcher indexSearcher = new IndexSearcher(directoryReader);  
//
//	            // 4、创建搜索的Query  
//	            Analyzer analyzer = new StandardAnalyzer();  
//	            // 创建parser来确定要搜索文件的内容，第一个参数为搜索的域  
//	            QueryParser queryParser = new QueryParser("filepath", analyzer);  
////	            QueryParser queryParser = new QueryParser("content", analyzer);  
//	            // 创建Query表示搜索域为content包含UIMA的文档  
//	            Query query = queryParser.parse(keyWord);  
//
//	            // 5、根据searcher搜索并且返回TopDocs  
//	            TopDocs topDocs = indexSearcher.search(query, 10);  
//	            System.out.println("查找到的文档总共有："+topDocs.totalHits);
//
//	            // 6、根据TopDocs获取ScoreDoc对象  
//	            ScoreDoc[] scoreDocs = topDocs.scoreDocs;  
//	            for (ScoreDoc scoreDoc : scoreDocs) {  
//
//	                // 7、根据searcher和ScoreDoc对象获取具体的Document对象  
//	                Document document = indexSearcher.doc(scoreDoc.doc);  
//
//	                // 8、根据Document对象获取需要的值  
//	                System.out.println(document.get("filename") + " " + document.get("filepath"));  
//	            }  
//
//	        } catch (Exception e) {  
//	            e.printStackTrace();  
//	        } finally {  
//	            try {  
//	                if (directoryReader != null) {  
//	                    directoryReader.close();  
//	                }  
//	            } catch (Exception e) {  
//	                e.printStackTrace();  
//	            }  
//	        }  
//	    }  
}

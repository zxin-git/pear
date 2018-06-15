package com.zxin.lucene.concurrent;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

public class MainTest {

	public static void main(String[] args) {
		try {
			StandardAnalyzer analyzer = new StandardAnalyzer(Version.LUCENE_40);
			String indexPath = "D:\\developer\\temp\\java\\lucene";
			Directory dir = FSDirectory.open(new File(indexPath));  
			IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_40,analyzer);
			IndexWriter w = new IndexWriter(dir,config);
			for (int i = 0; i < 10; i++) {
				DocumentHandler dh = new DocumentHandler("name"+i,w);
				new Thread(dh).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

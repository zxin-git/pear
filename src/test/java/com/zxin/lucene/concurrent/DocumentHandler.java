package com.zxin.lucene.concurrent;

import java.io.IOException;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.nutz.json.Json;

public class DocumentHandler implements Runnable{
	
	private String name;
	private IndexWriter w;
	public DocumentHandler(String name,IndexWriter w) {
		this.name = name;
		this.w = w;
	}
	
	@Override
	public void run() {
		try {
			Document doc = new Document();
			int i=500;
			while(i--!=0){
				doc.add(new TextField(name+i,"magic iphone",Field.Store.YES));
				System.out.println(Json.toJson(doc));
				w.addDocument(doc);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

}

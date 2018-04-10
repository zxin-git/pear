package com.zxin.lucene;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;

public class LuceneTest {
	static StandardAnalyzer analyzer = new StandardAnalyzer(Version.LUCENE_40);
	static String indexPath = "D:\\developer\\temp\\java\\lucene";
	public static void main(String[] args) {
//		Directory dir = new RAMDirectory();  
		try {
			Directory dir = FSDirectory.open(new File(indexPath));  
			IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_40,analyzer);
			IndexWriter w = new IndexWriter(dir,config);
			Document doc = new Document();
			doc.add(new TextField("zx","magic iphone",Field.Store.YES));
			w.addDocument(doc);
//			addDoc(w, "Lucene in Action", "193398817");
//			addDoc(w, "Lucene for Dummies", "55320055Z");
//			addDoc(w, "Managing Gigabytes", "55063554A");
//			addDoc(w, "The Art of Computer Science", "9900333X");
			w.close();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			
		}
	}
	
	
	private static void addDoc(IndexWriter w, String title, String isbn) throws IOException {
		Document doc = new Document();
		doc.add(new TextField("title", title, Field.Store.YES));
		doc.add(new StringField("isbn", isbn, Field.Store.YES));
		w.addDocument(doc);
	}
}

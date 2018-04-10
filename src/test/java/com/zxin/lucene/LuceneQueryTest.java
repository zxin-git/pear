package com.zxin.lucene;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.nutz.json.Json;

public class LuceneQueryTest {
	static StandardAnalyzer analyzer = new StandardAnalyzer(Version.LUCENE_40);
	static String indexPath = "D:\\developer\\temp\\java\\lucene";
	public static void main(String[] args) {
		String querystr = args.length > 0 ? args[0] : "lucene";
		try {
			Query q = new QueryParser(Version.LUCENE_40, "title", analyzer).parse(querystr);
			int hitsPerPage = 10;
			Directory dir = FSDirectory.open(new File(indexPath));
			IndexReader reader = IndexReader.open(dir);
			IndexSearcher searcher = new IndexSearcher(reader);
//			TopScoreDocCollector collector = TopScoreDocCollector.create(hitsPerPage, true);
//			searcher.search(q, collector);
			TopDocs topDocs = searcher.search(q, 10);
//			ScoreDoc[] hits = collector.topDocs().scoreDocs;
			ScoreDoc[] hits = topDocs.scoreDocs;
			System.out.println(Json.toJson(hits));
			System.out.println("Found " + hits.length + " hits.");
			for(int i=0;i<hits.length;++i) {
			    int docId = hits[i].doc;
			    Document d = searcher.doc(docId);
			    System.out.println((i + 1) + ". " + d.get("isbn") + "\t" + d.get("title"));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

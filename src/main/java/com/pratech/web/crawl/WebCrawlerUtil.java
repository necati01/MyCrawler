package com.pratech.web.crawl;

import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class WebCrawlerUtil {
	private static final String USER_AGENT =
	          "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/13.0.782.112 Safari/535.1";

	public  Document getPage(String url) throws  IOException , IllegalArgumentException {
			Connection connection = Jsoup.connect(url).userAgent(USER_AGENT);
			return connection.get();
	}
	

}

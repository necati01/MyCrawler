package com.pratech.crawler;

import org.jsoup.nodes.Document;

public interface IWebCrawler {
	public void crawl(Domain domain, Document document, String url);
}

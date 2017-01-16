package com.pratech.crawler;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WebCrawler {
	
	public WebCrawler(){
	}
	
	public void crawl(Domain domain, Document document, String url) {
		String baseUrl=domain.getBaseUrl();
		Elements links = document.select("a[href]");
		for (Element link : links) {
			String href = link.attr("abs:href");
			if (href.startsWith(baseUrl)) {
				domain.getDomainUrls().add(href);
			} else if (!domain.getExternalUrls().contains(href)) {
				domain.getExternalUrls().add(href);
			}
		}
		
	}

}

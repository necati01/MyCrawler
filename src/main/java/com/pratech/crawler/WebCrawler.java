package com.pratech.crawler;

import java.io.IOException;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WebCrawler implements IWebCrawler{
	
	public WebCrawler(){
	}
	
	public void crawl(Domain domain, Document document, String url) {
			domain.getVisitedDomainUrls().add(url);
			getDomainUrls(domain, document, url);
			domain.getDomainUrls().add(url);
	}
	
	private void getDomainUrls(Domain domain, Document document,String url){
		String baseUrl=domain.getBaseUrl();
		Elements links = document.select("a[href]");
		for (Element link : links) {
			String href = link.attr("href");
			href=WebCrawlerUtil.normalize(href, baseUrl);
			if (href.startsWith(baseUrl)) {
				if (!domain.getVisitedDomainUrls().contains(href)) {
					try {
						document =  WebCrawlerUtil.getPage(href);
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IOException e) {
						return;
					}
					crawl(domain,document,href);
					domain.getDomainUrls().add(href);
				}
			} else if (!domain.getExternalUrls().contains(href)) {
				domain.getExternalUrls().add(href);
			}
		}
	}
	
	

}

package com.pratech.crawler;

import java.io.IOException;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WebCrawler implements IWebCrawler{
	private static final int MAX_PAGES_TO_SEARCH = 500;
	public WebCrawler(){
	}
	
	public void crawl(Domain domain, Document document, String url) {
		getImgStaticUrls(domain, document, url);	
		getSrcStaticUrls(domain, document, url);
		getLinkStaticUrls(domain, document, url);
		domain.getVisitedDomainUrls().add(url);
		domain.getDomainUrls().add(url);
		if(domain.getVisitedDomainUrls().size()>MAX_PAGES_TO_SEARCH){
			return;
		}
		getDomainUrls(domain, document, url);
		
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
						Document doc =  WebCrawlerUtil.getPage(href);
						crawl(domain,doc,href);
						domain.getDomainUrls().add(href);
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IOException e) {
						return;
					}
				}
			} else if (!domain.getExternalUrls().contains(href)) {
				domain.getExternalUrls().add(href);
			}
		}
	}
	
	private void getSrcStaticUrls(Domain domain, Document document,String url){
		Elements links = document.select("script");
		for (Element link : links) {
			String href = link.attr("src");
			domain.getStaticUrls().add(href);
		}
	}
	
	private void getLinkStaticUrls(Domain domain, Document document,String url){
		Elements links = document.select("link");
		for (Element link : links) {
			String href = link.attr("abs:href");
			domain.getStaticUrls().add(href);
		}
	}
	
	private void getImgStaticUrls(Domain domain, Document document,String url){
		Elements links = document.select("img");
		for (Element link : links) {
			String href = link.attr("src");
				domain.getStaticUrls().add(href);
		}
	}
	
	

}

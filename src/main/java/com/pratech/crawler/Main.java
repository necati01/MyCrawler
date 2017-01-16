package com.pratech.crawler;

import java.io.IOException;

import org.jsoup.nodes.Document;

public class Main {
	public static void main(String[] args) throws IllegalArgumentException, IOException {
		String host = "http://www.pratechnology.co.uk"; 
    	Domain domain=new Domain(host);
    	Document doc =  WebCrawlerUtil.getPage(host);
		new WebCrawler().crawl(domain,doc,host);
		System.out.println("ALL URLS:");
		domain.printUrlList();
	}
}

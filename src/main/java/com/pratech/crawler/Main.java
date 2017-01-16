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
		for (String string : domain.getDomainUrls()) {
			System.out.println(string);
		}
		for (String string : domain.getStaticUrls()) {
			System.out.println(string);
		}
		for (String string : domain.getExternalUrls()) {
			System.out.println(string);
		}
	}
}

package com.pratech.web;

import com.pratech.web.crawl.WebCrawler;
import com.pratech.web.site.SiteListPrinter;
import com.pratech.web.site.SiteUrlList;

public class App {


	public static void main(String[] args) throws Exception {

		String baseUrl = "http://www.pratechnology.co.uk";

		CrawlAndPrinter myCrawler = new CrawlAndPrinter(new SiteListPrinter(new SiteUrlList(baseUrl)), new WebCrawler());

		myCrawler.crawlTheSite();

		myCrawler.printTheLinks();

	}



}

package com.pratech.web;

import com.pratech.web.crawl.WebCrawler;
import com.pratech.web.site.SiteListPrinter;
import com.pratech.web.site.SiteUrlList;

import java.io.IOException;

public class CrawlAndPrinter {

    private final String baseUrl;
    private final SiteListPrinter siteListPrinter;
    private final SiteUrlList siteUrlList ;
    private final WebCrawler webCrawler;

    public CrawlAndPrinter(SiteListPrinter siteListPrinter, WebCrawler webCrawler) {
        this.siteUrlList = siteListPrinter.getSiteUrlList();
        this.baseUrl = siteUrlList.getBaseUrl();
        this.siteListPrinter = siteListPrinter;
        this.webCrawler = webCrawler;
    }

    public  void printTheLinks() {
        siteListPrinter.printAll();
    }

    public  void crawlTheSite() throws IOException {
        webCrawler.crawl(siteUrlList, baseUrl);
    }

}

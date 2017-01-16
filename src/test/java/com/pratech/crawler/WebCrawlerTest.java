package com.pratech.crawler;

import static org.hamcrest.Matchers.hasItem;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Before;
import org.junit.Test;

public class WebCrawlerTest {
	Document doc = null; 
	WebCrawler crawler ;
	
	@Before
	public void init() throws IOException {
		crawler =new WebCrawler();
	}

    @Test 
    public void getPageTest() throws IOException , IllegalArgumentException { 
    	String url="https://www.google.com";
    	doc=WebCrawlerUtil.getPage(url);
		assertTrue(doc.title().contains("Google"));
    }
    
    @Test 
    public void getDomainLinks() {
    	StringBuffer html = new StringBuffer("<a href=\"http://example.tst\"></a>"); 
    	String host = "http://example.tst"; 
    	Domain domain=new Domain(host);
    	String expectedUrl = "http://example.tst";
    	setDoc(html.toString(), host);
    	
    	crawler.crawl(domain,doc,host);
    	assertThat(domain.getDomainUrls(), hasItem(expectedUrl));
    	
    	html.append("<a href=\"http://www.abcdef.co.uk\"></a>");
    	html.append("<a href=\"http://example.tst/company\"></a>"); 
    	Domain domain2=new Domain(host);
    	setDoc(html.toString(), host);
    	crawler.crawl(domain2,doc,host);
    	assertThat(domain2.getDomainUrls(), hasItem("http://example.tst"));
    	assertThat(domain2.getDomainUrls(), hasItem("http://example.tst/company"));
    	assertTrue(!domain2.getDomainUrls().contains("http://www.abcdef.co.uk"));
    }
    
    @Test 
    public void getNormalizedDomainLinks() {
    	StringBuffer html = new StringBuffer("<a href=\"http://example.tst/company/\"></a>"); 
    	html.append("<a href=\"/ref/references.html\"></a>");
    	String host = "http://example.tst"; 
    	Domain domain=new Domain(host);
    	setDoc(html.toString(), host);
    	crawler.crawl(domain,doc,host);
    	assertThat(domain.getDomainUrls(), hasItem("http://example.tst/ref/references.html"));
    }
    
    @Test 
    public void checkVisitedPages() throws IllegalArgumentException, IOException {
    	String host = "http://www.pratechnology.co.uk"; 
    	Domain domain=new Domain(host);
    	doc =  WebCrawlerUtil.getPage(host);
    	crawler.crawl(domain,doc,host);
    	for (String str : domain.getVisitedDomainUrls()) {
    		assertTrue(str.length()>=host.length());
		}
    	assertTrue((domain.getVisitedDomainUrls().size()>1));
//    	domain.getVisitedDomainUrls().forEach(url -> assertTrue(url.length()>=host.length()));    	   	
    }
    
    private void setDoc(String html, String host) {
    	  String htmlSkeleton = "<html><head></head><body></body></html>"; 
    	  this.doc = Jsoup.parse(htmlSkeleton, "http://" + host); 
    	  this.doc.body().append(html);   
    	  this.doc.normalise(); 
   } 
}

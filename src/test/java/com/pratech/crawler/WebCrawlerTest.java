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
	
	@Before
	public void init() throws IOException {
	}

    @Test 
    public void getPageTest() throws IOException , IllegalArgumentException { 
    	String url="https://www.google.com";
    	doc=WebCrawlerUtil.getPage(url);
		assertTrue(doc.title().contains("Google"));
    }
    
    @Test 
    public void getDomainLinks() throws IOException , IllegalArgumentException {
    	StringBuffer html = new StringBuffer("<a href=\"http://pratechnology.co.uk\"></a>"); 
    	String host = "http://pratechnology.co.uk"; 
    	Domain domain=new Domain(host);
    	String expectedUrl = "http://pratechnology.co.uk";
    	setDoc(html.toString(), host);
    	WebCrawler crawler =new WebCrawler();
    	crawler.crawl(domain,doc,host);
    	assertThat(domain.getDomainUrls(), hasItem(expectedUrl));
    	
    	html.append("<a href=\"http://www.abcdef.co.uk\"></a>");
    	html.append("<a href=\"http://pratechnology.co.uk/company\"></a>"); 
    	Domain domain2=new Domain(host);
    	setDoc(html.toString(), host);
    	crawler =new WebCrawler();
    	crawler.crawl(domain2,doc,host);
    	assertThat(domain2.getDomainUrls(), hasItem("http://pratechnology.co.uk"));
    	assertThat(domain2.getDomainUrls(), hasItem("http://pratechnology.co.uk/company"));
    	assertTrue(!domain2.getDomainUrls().contains("http://www.abcdef.co.uk"));
    }
    
    private void setDoc(String html, String host) { 
    	  String htmlSkeleton = "<html><head></head><body></body></html>"; 
    	  this.doc = Jsoup.parse(htmlSkeleton, "http://" + host); 
    	  this.doc.body().append(html);   
    	  this.doc.normalise(); 
   } 
}

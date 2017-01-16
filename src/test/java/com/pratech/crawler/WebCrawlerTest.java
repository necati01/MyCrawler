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
    }
    
    @Test 
    public void getExternalLinks() {
    	StringBuffer html = new StringBuffer("<a href=\"http://tyudsf.tref\"></a>"); 
    	html.append("<script src=\"https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js\"></script>");
    	html.append("<a href=\"http://www.xyzabcdef.co.uk\"></a>");
    	html.append("<a href=\"http://tyudsf.tref/company\"></a>"); 
    	html.append("<img src=\"http://uhtgfs.kltdf/img/test.png\" alt=\"Wait\" />"); 
//    	html.append("<a href=\"/ref/references.html\"></a>");
    	String host = "http://tyudsf.tref"; 
    	Domain domain=new Domain(host);
    	setDoc(html.toString(), host);
    	crawler.crawl(domain,doc,host);
    	assertTrue(domain.getExternalUrls().size()==1);
    	assertThat(domain.getExternalUrls(),hasItem("http://www.xyzabcdef.co.uk"));
    	assertTrue(!domain.getExternalUrls().contains("http://tyudsf.tref/company"));
    	assertTrue(!domain.getExternalUrls().contains("https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"));
    }
    
    @Test 
    public void getStaticLinks() {
    	StringBuffer html = new StringBuffer("<a href=\"http://example.tst/x/y/z\"></a>"); 
    	html.append("<a href=\"http://www.abcdef.co.uk\"></a>");
    	html.append("<img src=\"../img/ajax-loader.gif\" alt=\"Wait\" />"); 
    	html.append("<a href=\"references.html\"></a>");
    	html.append("<link rel=\"stylesheet\" href=\"../../dist/css/dvf.css\" type= \"text/css\" media=\"screen\"/>");
//    	html.append("<script src=\"https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js\"></script>");
    	html.append("<script type=\"text/javascript\" src=\"../dist/leaflet-dvf.js\"></script>");
    	String host = "http://example.tst"; 
    	Domain domain=new Domain(host);
    	setDoc(html.toString(), host);
    	crawler.crawl(domain,doc,host);
    	assertTrue(!domain.getStaticUrls().contains("http://example.tst/references.html"));
    	assertTrue(domain.getDomainUrls().contains("http://example.tst/references.html"));
    	assertTrue(domain.getStaticUrls().contains("../img/ajax-loader.gif"));
    	assertTrue(domain.getStaticUrls().contains("../../dist/css/dvf.css"));
    	assertTrue(domain.getStaticUrls().contains("../dist/leaflet-dvf.js"));
    	assertTrue(domain.getExternalUrls().contains("http://www.abcdef.co.uk"));
    }
    
    private void setDoc(String html, String host) {
    	  String htmlSkeleton = "<html><head></head><body></body></html>"; 
    	  this.doc = Jsoup.parse(htmlSkeleton, "http://" + host); 
    	  this.doc.body().append(html);   
    	  this.doc.normalise(); 
   } 
}

package com.pratech.crawler;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.jsoup.nodes.Document;
import org.junit.Before;
import org.junit.Test;

public class WebCrawlerTest {

	@Before
	public void init() throws IOException {
	}

    @Test 
    public void getPageTest() throws IOException { 
    	String url="www.google.com";
    	Document doc=WebCrawler.getPage(url);
		assertTrue(doc.title().contains("Google")); 
    }
	
}

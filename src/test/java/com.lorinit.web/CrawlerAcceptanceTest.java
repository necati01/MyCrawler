package com.lorinit.web;

import com.lorinit.web.crawler.Crawler;
import com.lorinit.web.crawler.Link;
import com.lorinit.web.exceptions.WrongUrlException;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CrawlerAcceptanceTest {


    @Test(expected = WrongUrlException.class)
    public void givenBrokenUrlGivesException() throws Exception {

        // given a non existing url

        PageProvider pageProvider = Mockito.mock(PageProvider.class);
        String siteUrl = "http://34534534";

        Mockito.when(pageProvider.getPageContent(siteUrl)).thenThrow(new WrongUrlException());

        Crawler crawler = new Crawler(pageProvider);

        // when attempted to crawl
        crawler.crawl(siteUrl);
    }


    @Test
    public void givenAPageWithNoUrlGivesNoLinkListed() throws Exception {

        // given a site with no link
/*
        String htmlSkeleton = "<html><head></head><body></body></html>";
        Document document = Jsoup.parse(htmlSkeleton, "http://www.yyy.com");
        document.body().append("");
        document.normalise();
*/

        PageProvider pageProvider = Mockito.mock(PageProvider.class);
        String siteUrl = "http://localhost/firspage1.html";
        Mockito.when(pageProvider.getPageContent(siteUrl)).thenReturn("<html><head></head><body></body></html>");

        Crawler crawler = new Crawler(pageProvider);

        // when attempted to crawl
        Set<Link> linkSet = crawler.crawl(siteUrl);

        // then gives any empty list
        Assert.assertTrue(linkSet.isEmpty());

    }
    @Test
    public void givenAPageWithAnInternalSingleLink() throws Exception {

        // given a site with one link
        PageProvider pageProvider = Mockito.mock(PageProvider.class);
        String siteUrl = "http://www.yyy.com";
        Mockito.when(pageProvider.getPageContent(siteUrl)).thenReturn("<html><head></head><body><a href='/lists/personal.html'/></body></html>");

        Crawler crawler = new Crawler(pageProvider);

        // when attempted to crawl
        Set<Link> linkSet = crawler.crawl(siteUrl);

        // then gives a the list with one link
        Assert.assertFalse(linkSet.isEmpty());
        assertThat(linkSet.iterator().next().getFullUrl(), is("http://www.yyy.com/lists/personal.html"));

    }

    /*
    @Test
    public void givenAPageWithMultiLinks() throws Exception {

        // given a site with two links

        // when attempted to crawl

        // then gives a the list with two links

    }

    @Test
    public void givenAPageWithASingleALinkToAnOnsitePageHavingTwoLinks() throws Exception {

        // given a site with one link having a link to a page having two links

        // when attempted to crawl

        // then gives a the list with one link pointing to two links

    }
    */

}

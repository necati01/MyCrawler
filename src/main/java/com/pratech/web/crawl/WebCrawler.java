package com.pratech.web.crawl;

import java.io.IOException;

import com.pratech.web.site.PathMerger;
import com.pratech.web.site.SiteUrlList;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WebCrawler  {
	private static final int MAX_PAGES_TO_SEARCH = 100;

	private final PageDownloader pageDownloader = new PageDownloader();
	private final PageLinkExtracter pageLinkExtracter = new PageLinkExtracter();
	private final PathMerger pathMerger = new PathMerger();

	public WebCrawler(){
	}

	public void crawl(SiteUrlList siteUrlList, String url) throws IOException {

		Document document =  pageDownloader.getPage(url);

		// add image urls, static urls, css urls to the list
		pageLinkExtracter.extractStaticLinks(siteUrlList, document, "img", "src");
		pageLinkExtracter.extractStaticLinks(siteUrlList, document, "script", "src");
		pageLinkExtracter.extractStaticLinks(siteUrlList, document, "link", "abs:href");
		pageLinkExtracter.extractOnsiteUrls(siteUrlList, document);

		if(siteUrlList.getDomainUrls().size()>MAX_PAGES_TO_SEARCH){
			return;
		}
		crawlDomainUrls(siteUrlList, document);

	}
	private void crawlDomainUrls(SiteUrlList siteUrlList, Document document) throws IOException {
		String baseUrl= siteUrlList.getBaseUrl();
		Elements links = document.select("a[href]");
		for (Element link : links) {
			String href = link.attr("href");
			href= pathMerger.normalize(href, baseUrl);
			if (href.startsWith(baseUrl)) {
				if (!siteUrlList.getDomainUrls().contains(href)) {
						crawl(siteUrlList, href);
				}
			}
		}
	}


}

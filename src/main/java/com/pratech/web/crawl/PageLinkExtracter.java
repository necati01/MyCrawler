package com.pratech.web.crawl;

import com.pratech.web.site.PathMerger;
import com.pratech.web.site.SiteUrlList;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class PageLinkExtracter {
    private final PathMerger pathMerger= new PathMerger();

    void extractOnsiteUrls(SiteUrlList siteUrlList, Document document) throws IOException {
        String baseUrl= siteUrlList.getBaseUrl();
        Elements links = document.select("a[href]");
        for (Element link : links) {
            String href = link.attr("href");
            href= pathMerger.normalize(href, baseUrl);
            if (href.startsWith(baseUrl)) {
                        siteUrlList.getDomainUrls().add(href);
            } else if (!siteUrlList.getExternalUrls().contains(href)) {
                siteUrlList.getExternalUrls().add(href);
            }
        }
    }

    void extractStaticLinks(SiteUrlList siteUrlList, Document document, String tag, String attributeKey){
        Elements links = document.select(tag);
        for (Element link : links) {
            String href = link.attr(attributeKey);
                siteUrlList.getStaticUrls().add(href);
        }
    }
}

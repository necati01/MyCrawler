package com.pratech.web.site;

import java.util.Set;

public class SiteListPrinter {

    public SiteUrlList getSiteUrlList() {
        return siteUrlList;
    }

    private final SiteUrlList siteUrlList;

    public SiteListPrinter(SiteUrlList siteUrlList) {

        this.siteUrlList = siteUrlList;
    }

    public void printAll() {

        printDomainUrls();

        printExternalUrls();

        printStaticUrls();
    }

    public void printStaticUrls() {
        printSet(siteUrlList.getStaticUrls(), "Links to static content such as images for each respective page, count : ");
    }

    public void printExternalUrls() {
        printSet(siteUrlList.getExternalUrls(), "Links to external URLs, count : ");
    }

    public void printDomainUrls() {
        printSet(siteUrlList.getDomainUrls(), "Links to other pages under the same domain, count : ");
    }

    private void printSet(Set<String> domainUrls, String caption) {
        System.out.println(caption + domainUrls.size());
        domainUrls.forEach(System.out::println);
        System.out.println("\n");
    }


}

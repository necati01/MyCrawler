package com.pratech.web.site;

import java.util.HashSet;
import java.util.Set;

public class SiteUrlList {

	private String baseUrl;
	private Set<String> domainUrls = new HashSet<String>();
	private Set<String> externalUrls = new HashSet<String>();
	private Set<String> staticUrls = new HashSet<String>();
	
	public SiteUrlList(String baseUrl) {
		this.baseUrl=baseUrl;
	}

	public Set<String> getDomainUrls() {
		return domainUrls;
	}
	
	public String getBaseUrl() {
		return baseUrl;
	}

	public Set<String> getExternalUrls() {
		return externalUrls;
	}

	public Set<String> getStaticUrls() {
		return staticUrls;
	}


}

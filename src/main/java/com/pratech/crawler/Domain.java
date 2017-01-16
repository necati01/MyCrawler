package com.pratech.crawler;

import java.util.HashSet;
import java.util.Set;

public class Domain {
	private String baseUrl;
	private Set<String> domainUrls = new HashSet<String>();
	private Set<String> externalUrls = new HashSet<String>();
	private Set<String> visitedDomainUrls = new HashSet<String>();
	private Set<String> staticUrls = new HashSet<String>();
	
	public Domain() {
	}
	
	public Domain(String baseUrl) {
		this.baseUrl=baseUrl;
	}

	public Domain(Set<String> domainUrls) {
		super();
		this.domainUrls = domainUrls;
	}

	public Set<String> getDomainUrls() {
		return domainUrls;
	}
	
	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public void setDomainUrls(Set<String> domainUrls) {
		this.domainUrls = domainUrls;
	}
	
	public Set<String> getExternalUrls() {
		return externalUrls;
	}

	public void setExternalUrls(Set<String> externalUrls) {
		this.externalUrls = externalUrls;
	}
	
	public Set<String> getVisitedDomainUrls() {
		return visitedDomainUrls;
	}

	public void setVisitedDomainUrls(Set<String> visitedDomainUrls) {
		this.visitedDomainUrls = visitedDomainUrls;
	}

	public Set<String> getStaticUrls() {
		return staticUrls;
	}

	public void setStaticUrls(Set<String> staticUrls) {
		this.staticUrls = staticUrls;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((baseUrl == null) ? 0 : baseUrl.hashCode());
		result = prime * result + ((domainUrls == null) ? 0 : domainUrls.hashCode());
		result = prime * result + ((externalUrls == null) ? 0 : externalUrls.hashCode());
		result = prime * result + ((staticUrls == null) ? 0 : staticUrls.hashCode());
		result = prime * result + ((visitedDomainUrls == null) ? 0 : visitedDomainUrls.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Domain other = (Domain) obj;
		if (baseUrl == null) {
			if (other.baseUrl != null)
				return false;
		} else if (!baseUrl.equals(other.baseUrl))
			return false;
		if (domainUrls == null) {
			if (other.domainUrls != null)
				return false;
		} else if (!domainUrls.equals(other.domainUrls))
			return false;
		if (externalUrls == null) {
			if (other.externalUrls != null)
				return false;
		} else if (!externalUrls.equals(other.externalUrls))
			return false;
		if (staticUrls == null) {
			if (other.staticUrls != null)
				return false;
		} else if (!staticUrls.equals(other.staticUrls))
			return false;
		if (visitedDomainUrls == null) {
			if (other.visitedDomainUrls != null)
				return false;
		} else if (!visitedDomainUrls.equals(other.visitedDomainUrls))
			return false;
		return true;
	}
	
	public void printUrlList () {
		domainUrls.forEach(System.out::println);
		externalUrls.forEach(System.out::println);
		staticUrls.forEach(System.out::println);
	}

	@Override
	public String toString() {
		
		return "Domain [baseUrl=" + baseUrl + ", domainUrls=" + domainUrls + ", externalUrls=" + externalUrls
				+ ", visitedDomainUrls=" + visitedDomainUrls + ", staticUrls=" + staticUrls + "]";
	}
	
}

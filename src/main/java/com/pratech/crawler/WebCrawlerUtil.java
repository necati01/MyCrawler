package com.pratech.crawler;

import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class WebCrawlerUtil {
	private static final String USER_AGENT =
	          "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/13.0.782.112 Safari/535.1";

	public static Document getPage(String url) throws  IOException , IllegalArgumentException {
			Connection connection = Jsoup.connect(url).userAgent(USER_AGENT);
			return connection.get();
	}
	
	public static String normalize(String inputUrl, String baseUrl) {
        if(inputUrl.startsWith("http://"))                   return inputUrl;
        if(inputUrl.startsWith("https://"))                  return inputUrl;
        if(inputUrl.contains("mailto:"))                  	return inputUrl;
        if(inputUrl.toLowerCase().startsWith("javascript:")) return inputUrl;

        StringBuilder normalizedUrl = new StringBuilder();

        if(!baseUrl.startsWith("http://")) {
            normalizedUrl.append("http://");
        }

        if(inputUrl.startsWith("/")) {
            int endOfDomain = baseUrl.indexOf("/", 7);
            if(endOfDomain == -1){
                normalizedUrl.append(baseUrl);
            } else {
                normalizedUrl.append(baseUrl.substring(0, endOfDomain));
            }
            normalizedUrl.append(inputUrl);
        } else {
            //find last directory of base url
            int lastDirSeparatorIndex = baseUrl.lastIndexOf("/");
            if(lastDirSeparatorIndex > 6){
                normalizedUrl.append(baseUrl.substring(0, lastDirSeparatorIndex));
            } else {
                normalizedUrl.append(baseUrl);
            }
            normalizedUrl.append('/');
            normalizedUrl.append(inputUrl);
        }
        
        // delete any fragment identifiers
        int fragmentIndex = normalizedUrl.indexOf("#");
        if(fragmentIndex > -1) {
            normalizedUrl.delete(fragmentIndex, normalizedUrl.length());
        }

        // delete any internal dir up ../ if any are inside the URL, and not just in the start.
        int indexOfDirUp = normalizedUrl.indexOf("../");
        while(indexOfDirUp > -1){
            int indexOfLastDirBeforeDirUp = normalizedUrl.lastIndexOf("/", indexOfDirUp - 2);
            if(indexOfLastDirBeforeDirUp > -1 ) {
                normalizedUrl.delete(indexOfLastDirBeforeDirUp +1, indexOfDirUp + 3);
            }
            indexOfDirUp = normalizedUrl.indexOf("../");
        }

        return normalizedUrl.toString();

    }
}

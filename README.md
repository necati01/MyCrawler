# MyCrawler
This is a simple, recursive Java Web-Crawler for internal and external links and images on a specific website, which produce an output to console including the found pages and links. While it attempts to crawl through any website and find new links.

Important: Crawling may take some time and use many server-resources. Be careful!

- How to build and Run Project: 

1- Clone or Download source code fro repository
from git-ssh: 
>git clone git@github.com:necati01/MyCrawler.git

2- Go to Project Folder and type into console: 
>mvn compile

3- Type into console to run:
mvn exec:java -Dexec.mainClass="com.pratech.crawler.Main"

- How to be improved:

1- Url normalizer can be tested using more test case
2- Performans can be improved
3- Multithreadding can be added 
4- Mock objects can be used for test cases
5- Developping GUIor input url from user  can be refactored
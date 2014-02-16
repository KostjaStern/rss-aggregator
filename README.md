## Java Web RSS Aggregator


Simple  Spring 3.1.1 + Hibernate 4 + PostgreSQL 9.3 + Maven web application
that allowed saved rss feeds and get from this feeds news (now only news title, news link)


### For deploying application you need 

#### install
   * [JDK 1.6](http://www.oracle.com/technetwork/java/javase/downloads/java-archive-downloads-javase6-419409.html)
   * [Tomcat 7.0](http://tomcat.apache.org/download-70.cgi)
   * [PostgreSQL 9.3](http://www.enterprisedb.com/products-services-training/pgdownload)
   * [Maven 2.0 or later](http://maven.apache.org/download.cgi)
   
#### deploy
   * create database **rssagregator**
   * execute sql from file **src/main/sql/schema.sql**
   * edit database connect settings in file **src/main/resources/hibernate.cfg.xml**
   * run maven command 
```
   mvn package
```


    from folder where you unpacked archive
	
   * copy RssAggregator.war file from **target** folder (it maven created) to you **webapps** folder in Tomcat installation folder
   * open in browser url [http://localhost:8080/RssAggregator](http://localhost:8080/RssAggregator)

### Application features
   * using annotations for modeling relationship *one to many*
   * using custom annotation for validation rss url
   * using Ajax for deleting and updating rss news list
   
   
![Feeds list page](/img/feeds-page.png)

![Add new feed page](/img/add-feed-page.png)

![News list page](/img/news-page.png)
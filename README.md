## Java Web RSS Aggregator


Simple  Spring 3.1.1 + Hibernate 4 + PostgreSQL 9.3 + Maven web application
that allowed save rss feeds and get from this feeds news (now only title and link)


### To deploy the application, you will need

#### install
   * [JDK 1.6](http://www.oracle.com/technetwork/java/javase/downloads/java-archive-downloads-javase6-419409.html)
   * [Tomcat 7.0](http://tomcat.apache.org/download-70.cgi)
   * [PostgreSQL 9.3](http://www.enterprisedb.com/products-services-training/pgdownload)
   * [Maven 2.0 or later](http://maven.apache.org/download.cgi)
   
#### deploy
   * create database **rssagregator**
   * execute sql from file **src/main/sql/schema.sql**
```sql
CREATE SEQUENCE auto_id_rss_feeds;

CREATE TABLE rss_feeds (
    id      integer NOT NULL DEFAULT nextval('auto_id_rss_feeds'),
    name    varchar(50),
    url     varchar(250),
	PRIMARY KEY (id)
);


CREATE SEQUENCE auto_id_rss_news;

CREATE TABLE rss_news (
    id      integer NOT NULL DEFAULT nextval('auto_id_rss_news'),
	feed_id integer,
    title   varchar(250),
    link    varchar(250),
	PRIMARY KEY (id)
);   
```
   * edit database connect settings in file **src/main/resources/hibernate.cfg.xml**
```xml   
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE hibernate-configuration PUBLIC
        "-//Hibernate/Hibernate Configuration DTD 3.0//EN"
        "http://www.hibernate.org/dtd/hibernate-configuration-3.0.dtd">
<hibernate-configuration>
    <session-factory>
        <property name="connection.driver_class">org.postgresql.Driver</property>
        <property name="connection.password">654321</property>
        <property name="connection.url">jdbc:postgresql://localhost:5432/rssagregator</property>
        <property name="connection.username">postgres</property>
        <property name="dialect">org.hibernate.dialect.PostgreSQLDialect</property>
        <property name="hbm2ddl.auto">update</property>
        <property name="databasename">rssagregator</property>
        <property name="connection.schemaname">public</property>
        <property name="current_session_context_class">thread</property>
        
        <mapping class="com.sternkn.testtasks.rss.domain.RssFeed" />
		<mapping class="com.sternkn.testtasks.rss.domain.RssNew" />
    </session-factory>
</hibernate-configuration>   
```   
   * run maven command from folder where you unpacked archive

```bash
   mvn package
```
	
   * copy RssAggregator.war file from **target** folder (it maven created) to you **webapps** folder in Tomcat installation folder
   * open in browser url [http://localhost:8080/RssAggregator](http://localhost:8080/RssAggregator)

### Application features
   * using annotations for modeling relationship *one to many*
   * using custom annotation for validation the rss url
   * using Ajax for deleting and updating the rss news list
   
   
![Feeds list page](/img/feeds-page.png)

![Add new feed page](/img/add-feed-page.png)

![News list page](/img/news-page.png)

> Sorry for my english.
package com.sternkn.testtasks.rss.domain;

import java.util.Set;
import java.util.HashSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.OneToMany;

import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;

@Entity
@Table(name = "rss_feeds")
public class RssFeed
{
	@Id
    @Column(name = "id")
    @GeneratedValue
    private Integer id;

	@Size(min=1, max=49)
    @Column(name = "name")
    private String name;

	@URL
	@Size(min=4, max=249) 
    @Column(name = "url")
    private String url;

    
    @OneToMany(mappedBy="rssFeed")
    private Set<RssNew> rssNews = new HashSet<RssNew>();
    
    public RssFeed(){
    }
    
    public void setId(Integer id){
    	this.id = id;
    }
    
    public Integer getId(){
    	return id;
    }
    
    public void setName(String name){
    	this.name = name;
    }
    
    public String getName(){
    	return name;
    }
    
    public void setUrl(String url){
    	this.url = url;
    }
    
    public String getUrl(){
    	return url;
    }
    
    public void setRssNews(Set<RssNew> rssNews){
    	this.rssNews = rssNews;
    }
    
    public Set<RssNew> getRssNews(){
    	return rssNews;
    }
    
    @Override
    public String toString(){
    	return "RssFeed[id = " + id + ", name = " + name + " , url = " + url + "]";
    }
}

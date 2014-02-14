package com.sternkn.testtasks.rss.domain;

import java.util.Set;
import java.util.HashSet;

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
    @GeneratedValue
    private Integer id;

	@Size(min=1, max=50)
    private String name;

	@URL
	@Size(min=4, max=250) 
    private String url;

    
    @OneToMany(mappedBy="rssFeed")
    private Set<RssNew> rssNews = new HashSet<RssNew>();
    
    public RssFeed()
    {
    	id   = 0;
    	name = "";
    	url  = "";
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
    public boolean equals(Object otherObject)
    {
    	if(this == otherObject) return true;
    	if(otherObject == null) return false;
    	
    	if(getClass() != otherObject.getClass()){
    		return false;
    	}
    	
    	RssFeed other = (RssFeed)otherObject;
    	
    	return name.equals(other.getName()) &&
    		   url.equals(other.getUrl());	
    }
    
    @Override
    public int hashCode(){
    	return name.hashCode() + 7 * url.hashCode();
    }
    
    @Override
    public String toString(){
    	return "RssFeed[id = " + id + ", name = " + name + " , url = " + url + "]";
    }
}

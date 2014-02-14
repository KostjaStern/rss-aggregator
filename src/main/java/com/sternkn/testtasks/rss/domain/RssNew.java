package com.sternkn.testtasks.rss.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "rss_news")
public class RssNew
{
	@Id
    @GeneratedValue
    private Integer id;

    private String title;

    private String link;

    
    @ManyToOne
    @JoinColumn(name="feed_id")
    private RssFeed rssFeed;
    
    
    public RssNew()
    {
    	id      = 0;
    	title   = "";
    	link    = "";
    	rssFeed = null;
    }
    
    public void setId(Integer id){
    	this.id = id;
    }
    
    public Integer getId(){
    	return id;
    }
   
    public void setTitle(String title){
    	this.title = title;
    }
    
    public String getTitle(){
    	return title;
    }
    
    public void setLink(String link){
    	this.link = link;
    }
    
    public String getLink(){
    	return link;
    }

    
    public void setRssFeed(RssFeed rssFeed){
    	this.rssFeed = rssFeed;
    }
    
    public RssFeed getRssFeed(){
    	return rssFeed;
    }
    
    
    @Override
    public boolean equals(Object otherObject)
    {
    	if(this == otherObject) return true;
    	if(otherObject == null) return false;
    	
    	if(getClass() != otherObject.getClass()){
    		return false;
    	}
    	
    	RssNew other = (RssNew)otherObject;
    	
    	boolean isEqualFeed = false;
    	if((rssFeed == null && other.getRssFeed() == null) || rssFeed.equals(other)){
    		isEqualFeed = true;
    	}
    	
    	return title.equals(other.getTitle()) &&
    		   link.equals(other.getLink()) &&
    		   isEqualFeed;	
    }
    
    @Override
    public int hashCode()
    {
    	int feedCode;
    	if(rssFeed == null){
    		feedCode = 0;
    	}
    	else {
    		feedCode = rssFeed.hashCode(); 
    	}
    	
    	return title.hashCode() + 7 * link.hashCode() + 11 * feedCode;
    }
    
    @Override
    public String toString(){
    	return "RssNew[id = " + id + ", title = " + title + " , link = " + link + " , rssFeed = " + rssFeed + "]";
    }
}

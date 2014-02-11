package com.sternkn.testtasks.rss.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
//import javax.persistence.ManyToOne;
//import javax.persistence.FetchType;
//import javax.persistence.JoinColumn;

@Entity
@Table(name = "rss_news")
public class RssNew
{
	@Id
    @Column(name = "id")
    @GeneratedValue
    private Integer id;

	@Column(name = "feed_id")
	private Integer feedId;
	
    @Column(name = "title")
    private String title;

    @Column(name = "link")
    private String link;

/*    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="feed_id")
    private RssFeed rssFeed;
*/    
    
    public RssNew(){
    }
    
    public void setId(Integer id){
    	this.id = id;
    }
    
    public Integer getId(){
    	return id;
    }
   
    public void setFeedId(Integer feedId){
    	this.feedId = feedId;
    }
    
    public Integer getFeedId(){
    	return feedId;
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

/*    
    public String getNewFeedName(){
    	return rssFeed.getName();
    }
*/    
}

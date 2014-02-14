package com.sternkn.testtasks.rss.dao;

import java.util.List;
import com.sternkn.testtasks.rss.domain.RssFeed;

public interface RssFeedDAO
{
	void addRssFeed(RssFeed rssFeed);

    List<RssFeed> listRssFeeds();

    void removeRssFeed(Integer id);
    
    void close();
}

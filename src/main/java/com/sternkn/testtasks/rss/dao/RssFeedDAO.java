package com.sternkn.testtasks.rss.dao;

import java.util.List;

import com.sternkn.testtasks.rss.domain.RssFeed;

public interface RssFeedDAO
{
	int addRssFeed(RssFeed rssFeed) throws DbException;

    List<RssFeed> listRssFeeds() throws DbException;

    void removeRssFeed(Integer id) throws DbException;
    
    void close() throws DbException;
}

package com.sternkn.testtasks.rss.dao;

import java.util.List;

import com.sternkn.testtasks.rss.domain.RssNew;


public interface RssNewDAO
{
	int addRssNew(RssNew rssNew) throws DbException;

    List<RssNew> listRssNews() throws DbException;

    void removeRssNew(Integer id) throws DbException;
    
    void removeAllNews() throws DbException;
    
    void close() throws DbException;
}

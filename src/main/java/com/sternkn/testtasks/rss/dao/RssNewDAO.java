package com.sternkn.testtasks.rss.dao;

import java.util.List;
import com.sternkn.testtasks.rss.domain.RssNew;


public interface RssNewDAO
{
	void addRssNew(RssNew rssNew);

    List<RssNew> listRssNew();

    void removeRssNew(Integer id);
}

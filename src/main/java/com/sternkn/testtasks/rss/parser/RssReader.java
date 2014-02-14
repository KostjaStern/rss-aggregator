package com.sternkn.testtasks.rss.parser;

import java.util.List;

import com.sternkn.testtasks.rss.domain.RssNew;


public interface RssReader
{
    List<RssNew> readNews() throws RssReadException;
}

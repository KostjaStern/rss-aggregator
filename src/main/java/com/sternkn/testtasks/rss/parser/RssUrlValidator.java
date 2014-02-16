package com.sternkn.testtasks.rss.parser;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.sternkn.testtasks.rss.domain.RssFeed;

public class RssUrlValidator implements ConstraintValidator<RssUrl, String>
{
	@Override
	public void initialize(RssUrl String) { }

	@Override
	public boolean isValid(String rssUrlField, ConstraintValidatorContext cxt) 
	{
		if(rssUrlField == null) {
			return false;
		}
		
		
		RssFeed rssFeed = new RssFeed();
		rssFeed.setUrl(rssUrlField);
		
		try
		{
			RssReader reader = new RssReaderImpl(rssFeed);
			reader.readNews();
			
			return true;
		}
		catch (RssReadException e){
			return false;
		}
	}
}

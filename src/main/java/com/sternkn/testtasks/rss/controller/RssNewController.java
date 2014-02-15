package com.sternkn.testtasks.rss.controller;

import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;

import org.json.JSONObject;
import org.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sternkn.testtasks.rss.dao.RssFeedDAO;
import com.sternkn.testtasks.rss.dao.RssNewDAO;
import com.sternkn.testtasks.rss.dao.RssFeedDAOImpl;
import com.sternkn.testtasks.rss.dao.RssNewDAOImpl;
import com.sternkn.testtasks.rss.dao.DbException;
import com.sternkn.testtasks.rss.domain.RssFeed;
import com.sternkn.testtasks.rss.domain.RssNew;
import com.sternkn.testtasks.rss.parser.*;

@Controller
@RequestMapping("/news")
public class RssNewController
{
	@RequestMapping(method = RequestMethod.GET)
	public String newsPage(ModelMap model) 
	{
        System.out.println("newsPage ...");
		// model.addAttribute("message", "... news");
     
/*        
        try
        {
            RssReader rssReader = new RssReaderImpl("http://www.javaworld.com/howto/index.rss"); 
            List<RssNew> news = rssReader.readNews();
            
            for(RssNew rnew: news)
            {
            	System.out.println("title = " + rnew.getTitle());
            	System.out.println("link = " + rnew.getLink() + "\n");
            }
        }
        catch (RssReadException e){
        	e.printStackTrace();
        }
*/        
        
        RssNewDAOImpl rssNewDAOImpl = null;
        List<RssNew>  rssNews = null;
        String message = "";
        try
        {
	        try
	        {
	        	rssNewDAOImpl = new RssNewDAOImpl(); 
	            rssNews = rssNewDAOImpl.listRssNews();
	        }
	        finally
	        {
	        	if(rssNewDAOImpl != null){
	        		rssNewDAOImpl.close();
	        	}
	        }
        }
        catch (DbException e){
        	message = e.getMessage();
        	rssNews = new LinkedList<RssNew>(); 
        }
        
        model.addAttribute("activeMenu", 3);
        model.addAttribute("rssNews", rssNews);
        model.addAttribute("pageName", "News");
        model.addAttribute("message", message);
        
		// Spring uses InternalResourceViewResolver and return back news.jsp
		return "news";
	}
	
	@RequestMapping(value="/delete-all", method=RequestMethod.POST, produces="application/json; charset=utf-8")
	@ResponseBody
	public String delAllNews(ModelMap model)
	{
		System.out.println("delAllNews ...");
		int code = 1;
		String message = "All news successfully deleted.";
		
		RssNewDAOImpl rssNewDAOImpl = null;
        try
        {
	        try
	        {
	        	rssNewDAOImpl = new RssNewDAOImpl(); 
	            rssNewDAOImpl.removeAllNews();
	        }
	        finally
	        {
	        	if(rssNewDAOImpl != null){
	        		rssNewDAOImpl.close();
	        	}
	        }
        }
        catch (DbException e){
        	code = 0;
        	message = e.getMessage();
        }
		
		JSONObject json = new JSONObject();
		json.put("code", code);
		json.put("message", message);
		return json.toString(); 
	}
	
	
	@RequestMapping(value="/get-news", method=RequestMethod.POST, produces="application/json; charset=utf-8")
	@ResponseBody
	public String getAllNews(ModelMap model)
	{
		System.out.println("getAllNews ...");
		
		RssFeedDAO feedDAO = null;
		List<RssFeed> rssFeeds = null;
		int code = 1;
		String message = "The list of news is updated.";
		
		// get all exist rss feeds
		try
		{
			try
			{
				feedDAO = new RssFeedDAOImpl(); 
				rssFeeds = feedDAO.listRssFeeds();
			}
			finally
			{
				if(feedDAO != null){
					feedDAO.close();
				}
			}
		}
		catch (DbException e){
			code    = 0;
			message = e.getMessage();
			rssFeeds = new LinkedList<RssFeed>();
		}
		
		
		List<RssNew> rssNews = new ArrayList<RssNew>();

		// get the last rss news
		for(RssFeed rssFeed: rssFeeds)
		{
			try
	        {
	            RssReader rssReader = new RssReaderImpl(rssFeed); 
	            List<RssNew> news = rssReader.readNews();
	            rssNews.addAll(news);
	        }
	        catch (RssReadException e){
	        	e.printStackTrace();
	        	message = e.getMessage();
	        }	
		}

		System.out.println("rssNews.size() = " + rssNews.size());
		
		RssNewDAO    newDAO  = null;
		List<RssNew> oldNews = null;
		try
		{
			try
			{
				newDAO = new RssNewDAOImpl(); 
				oldNews = newDAO.listRssNews();
				
				// add the last rss news to database, update ID for the new item in list 
				for(RssNew rssNew: rssNews)
				{
					int index = oldNews.indexOf(rssNew);
					if(index == -1){
						newDAO.addRssNew(rssNew);
					}
					else {
						rssNew.setId(oldNews.get(index).getId());
					}
				}
				
				// add the old news to list
				for(RssNew rssNew: oldNews)
				{
					if(!rssNews.contains(rssNew)){
						rssNews.add(rssNew);
					}
				}
			}
			finally
			{
				if(newDAO != null){
					newDAO.close();
				}
			}
		}
		catch (DbException e){
			message = e.getMessage();
		}
		
		JSONObject json = new JSONObject();
		json.put("code", code);
		json.put("message", message);
		
		JSONArray arrNews = new JSONArray(); 
		
		for(RssNew rssNew: rssNews)
        {
			JSONObject jsonNew = new JSONObject();
			jsonNew.put("newId", rssNew.getId());
			jsonNew.put("newTitle", rssNew.getTitle());
			jsonNew.put("newLink", rssNew.getLink());
			jsonNew.put("newFeedName", rssNew.getRssFeed().getName());
			arrNews.put(jsonNew);
        }
		json.put("news", arrNews);
		
		return json.toString();
	}
}

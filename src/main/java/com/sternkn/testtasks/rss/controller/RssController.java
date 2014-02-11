package com.sternkn.testtasks.rss.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sternkn.testtasks.rss.domain.RssNew;
import com.sternkn.testtasks.rss.dao.RssNewDAOImpl;

@Controller
@RequestMapping("/")
public class RssController
{
	@RequestMapping(value="/", method = RequestMethod.GET)
	public String homePage(ModelMap model) 
	{
        System.out.println("call homePage(...)");
        
        
//        RssNew rssNew = new RssNew();
//        rssNew.setFeedId(1);
//        rssNew.setTitle("test title");
//        rssNew.setLink("http://habrahabr.ru/post/111102/");
        RssNewDAOImpl rssNewDAOImpl = new RssNewDAOImpl(); 
        List<RssNew> rssNews = rssNewDAOImpl.listRssNew();
        System.out.println("rssNews.size() = " + rssNews.size());
        for(RssNew rssNew: rssNews)
        {
        	System.out.println("id = " + rssNew.getId() + " , title = " + rssNew.getTitle());
        }
        
		model.addAttribute("message", "Maven Web Project + Spring 3 MVC - welcome()");
 
		//Spring uses InternalResourceViewResolver and return back index.jsp
		return "index";
	}
	
	
	@RequestMapping(value="/news", method = RequestMethod.GET)
	public String newsPage(ModelMap model) 
	{
        System.out.println("call newsPage(...)");
		model.addAttribute("message", "... news");
 
		//Spring uses InternalResourceViewResolver and return back index.jsp
		return "news";
	}
	
}

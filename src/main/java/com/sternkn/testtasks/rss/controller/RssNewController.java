package com.sternkn.testtasks.rss.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sternkn.testtasks.rss.dao.RssNewDAOImpl;
import com.sternkn.testtasks.rss.domain.RssNew;


@Controller
@RequestMapping("/news")
public class RssNewController
{
	@RequestMapping(method = RequestMethod.GET)
	public String newsPage(ModelMap model) 
	{
        System.out.println("newsPage ...");
		// model.addAttribute("message", "... news");
        
        RssNewDAOImpl rssNewDAOImpl = new RssNewDAOImpl(); 
        List<RssNew>  rssNews = rssNewDAOImpl.listRssNew();
        
        model.addAttribute("activeMenu", 3);
        model.addAttribute("rssNews", rssNews);
        
		//Spring uses InternalResourceViewResolver and return back index.jsp
		return "news";
	}
}

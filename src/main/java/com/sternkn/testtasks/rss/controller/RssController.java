package com.sternkn.testtasks.rss.controller;

// import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

// import com.sternkn.testtasks.rss.domain.RssNew;
// import com.sternkn.testtasks.rss.domain.RssFeed;
// import com.sternkn.testtasks.rss.dao.RssNewDAOImpl;

@Controller
// @RequestMapping("/")
public class RssController
{
	@RequestMapping(value="/", method = RequestMethod.GET)
	public String homePage(ModelMap model) 
	{
        System.out.println("homePage ...");
        
/*        
        // add rss new 
        RssFeed rssFeed = new RssFeed();
        rssFeed.setId(2);
        
        RssNew rssNew = new RssNew();
        rssNew.setRssFeed(rssFeed);
        rssNew.setTitle("test title s");
        rssNew.setLink("http://www.javaworld.com/article/2092353/core-java/httpclient-basic-authentication.html#tk.rss_corejava");
        
        RssNewDAOImpl rssNewDAOImpl = new RssNewDAOImpl();
        rssNewDAOImpl.addRssNew(rssNew);
*/
        
/*
        // get list news 
        RssNewDAOImpl rssNewDAOImpl = new RssNewDAOImpl(); 
        List<RssNew> rssNews = rssNewDAOImpl.listRssNew();
        System.out.println("rssNews.size() = " + rssNews.size());
        for(RssNew rssNew: rssNews)
        {
        	System.out.println("id = " + rssNew.getId() + " , title = " + rssNew.getTitle() + " , feedName = " + rssNew.getRssFeed().getName());
        }
*/       

//        model.addAttribute("activeMenu", 1);
//		model.addAttribute("message", "Maven Web Project + Spring 3 MVC - welcome()");
 

		return "redirect:/feed/list";
	}
	
}

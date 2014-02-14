package com.sternkn.testtasks.rss.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import com.sternkn.testtasks.rss.domain.RssFeed;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.sternkn.testtasks.rss.dao.RssFeedDAO;
import com.sternkn.testtasks.rss.dao.RssFeedDAOImpl;
import org.json.JSONObject;


@Controller
@RequestMapping("/feed")
public class RssFeedController
{
	
	@RequestMapping(value="/add", method = RequestMethod.GET)
	public String addFeedPage(ModelMap model) 
	{
		System.out.println("addFeedPage ...");
		
		model.addAttribute("rssFeed", new RssFeed());
		model.addAttribute("message", "");
		model.addAttribute("activeMenu", 2);
		return "addfeed";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String submitFeedForm(@Valid @ModelAttribute RssFeed rssFeed, BindingResult result, ModelMap model)
	{
		System.out.println("submitFeedForm ...");
		model.addAttribute("activeMenu", 2);
		
		if(result.hasErrors()) {
			model.addAttribute("message", "");
			return "addfeed";
		}
		
		System.out.println("rssFeed = " + rssFeed);
		
		RssFeedDAO feedDAO = new RssFeedDAOImpl(); 
		feedDAO.addRssFeed(rssFeed);
		feedDAO.close();
		
		model.addAttribute("rssFeed", new RssFeed());
		model.addAttribute("message", "Feed was successfully added.");
		return "addfeed";
	}
	
	
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public String listFeedsPage(ModelMap model) 
	{
		System.out.println("listFeedsPage ...");

		RssFeedDAO feedDAO = new RssFeedDAOImpl(); 
		List<RssFeed> rssFeeds = feedDAO.listRssFeeds();
		feedDAO.close();
		
		model.addAttribute("rssFeeds", rssFeeds);
		model.addAttribute("activeMenu", 1);
		return "feeds";
	}
	
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.POST, produces="application/json")
	@ResponseBody
    public String deleteFeed(@PathVariable Integer id, ModelMap model)
    {
		System.out.println("id = " + id);
		
		// TODO add custom DbException
		RssFeedDAO feedDAO = new RssFeedDAOImpl(); 
		feedDAO.removeRssFeed(id);
		feedDAO.close();
		
		JSONObject json = new JSONObject();
		json.put("code", 1);
		json.put("message", "ok");
    	return json.toString();
    }
}

package com.sternkn.testtasks.rss.controller;

import java.util.List;
import java.util.LinkedList;

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
import com.sternkn.testtasks.rss.dao.DbException;

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
		model.addAttribute("pageName", "Add Feed");
		model.addAttribute("activeMenu", 2);
		return "addfeed";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String submitFeedForm(@Valid @ModelAttribute RssFeed rssFeed, BindingResult result, ModelMap model)
	{
		// System.out.println("submitFeedForm ...");
		model.addAttribute("activeMenu", 2);
		model.addAttribute("pageName", "Add Feed");
		String message = "Error add feed.";
		
		if(result.hasErrors()) {
			model.addAttribute("message", message);
			return "addfeed";
		}
		
		// System.out.println("rssFeed = " + rssFeed);
		
		RssFeedDAO feedDAO = null;
		message = "Feed was successfully added."; 
		try
		{
			try
			{
				feedDAO = new RssFeedDAOImpl(); 
				int feedID = feedDAO.addRssFeed(rssFeed);
				System.out.println("feedID = " + feedID);
			}
			finally
			{
				if(feedDAO != null){
					feedDAO.close();
				}
			}
		}
		catch (DbException e){
			message = e.getMessage();
		}

		
		model.addAttribute("rssFeed", new RssFeed());
		model.addAttribute("message", message);
		return "addfeed";
	}
	
	
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public String listFeedsPage(ModelMap model) 
	{
		System.out.println("listFeedsPage ...");

		RssFeedDAO feedDAO = null;
		List<RssFeed> rssFeeds = null;
		String message = "";
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
			message = e.getMessage();
			rssFeeds = new LinkedList<RssFeed>();
		}
		
		System.out.println("rssFeeds.size() = " + rssFeeds.size());
		
		
		model.addAttribute("message", message);
		model.addAttribute("rssFeeds", rssFeeds);
		model.addAttribute("pageName", "Feeds");
		model.addAttribute("activeMenu", 1);
		return "feeds";
	}
	
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.POST, produces="application/json; charset=utf-8")
	@ResponseBody
    public String deleteFeed(@PathVariable Integer id, ModelMap model)
    {
		System.out.println("id = " + id);
		
		RssFeedDAO feedDAO = null;
		int code = 1;
		String message = "ok";
		try
		{
			try
			{
				feedDAO = new RssFeedDAOImpl(); 
				feedDAO.removeRssFeed(id);
			}
			finally
			{
				feedDAO.close();
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
}

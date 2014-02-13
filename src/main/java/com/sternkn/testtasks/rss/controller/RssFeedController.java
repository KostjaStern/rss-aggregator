package com.sternkn.testtasks.rss.controller;

// import java.util.List;
import java.util.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import com.sternkn.testtasks.rss.domain.RssFeed;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
// import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/feed")
public class RssFeedController
{
	
	@RequestMapping(value="/add", method = RequestMethod.GET)
	public String addFeedPage(ModelMap model) 
	{
		System.out.println("addFeedPage ...");
		
		model.addAttribute("rssFeed", new RssFeed());
		model.addAttribute("activeMenu", 2);
		return "addfeed";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String submitFeedForm(@Valid @ModelAttribute("rssFeed") RssFeed rssFeed, BindingResult result, ModelMap model)
	{
		model.addAttribute("activeMenu", 2);
		
		if(result.hasErrors()) {
			return "addfeed";
		}
		
		System.out.println("rssFeed = " + rssFeed);
		
		return "addfeed";
	}
	
	
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public String listFeedsPage(ModelMap model) 
	{
		System.out.println("listFeedsPage ...");
		
		model.addAttribute("activeMenu", 1);
		return "feeds";
	}
	
	
	
/*	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String submitFeedForm(HttpServletRequest request) 
	{
		System.out.println("submitFeedForm ...");
		
		String feedName = request.getParameter("feedName");
		String feedUrl  = request.getParameter("feedUrl");
		
		System.out.println("feedName = " + feedName);
		System.out.println("feedUrl = " + feedUrl);
		
		return "addfeed";
	}
*/	
}

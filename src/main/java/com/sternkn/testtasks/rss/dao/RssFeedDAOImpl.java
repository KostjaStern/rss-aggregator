package com.sternkn.testtasks.rss.dao;

import java.util.List;
import java.util.LinkedList;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.sternkn.testtasks.rss.domain.RssFeed;


public class RssFeedDAOImpl implements RssFeedDAO 
{
	private SessionFactory sessionFactory;
	
	public RssFeedDAOImpl()
	{
		try
		{
	        Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
	
	        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
	        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		}
		catch (HibernateException e){
			// TODO add to LOG
		}
	}
	
	@Override
	public void addRssFeed(RssFeed rssFeed)
	{
		try
		{
			Session session = sessionFactory.getCurrentSession();
		    Transaction tr = session.getTransaction();
		    
		    tr.begin();
		    session.save(rssFeed);
		    tr.commit();
		    
		    session.close();
		} 
		catch (HibernateException e){
			// TODO add to LOG
		}		
	}
	
	@Override
	public List<RssFeed> listRssFeeds()
	{
		List<RssFeed> rssFeeds = new LinkedList<RssFeed>();
		
		try
		{
			Session session = sessionFactory.getCurrentSession();
		    Transaction tr = session.getTransaction();
		    
		    tr.begin();
		    rssFeeds = session.createQuery("from RssFeed").list();
		    tr.commit();
			
		    session.close();
		}
		catch (HibernateException e){
			// TODO add to LOG
		}
		
		return rssFeeds;
	}
	
	@Override
	public void removeRssFeed(Integer id)
	{
		try
		{
			Session session = sessionFactory.getCurrentSession();
		    Transaction tr = session.getTransaction();
		    
		    tr.begin();
		    RssFeed rssFeed = (RssFeed)session.load(RssFeed.class, id);
	        if (null != rssFeed) {
	        	session.delete(rssFeed);
	        }
		    tr.commit();
			
		    session.close();
		}
		catch (HibernateException e){
			// TODO add to LOG
		}		
	}
	
	@Override
	public void close()
	{
		if(sessionFactory != null){
		    sessionFactory.close();
		}		
	}
	
}

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
	
	public RssFeedDAOImpl() throws DbException
	{
		try
		{
	        Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
	
	        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
	        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		}
		catch (HibernateException e){
			// TODO add to LOG
			throw new DbException(e.getMessage());
		}
	}
	
	@Override
	public int addRssFeed(RssFeed rssFeed) throws DbException
	{
		int feedID = 0;
		try
		{
			Session session = sessionFactory.openSession();
		    Transaction tr = session.getTransaction();
		    
		    tr.begin();
		    session.save(rssFeed);
		    session.flush();
		    feedID = rssFeed.getId();
		    tr.commit();
		    
		    session.close();
		} 
		catch (HibernateException e){
			// TODO add to LOG
			throw new DbException(e.getMessage());
		}		
		
		return feedID;
	}
	
	@Override
	public List<RssFeed> listRssFeeds() throws DbException
	{
		List<RssFeed> rssFeeds = new LinkedList<RssFeed>();
		
		try
		{
			Session session = sessionFactory.openSession();
		    Transaction tr = session.getTransaction();
		    
		    tr.begin();
		    rssFeeds = session.createQuery("from RssFeed").list();
		    tr.commit();
			
		    session.close();
		}
		catch (HibernateException e){
			// TODO add to LOG
			throw new DbException(e.getMessage());
		}
		
		return rssFeeds;
	}
	
	@Override
	public void removeRssFeed(Integer id) throws DbException
	{
		try
		{
			Session session = sessionFactory.openSession();
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
			throw new DbException(e.getMessage());
		}		
	}
	
	@Override
	public void close() throws DbException
	{
		try
		{
			if(sessionFactory != null){
			    sessionFactory.close();
			}
		}
		catch (HibernateException e){
			// TODO add to LOG
			throw new DbException(e.getMessage());
		}
	}
	
}

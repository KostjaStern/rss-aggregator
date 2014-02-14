package com.sternkn.testtasks.rss.dao;

import java.util.List;
import java.util.LinkedList;

import org.hibernate.SessionFactory;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;


// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.transaction.annotation.Transactional;

import org.springframework.stereotype.Repository;

import com.sternkn.testtasks.rss.domain.RssNew;

@Repository
public class RssNewDAOImpl implements RssNewDAO 
{
	// @Autowired    TODO why @Autowired not works ?
    private SessionFactory sessionFactory;
	
	public RssNewDAOImpl()
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
	public void addRssNew(RssNew rssNew) 
	{
		try
		{
			Session session = sessionFactory.getCurrentSession();
		    Transaction tr = session.getTransaction();
		    
		    tr.begin();
		    session.save(rssNew);
		    tr.commit();
		    
		    session.close();
		} 
		catch (HibernateException e){
			// TODO add to LOG
		}
    }

	// @Transactional  TODO why @Transactional not works ? 
	@Override
    @SuppressWarnings("unchecked")
    public List<RssNew> listRssNews() 
    {
		List<RssNew> rssNews = new LinkedList<RssNew>();
		
		try
		{
			Session session = sessionFactory.getCurrentSession();
		    Transaction tr = session.getTransaction();
		    
		    tr.begin();
		    rssNews = session.createQuery("from RssNew").list();
		    tr.commit();
			
		    session.close();
		}
		catch (HibernateException e){
			// TODO add to LOG
		}
		
        return rssNews;
    }

	// @Transactional
	@Override
    public void removeRssNew(Integer id) 
    {
		try
		{
			Session session = sessionFactory.getCurrentSession();
		    Transaction tr = session.getTransaction();
		    
		    tr.begin();
		    RssNew rssNew = (RssNew)session.load(RssNew.class, id);
	        if (null != rssNew) {
	        	session.delete(rssNew);
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

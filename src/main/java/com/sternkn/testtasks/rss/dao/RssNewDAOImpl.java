package com.sternkn.testtasks.rss.dao;

import java.util.List;

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
	// @Autowired
    private SessionFactory sessionFactory;
	
	public RssNewDAOImpl()
	{
		
        try
        {
            Configuration configuration = new Configuration().configure("hibernate.cfg.xml");

            ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        }
        catch (HibernateException he)
        {
            he.printStackTrace();
        }
        
	}
	

	@Override
	public void addRssNew(RssNew rssNew) 
	{
		System.out.println("addRssNew: sessionFactory = " + sessionFactory);
		
		Session session = sessionFactory.getCurrentSession();
	    Transaction tr = session.getTransaction();
	    
	    tr.begin();
	    session.save(rssNew);
	    tr.commit();
    }

	// @Transactional
	@Override
    @SuppressWarnings("unchecked")
    public List<RssNew> listRssNew() 
    {
		System.out.println("listRssNew: sessionFactory = " + sessionFactory);
		Session session = sessionFactory.getCurrentSession();
	    Transaction tr = session.getTransaction();
	    
	    tr.begin();
	    List<RssNew> rssNews = session.createQuery("from RssNew").list();
	    tr.commit();
		
        return rssNews;
    }

	// @Transactional
	@Override
    public void removeRssNew(Integer id) 
    {
		RssNew rssNew = (RssNew) sessionFactory.getCurrentSession().load(RssNew.class, id);
        if (null != rssNew) {
            sessionFactory.getCurrentSession().delete(rssNew);
        }
    }    
}

package com.flyaway.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.flyaway.model.sourceLocations;

public class sourceOps 
{
	public static String checkSourceId(String srcId)
	{
		String isPresent = null;
		List<sourceLocations> alist = null;
		try 
		{
	        SessionFactory factory = hibernateUtil.getSessionFactory();
	        
	        Session session = factory.openSession();
	        
	        Query aquery = session.createQuery("from sourceLocations s where s.srcId = :sId");
	        aquery.setParameter("sId", srcId);
	        alist = (List<sourceLocations>)aquery.list();
	        if(alist.isEmpty())
	        {
	        	isPresent = "false";
	        }
	        else
	        {
	        	isPresent = "true";
	        }
		}
		catch(Exception e)
		{
			isPresent = "error";
			System.out.println(e.getMessage());
		}
		
		return isPresent;
		
	}

	public static String checkSourceName(String srcname)
	{
		String isPresent = null;
		List<sourceLocations> alist = null;
		try 
		{
	        SessionFactory factory = hibernateUtil.getSessionFactory();
	        
	        Session session = factory.openSession();
	        
	        Query aquery = session.createQuery("from sourceLocations s where s.srcname = :sname");
	        aquery.setParameter("sname", srcname);
	        alist = (List<sourceLocations>)aquery.list();
	        if(alist.isEmpty())
	        {
	        	isPresent = "false";
	        }
	        else
	        {
	        	isPresent = "true";
	        }
		}
		catch(Exception e)
		{
			isPresent = "error";
			System.out.println(e.getMessage());
		}
		
		return isPresent;
		
	}

	public static boolean addSrc(String sId,String sname)
	{
		boolean isadded = false;
		try 
		{
	        SessionFactory factory = hibernateUtil.getSessionFactory();
	        
	        Session session = factory.openSession();
	        
	        Transaction tx = session.beginTransaction();

	        sourceLocations src = new sourceLocations();
	        src.setSrcId(sId);
	        src.setSrcname(sname);
	        session.save(src);
	        tx.commit();
	        session.close();
	        isadded = true;
		}
		catch(Exception e)
		{
			isadded = false;
			System.out.println(e.getMessage());	
		}
		return isadded;
	}
}

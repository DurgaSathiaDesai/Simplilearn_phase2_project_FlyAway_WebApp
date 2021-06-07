package com.flyaway.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.flyaway.model.destnLocations;
import com.flyaway.model.sourceLocations;

public class destinationOps 
{
	public static String checkDestnId(String destnId)
	{
		String isPresent = null;
		List<destnLocations> alist = null;
		try 
		{
	        SessionFactory factory = hibernateUtil.getSessionFactory();
	        
	        Session session = factory.openSession();
	        
	        Query aquery = session.createQuery("from destnLocations d where d.destnId = :dId");
	        aquery.setParameter("dId", destnId);
	        alist = (List<destnLocations>)aquery.list();
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
	
	public static String checkDestnName(String dname)
	{
		String isPresent = null;
		List<destnLocations> alist = null;
		try 
		{
	        SessionFactory factory = hibernateUtil.getSessionFactory();
	        
	        Session session = factory.openSession();
	        
	        Query aquery = session.createQuery("from destnLocations d where d.destnname = :dname");
	        aquery.setParameter("dname", dname);
	        alist = (List<destnLocations>)aquery.list();
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
	
	public static boolean addDestn(String dId,String dname)
	{
		boolean isadded = false;
		try 
		{
	        SessionFactory factory = hibernateUtil.getSessionFactory();
	        
	        Session session = factory.openSession();
	        
	        Transaction tx = session.beginTransaction();

	        destnLocations dest = new destnLocations();
	        dest.setDestnId(dId);
	        dest.setDestnname(dname);
	        session.save(dest);
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

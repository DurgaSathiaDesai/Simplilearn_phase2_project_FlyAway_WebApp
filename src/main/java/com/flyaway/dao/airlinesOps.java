package com.flyaway.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.flyaway.model.airlines;
import com.flyaway.model.flightInfo;

public class airlinesOps 
{
	public static String checkAirlineId(String aId)
    {
    	//boolean isPresent = false;
		String isPresent = null;
    	List<airlines> alist = null;
    	try 
    	{
            SessionFactory factory = hibernateUtil.getSessionFactory();
            
            Session session = factory.openSession();
            
            Query aquery = session.createQuery("from airlines a where a.airlineId = :aId");
            aquery.setParameter("aId", aId);
            alist = (List<airlines>)aquery.list();
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
    
    public static boolean addAirlines(String aId,String aname,String aStatus)
    {
    	boolean isadded = false;
    	try 
    	{
            SessionFactory factory = hibernateUtil.getSessionFactory();
            
            Session session = factory.openSession();
            
            Transaction tx = session.beginTransaction();

            airlines airline = new airlines();
            airline.setAirlineId(aId);;
            airline.setAirlinename(aname);
            airline.setAirlinestatus(aStatus);
            session.save(airline);
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
    
    public static List<airlines> getAirlinesInService()
    {
    	List<airlines> alist = null;
    	String status = "inservice";
    	try 
    	{
            SessionFactory factory = hibernateUtil.getSessionFactory();
            
            Session session = factory.openSession();
            
            Query aquery = session.createQuery("from airlines a where a.airlinestatus = :aStatus");
            aquery.setParameter("aStatus", status);
            alist = aquery.list();
    	}
    	catch(Exception e)
    	{
    		System.out.println(e.getMessage());
    	}
    	
    	return alist;
    	
    }
    
    public static boolean updateAirlineStatus(String aId,String aStatus)
    {
    	boolean isupdated = false;
    	airlines airline = null;
    	try 
    	{
            SessionFactory factory = hibernateUtil.getSessionFactory();
            
            Session session = factory.openSession();
            
            Transaction tx = session.beginTransaction();
            airline = session.get(airlines.class, aId);
            airline.setAirlinestatus(aStatus);
            session.update(airline);
            tx.commit();
            session.close();
            isupdated = true;
    	}
    	catch(Exception e)
    	{
    		isupdated = false;
    		System.out.println(e.getMessage());	
    	}
    	return isupdated;

    }
}

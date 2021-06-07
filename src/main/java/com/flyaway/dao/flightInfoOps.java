package com.flyaway.dao;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.flyaway.model.airlines;
import com.flyaway.model.destnLocations;
import com.flyaway.model.flightInfo;
import com.flyaway.model.sourceLocations;

public class flightInfoOps 
{
	public static String getFlightInfo(String flightcode,String src,String destn,String tdate)
    {
		
    	flightInfo finfo = null;
    	String isPresent = null;
    	LocalDate trdate = LocalDate.parse(tdate);
    	String status = "scheduled";
    	//int fiId = Integer.parseInt(fId);
    	try {
			SessionFactory factory = hibernateUtil.getSessionFactory();

			Session session = factory.openSession();
			
			Query flightq = session.createQuery("from flightInfo f where f.flightCode.airlineId = :fcode and f.src.srcId = :src and f.destination.destnId = :destn and f.travelDate = :tdate and f.flightStatus = :fstatus");
			flightq.setParameter("fcode", flightcode);
			flightq.setParameter("src", src);
			flightq.setParameter("destn", destn);
			flightq.setParameter("tdate", trdate);
			flightq.setParameter("fstatus", status);
			
			finfo = (flightInfo) flightq.uniqueResult();
		    if(finfo != null)
		    {
		    	isPresent = "true";
		    }
		    else
		    {
		    	isPresent = "false";
		    }
    	}
    	catch(Exception e)
    	{
    		isPresent = "error";
    		System.out.println(e.getMessage());	
    	}
    	return isPresent;
    	
    }
	
	public static boolean addFlightInfo(String flightCode,String src,String destination,String tdate,String atime,String deptime,String totSeats,String availSeats,String price)
	{
		boolean isadded = false;
		LocalDate trdate = LocalDate.parse(tdate);
		int totalSeats = Integer.parseInt(totSeats);
		int aSeats = Integer.parseInt(availSeats);
		double fare = Double.parseDouble(price);
		String status = "scheduled";
		try 
		{
	        SessionFactory factory = hibernateUtil.getSessionFactory();
	        
	        Session session = factory.openSession();
	        
	        Transaction tx = session.beginTransaction();

	        flightInfo finfo = new flightInfo();
	        
	        airlines a = (airlines)session.get(airlines.class, flightCode);
	        //a.setAirlineId(flightCode);
	        finfo.setFlightCode(a);
	        
	        sourceLocations srcloc = (sourceLocations)session.get(sourceLocations.class, src);
	        //srcloc.setSrcId(src);
	        finfo.setSrc(srcloc);
	        
	        destnLocations destnloc = (destnLocations)session.get(destnLocations.class, destination);
	        //destnloc.setDestnId(destination);
	        finfo.setDestination(destnloc);
	       
	        finfo.setTravelDate(trdate);
	        finfo.setArrTime(atime);
	        finfo.setDeptTime(deptime);
	        finfo.setTotalSeats(totalSeats);
	        finfo.setAvailSeats(aSeats);
	        finfo.setPrice(fare);
	        finfo.setFlightStatus(status);
	        session.save(finfo);
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

package com.flyaway.dao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import com.flyaway.model.adminCredentials;
import com.flyaway.model.airlines;
import com.flyaway.model.destnLocations;
import com.flyaway.model.flightBooking;
import com.flyaway.model.flightInfo;
import com.flyaway.model.passengers;
import com.flyaway.model.paymentInfo;
import com.flyaway.model.sourceLocations;

public class hibernateUtil 
{
	private static final SessionFactory sessionFactory;
	 
    static {
            try {
                    StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
                                    .configure("hibernate.cfg.xml").build();
                    Metadata metaData = new MetadataSources(standardRegistry).getMetadataBuilder().build();                        
                    sessionFactory = metaData.getSessionFactoryBuilder().build();
            } catch (Throwable th) {
                    throw new ExceptionInInitializerError(th);
            }
    }

    public static SessionFactory getSessionFactory() {
            return sessionFactory;
    }
    
    public static List<flightInfo> getScheduledFlights()
    {
    	List<flightInfo> flightlist = null;
    	try 
    	{
            SessionFactory factory = hibernateUtil.getSessionFactory();
            
            Session session = factory.openSession();
            
            Query fquery = session.createQuery("from flightInfo");
            
           flightlist = fquery.list();
    	}
    	catch(Exception e)
    	{
    		System.out.println(e.getMessage());
    	}
    	
    	return flightlist;
    	
    }
    
    public static List<sourceLocations> getSource()
    {
    	List<sourceLocations> srclist = null;
    	try 
    	{
            SessionFactory factory = hibernateUtil.getSessionFactory();
            
            Session session = factory.openSession();
            
            Query squery = session.createQuery("from sourceLocations");
            
            srclist = squery.list();
    	}
    	catch(Exception e)
    	{
    		System.out.println(e.getMessage());
    	}
    	
    	return srclist;
    	
    }
    
    public static List<destnLocations> getDestination()
    {
    	List<destnLocations> destnlist = null;
    	try 
    	{
            SessionFactory factory = hibernateUtil.getSessionFactory();
            
            Session session = factory.openSession();
            
            Query dquery = session.createQuery("from destnLocations");
            
            destnlist = dquery.list();
    	}
    	catch(Exception e)
    	{
    		System.out.println(e.getMessage());
    	}
    	
    	return destnlist;
    	
    }
    
    public static List<airlines> getAirlines()
    {
    	List<airlines> alist = null;
    	try 
    	{
            SessionFactory factory = hibernateUtil.getSessionFactory();
            
            Session session = factory.openSession();
            
            Query aquery = session.createQuery("from airlines");
            
            alist = aquery.list();
    	}
    	catch(Exception e)
    	{
    		System.out.println(e.getMessage());
    	}
    	
    	return alist;
    	
    }
    
    public static double getPrice(String fId)
    {
    	double price = 0.0;
    	int fiId = Integer.parseInt(fId);
    	try {
			SessionFactory factory = hibernateUtil.getSessionFactory();

			Session session = factory.openSession();
			
			Query flightq = session.createQuery("Select f.price from flightInfo f where f.finfoId = :fId");
			flightq.setParameter("fId", fiId);
		    price = Double.valueOf(flightq.uniqueResult().toString());
		    System.out.println(price);
    	}
    	catch(Exception e)
    	{
    		System.out.println(e.getMessage());	
    	}
    	return price;
    	
    }
    
    public static flightInfo getFlightInfo(String fId)
    {
    	flightInfo finfo = null;
    	int fiId = Integer.parseInt(fId);
    	try {
			SessionFactory factory = hibernateUtil.getSessionFactory();

			Session session = factory.openSession();
			
			Query flightq = session.createQuery("from flightInfo f where f.finfoId = :fId");
			flightq.setParameter("fId", fiId);
		    finfo = (flightInfo) flightq.uniqueResult();
    	}
    	catch(Exception e)
    	{
    		System.out.println(e.getMessage());	
    	}
    	return finfo;
    	
    }
    
    public static boolean addPayment(String cardNumber, int expMonth,int expYear,String fullName,int cvv)
    {
    	boolean isadded = false;
    	try 
    	{
            SessionFactory factory = hibernateUtil.getSessionFactory();
            
            Session session = factory.openSession();
            
            Transaction tx = session.beginTransaction();

            paymentInfo pinfo = new paymentInfo();
            pinfo.setCardNo(cardNumber);
            pinfo.setExpMonth(expMonth);
            pinfo.setExpYear(expYear);
            pinfo.setFullName(fullName);
            pinfo.setCvv(cvv);
            session.save(pinfo);
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
    
    public static int addBooking(String fname,String lname,String email,String contactNo,int noOfPassengers,flightInfo finfo,String cardNumber, int expMonth,int expYear,String fullName,int cvv,List<String> fnamelist,List<String> lnamelist)
    {
    	//boolean isadded = false;
    	int bookingId = 0;
    	double price = noOfPassengers * finfo.getPrice();
    	 //DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
    	 LocalDateTime now = LocalDateTime.now(); 
    	 String bStatus = "confirmed";
    	try 
    	{
            SessionFactory factory = hibernateUtil.getSessionFactory();
            
            Session session = factory.openSession();
            
            Transaction tx = session.beginTransaction();
            
            flightBooking fbook = new flightBooking();
            fbook.setFlightCode(finfo.getFlightCode().getAirlineId());
            fbook.setSrc(finfo.getSrc().getSrcId());
            fbook.setDestination(finfo.getDestination().getDestnId());
            fbook.setTravelDate(finfo.getTravelDate());
            fbook.setArrTime(finfo.getArrTime());
            fbook.setDeptTime(finfo.getDeptTime());
            fbook.setNoOfPassengers(noOfPassengers);
            fbook.setPrice(price);
            fbook.setfName(fname);
            fbook.setlName(lname);
            fbook.setEmailId(email);
            fbook.setContactNo(contactNo);
            fbook.setBookingDate(now);
            fbook.setBookingStatus(bStatus);
            
            paymentInfo pinfo = new paymentInfo();
            pinfo.setCardNo(cardNumber);
            pinfo.setExpMonth(expMonth);
            pinfo.setExpYear(expYear);
            pinfo.setFullName(fullName);
            pinfo.setCvv(cvv);
            
            fbook.setPinfo(pinfo);
            
            session.save(pinfo);
            session.save(fbook);
            for(int i=0;i<noOfPassengers;i++)
            {
            	passengers pass = new passengers();
            	pass.setFirstName(fnamelist.get(i));
            	pass.setLastName(lnamelist.get(i));
            	pass.setFbook(fbook);
            	session.save(pass);
            }
            bookingId = fbook.getBookingId();
            tx.commit();
            session.close();
            //isadded = true;
    	}
    	
    	catch(Exception e)
    	{
    		//isadded = false;
    		bookingId = 0;
    		System.out.println(e.getMessage());	
    	}
    	return bookingId;

    }
    
    public static boolean updateFlightInfo(String fId,int noOfPassengers)
    {
    	flightInfo finfo = getFlightInfo(fId);
    	int availSeats = finfo.getAvailSeats();
    	int newAvailSeats = availSeats - noOfPassengers;
    	 flightInfo flight = null;
    	 int fiId = Integer.parseInt(fId);
    	boolean isupdated = false;
    	try 
    	{
            SessionFactory factory = hibernateUtil.getSessionFactory();
            
            Session session = factory.openSession();
            
            Transaction tx = session.beginTransaction();

           /* flightInfo flight = new flightInfo();
            flight.setUserName(username);
            flight.setPassword(password);;*/
            flight = session.get(flightInfo.class, fiId);
            flight.setAvailSeats(newAvailSeats);
            session.update(flight);
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
    
    public static flightBooking getBookingInfo(int bookingId)
    {
    	flightBooking fbinfo = null;
    	//int fiId = Integer.parseInt(fId);
    	try {
			SessionFactory factory = hibernateUtil.getSessionFactory();

			Session session = factory.openSession();
			
			Query flightq = session.createQuery("from flightBooking f where f.bookingId = :fbId");
			flightq.setParameter("fbId", bookingId);
			fbinfo = (flightBooking) flightq.uniqueResult();
    	}
    	catch(Exception e)
    	{
    		System.out.println(e.getMessage());	
    	}
    	return fbinfo;
    }

    public static List<flightBooking> getBookingsbyDate(LocalDate tdate)
    {
    	List<flightBooking> fbinfolist = null;
    	//int fiId = Integer.parseInt(fId);
    	try {
    		SessionFactory factory = hibernateUtil.getSessionFactory();

    		Session session = factory.openSession();
    		
    		Query flightq = session.createQuery("from flightBooking f where f.travelDate = :tdate");
    		flightq.setParameter("tdate", tdate);
    		fbinfolist = (List<flightBooking>)flightq.list();
    	}
    	catch(Exception e)
    	{
    		System.out.println(e.getMessage());	
    	}
    	return fbinfolist;
    }

    public static List<flightInfo> getScheduledFlightsbyDate(LocalDate tdate)
    {
    	List<flightInfo> flightlist = null;
    	String status = "scheduled";
    	try 
    	{
            SessionFactory factory = hibernateUtil.getSessionFactory();
            
            Session session = factory.openSession();
            
            Query fquery = session.createQuery("from flightInfo f where f.travelDate = :tdate and f.flightStatus = :fstatus");
            fquery.setParameter("tdate", tdate);
            fquery.setParameter("fstatus", status);
           flightlist = (List<flightInfo>)fquery.list();
    	}
    	catch(Exception e)
    	{
    		System.out.println(e.getMessage());
    	}
    	
    	return flightlist;
    	
    }


}

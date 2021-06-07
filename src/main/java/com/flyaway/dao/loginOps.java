package com.flyaway.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.flyaway.model.adminCredentials;

public class loginOps 
{
	public static boolean validateLogin(String userName,String password)
    {
    	boolean isLoginValid = false;
    	try {
            SessionFactory factory = hibernateUtil.getSessionFactory();
            
            Session session = factory.openSession();
            // using HQL
            Query credq = session.createQuery("from adminCredentials a where a.userName = :uname and a.password = :pwd");
            credq.setParameter("uname", userName);
            credq.setParameter("pwd", password);
            List<adminCredentials> credlist = credq.list();
            if(credlist.isEmpty())
            {
            	isLoginValid = false;
            }
            else
            {
            	isLoginValid = true;
            }
            session.close();
    	}
    	catch(Exception e)
    	{
    		System.out.println(e.getMessage());
    	}
    	return  isLoginValid;
    }
	
	 public static boolean updateAdminCred(String username,String password)
	    {
	    	boolean isupdated = false;
	    	try 
	    	{
	            SessionFactory factory = hibernateUtil.getSessionFactory();
	            
	            Session session = factory.openSession();
	            
	            Transaction tx = session.beginTransaction();

	            adminCredentials cred = new adminCredentials();
	            cred.setUserName(username);
	            cred.setPassword(password);;
	            session.update(cred);
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

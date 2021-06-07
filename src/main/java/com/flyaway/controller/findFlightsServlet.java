package com.flyaway.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.flyaway.dao.hibernateUtil;
import com.flyaway.model.flightInfo;

/**
 * Servlet implementation class findFlightsServlet
 */
public class findFlightsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public findFlightsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String srcname = null;
		String destnname = null;
		String src = request.getParameter("srclocation");
		String destination = request.getParameter("destnlocation");
		String date = request.getParameter("traveldate");
		LocalDate traveldate = LocalDate.parse(date);
		
		String status = "scheduled";

		String adult = request.getParameter("Adult");
		String child = request.getParameter("Child");

		int noofadults = Integer.parseInt(adult);
		int noofchildren = Integer.parseInt(child);
		int noofpassengers = noofadults + noofchildren;
	
		List<String> passlist = new ArrayList<String>();
		for(int i=1;i<=noofpassengers;i++)
		{
			String pass = "Passenger " + i;
			passlist.add(pass);
		}
		
		String dateParts[] = date.split("-");
		  
        // Getting day, month, and year
        // from date
        String day = dateParts[2];
        String month = dateParts[1];
        String year = dateParts[0];
       
        HttpSession hsession = request.getSession();
        hsession.setAttribute("noOfPassengers", passlist);
        hsession.setAttribute("noOfPass", noofpassengers);
		PrintWriter out = response.getWriter();
		try {
			SessionFactory factory = hibernateUtil.getSessionFactory();

			Session session = factory.openSession();
			
			Query flightq = session.createQuery("from flightInfo f where f.src.srcId = :src and f.destination.destnId = :destn and  f.travelDate = :tdate and f.availSeats >= :noofPass and f.flightStatus = :fstatus");
			flightq.setParameter("src", src);
			flightq.setParameter("destn", destination);
			flightq.setParameter("tdate", traveldate);
			flightq.setParameter("noofPass", noofpassengers);
			flightq.setParameter("fstatus", status);
			
			List<flightInfo> flightlist = flightq.list();
			if (flightlist.isEmpty()) 
			{
				 Query srcq = session.createQuery("select srcname from sourceLocations s where s.srcId = :src");
				 srcq.setParameter("src", src);
				 srcname = (String)srcq.uniqueResult();
				 String srcParts[] = srcname.split("-");
				 String sname = srcParts[0];
				 
				 Query destnq = session.createQuery("select destnname from destnLocations d where d.destnId = :destn");
				 destnq.setParameter("destn", destination);
				 destnname = (String)destnq.uniqueResult();
				 String destnParts[] = destnname.split("-");
				 String dname = destnParts[0];
	             
	             
				 String error = "No flights available for " + srcname + " to " + destnname + " on " + day + "-" + month + "-" + year;
				/*request.getRequestDispatcher("searchFlights.jsp").include(request, response);
				out.println("<div id=msg align=center><SPAN style='color:red'><h2>" + msg + "</h2></SPAN></div>");*/
				 request.setAttribute("error", error);
				 RequestDispatcher dispatcher = request.getRequestDispatcher("searchFlights.jsp");
		            dispatcher.forward(request, response);
			} 
			else 
			{
				hsession.setAttribute("flightlist", flightlist);
	            RequestDispatcher dispatcher = request.getRequestDispatcher("displayFlights.jsp");
	            dispatcher.forward(request, response);
			}
			session.close();
		} 
		catch (Exception ex) 
		{
			out.println(ex.getMessage());
		}
	}

}

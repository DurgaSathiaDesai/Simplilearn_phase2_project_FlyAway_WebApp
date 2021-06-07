package com.flyaway.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.flyaway.dao.hibernateUtil;
import com.flyaway.model.flightInfo;
import com.flyaway.model.paymentInfo;

/**
 * Servlet implementation class bookFlightServlet
 */
public class bookFlightServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public bookFlightServlet() {
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
		 PrintWriter out = response.getWriter();
		 int bookingId = 0;
		 
		 String cardNumber = request.getParameter("cardnumber");
		 int expMonth = Integer.parseInt(request.getParameter("emonth"));
		 int expYear = Integer.parseInt(request.getParameter("eyear"));
		 String fullName = request.getParameter("fullname");
		 int cvv = Integer.parseInt(request.getParameter("cvv"));
		 
		 String fname = request.getParameter("fname");
		 String lname = request.getParameter("lname");
		 String email = request.getParameter("email");
		 String contactNo = request.getParameter("cno");
		 
		 HttpSession hsession = request.getSession(false);
		 String finfoId = (String) hsession.getAttribute("finfoId");
		 int noOfPassengers = Integer.parseInt(hsession.getAttribute("noOfPass").toString());
		 
		 List<String> pfnamelist = new ArrayList<String>();
		 List<String> plnamelist = new ArrayList<String>();
				 
		 List<String> plist =(List<String>)hsession.getAttribute("noOfPassengers");
		 for(String p : plist)
		 {
			 String temp = p + "fname";
			 pfnamelist.add(temp);
		 }
		 
		 for(String p : plist)
		 {
			 String temp = p + "lname";
			 plnamelist.add(temp);
		 }
		 //System.out.println(finfoId);
		 List<String> passFNameList = new ArrayList<String>();
		 for(String name : pfnamelist)
		 {
			 //System.out.println(request.getParameter(name));
			 passFNameList.add(request.getParameter(name));
		 }
		
		 List<String> passLNameList = new ArrayList<String>();
		 for(String name : plnamelist)
		 {
			 //System.out.println(request.getParameter(name));
			 passLNameList.add(request.getParameter(name));
		 }
		 
		 flightInfo finfo = hibernateUtil.getFlightInfo(finfoId);
		 if(finfo != null)
		 {
				 bookingId = hibernateUtil.addBooking(fname,lname,email,contactNo,noOfPassengers,finfo,cardNumber, expMonth,expYear,fullName,cvv,passFNameList,passLNameList);
				 //System.out.println(bookingId);
				 if(bookingId != 0)
				 {
					 boolean isSeatsUpdated = hibernateUtil.updateFlightInfo(finfoId, noOfPassengers);
				 }
				
		 }
		 request.setAttribute("bookingId", bookingId);
		 RequestDispatcher dispatcher = request.getRequestDispatcher("displayBooking.jsp");
         dispatcher.forward(request, response);
	}

}

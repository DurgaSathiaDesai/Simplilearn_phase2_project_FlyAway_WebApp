package com.flyaway.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.flyaway.dao.flightInfoOps;

/**
 * Servlet implementation class scheduleFlightsServlet
 */
public class scheduleFlightsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public scheduleFlightsServlet() {
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
		String flightcode = request.getParameter("flightcode");
		String src = request.getParameter("srclocation");
		String destn = request.getParameter("destnlocation");
		String tdate = request.getParameter("traveldate");
		String dtime = request.getParameter("deptime");
		String atime = request.getParameter("arrtime");
		String totSeats = request.getParameter("totalSeats");
		String availSeats = request.getParameter("availSeats");
		String price = request.getParameter("fprice");
		
		PrintWriter out = response.getWriter();
		String ispresent = null;
		//System.out.println(flightcode + "" + src + "" + destn +"" + tdate +"" + dtime +"" + atime + "" + totSeats + "" + availSeats + "" + price);
		
		ispresent = flightInfoOps.getFlightInfo(flightcode, src, destn, tdate);
		//System.out.println(ispresent);
		if(ispresent.equals("true"))
		{
			String errormsg ="Flight is already scheduled for this source to destination on the selected date!";
			request.setAttribute("errormsg", errormsg);
			 RequestDispatcher dispatcher = request.getRequestDispatcher("scheduleFlights.jsp");
	         dispatcher.forward(request, response);
			//request.getRequestDispatcher("scheduleFlights.jsp").include(request, response);
			//out.println("<div align=center><SPAN style='color:red'>Flight is already scheduled for this source to destination on the selected date!</SPAN></div>");
		}
		else
		{
			if(ispresent.equals("error"))
			{
				 String errormsg = "Error in scheduling a flight!Please try again!";
				request.setAttribute("errormsg", errormsg);
				 RequestDispatcher dispatcher = request.getRequestDispatcher("scheduleFlights.jsp");
		         dispatcher.forward(request, response);
				//request.getRequestDispatcher("scheduleFlights.jsp").include(request, response);
				//out.println("<div align=center><SPAN style='color:red'>Error in scheduling a flight!Please try again!</SPAN></div>");
			}
			else
			{
				boolean isadded = flightInfoOps.addFlightInfo(flightcode, src, destn, tdate, atime, dtime, totSeats, availSeats, price);
				if(isadded == true)
				{
				
					String successmsg = "Flight scheduling done successfully";
					 request.setAttribute("successmsg", successmsg);
					 RequestDispatcher dispatcher = request.getRequestDispatcher("scheduleFlights.jsp");
			         dispatcher.forward(request, response);
					//request.getRequestDispatcher("scheduleFlights.jsp").include(request, response);
					//out.println("<div align=center><SPAN style='color:black'>Flight scheduling done successfully</SPAN></div>");
				}
				else
				{
					 String errormsg = "Error in scheduling a flight!Please try again!";
					 request.setAttribute("errormsg", errormsg);
					 RequestDispatcher dispatcher = request.getRequestDispatcher("scheduleFlights.jsp");
			         dispatcher.forward(request, response);
					//request.getRequestDispatcher("scheduleFlights.jsp").include(request, response);
					//out.println("<div align=center><SPAN style='color:red'>Error in scheduling a flight!Please try again!</SPAN></div>");
				}
			}
		}
	}

}

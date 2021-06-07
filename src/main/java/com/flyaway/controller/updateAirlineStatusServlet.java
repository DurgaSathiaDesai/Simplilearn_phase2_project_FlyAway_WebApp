package com.flyaway.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.flyaway.dao.airlinesOps;

/**
 * Servlet implementation class updateAirlineStatusServlet
 */
public class updateAirlineStatusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateAirlineStatusServlet() {
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
		//PrintWriter out = response.getWriter();
		String aId = request.getParameter("airlineId");
		String aStatus = request.getParameter("astatus");
		
		//System.out.println(aId + " "+ aStatus);
		
		boolean isUpdated = airlinesOps.updateAirlineStatus(aId, aStatus);
		if(isUpdated==true) 
		{
			String successmsg = "Airline Status updated successfully";
			request.setAttribute("successmsg", successmsg);
			RequestDispatcher dispatcher = request.getRequestDispatcher("updateAirlineStatus.jsp");
	        dispatcher.forward(request, response);
			//request.getRequestDispatcher("updateAirlineStatus.jsp").include(request, response);
			//out.println("<div align=center><SPAN style='color:black'>Airline Status updated successfully</SPAN></div>");
		}
		else
		{
			String errormsg = "Error in updating the status!Please try again!";
			request.setAttribute("errormsg", errormsg);
			RequestDispatcher dispatcher = request.getRequestDispatcher("updateAirlineStatus.jsp");
	        dispatcher.forward(request, response);
			//request.getRequestDispatcher("updateAirlineStatus.jsp").include(request, response);
			//out.println("<div align=center><SPAN style='color:red'>Error in updating the status!Please try again!</SPAN></div>");
		}
	}

}

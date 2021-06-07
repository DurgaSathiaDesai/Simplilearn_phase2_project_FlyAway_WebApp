package com.flyaway.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.flyaway.dao.airlinesOps;
import com.flyaway.dao.hibernateUtil;

/**
 * Servlet implementation class addAirlinesServlet
 */
public class addAirlinesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addAirlinesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		PrintWriter out = response.getWriter();
		
		String aId = request.getParameter("airlineId");
		String aName = request.getParameter("airlinename");
		String aStatus = request.getParameter("astatus");
		
		String isIdPresent = null;
		
		isIdPresent = airlinesOps.checkAirlineId(aId);
		if(isIdPresent.equals("true"))
		{
			//PrintWriter out = response.getWriter();
			request.getRequestDispatcher("addAirlines.jsp").include(request, response);
			out.println("<div align=center><SPAN style='color:red'>Airline Id already exists!</SPAN></div>");
		}
		else
		{
			if(isIdPresent.equals("error"))
			{
				request.getRequestDispatcher("addAirlines.jsp").include(request, response);
				out.println("<div align=center><SPAN style='color:red'>Error in adding an airline!Please try again!</SPAN></div>");
			}
			else
			{
				boolean isAdded = airlinesOps.addAirlines(aId,aName,aStatus);
		
				if(isAdded==true)
				{
			
					request.getRequestDispatcher("addAirlines.jsp").include(request, response);
					out.println("<div align=center><SPAN style='color:black'>Airline added successfully</SPAN></div>");
				}
				else
				{
				
					request.getRequestDispatcher("addAirlines.jsp").include(request, response);
					out.println("<div align=center><SPAN style='color:red'>Error in adding an airline!Please try again!</SPAN></div>");
				}
			}
		}
	}

}

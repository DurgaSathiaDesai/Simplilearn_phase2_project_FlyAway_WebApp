package com.flyaway.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.flyaway.dao.destinationOps;
import com.flyaway.dao.hibernateUtil;

/**
 * Servlet implementation class addDestinationServlet
 */
public class addDestinationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addDestinationServlet() {
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
		
		String destId = request.getParameter("destnId");
		String destName = request.getParameter("destnname");
		String isIdPresent = null;
		String isNamePresent = null;
		
		 isIdPresent = destinationOps.checkDestnId(destId);
		if(isIdPresent.equals("true"))
		{
			
			request.getRequestDispatcher("addDestination.jsp").include(request, response);
			out.println("<div align=center><SPAN style='color:red'>Destination Id already exists!</SPAN></div>");
		}
		else
		{
			if(isIdPresent.equals("error"))
			{
				request.getRequestDispatcher("addDestination.jsp").include(request, response);
				out.println("<div align=center><SPAN style='color:red'>Error in adding a destination!Please try again!</SPAN></div>");
			}
			isNamePresent = destinationOps.checkDestnName(destName);
			if(isNamePresent.equals("true"))
			{
				
				request.getRequestDispatcher("addDestination.jsp").include(request, response);
				out.println("<div align=center><SPAN style='color:red'>Destination Name already exists!</SPAN></div>");
			}
			else
			{
				if(isNamePresent.equals("error"))
				{
					request.getRequestDispatcher("addDestination.jsp").include(request, response);
					out.println("<div align=center><SPAN style='color:red'>Error in adding a destination!Please try again!</SPAN></div>");
				}
				else
				{
					boolean isAdded = destinationOps.addDestn(destId, destName);
				
					if(isAdded==true)
					{
						request.getRequestDispatcher("addDestination.jsp").include(request, response);
						out.println("<div align=center><SPAN style='color:black'>Destination added successfully</SPAN></div>");
					}
					else
					{
						request.getRequestDispatcher("addDestination.jsp").include(request, response);
						out.println("<div align=center><SPAN style='color:red'>Error in adding a destination!Please try again!</SPAN></div>");
					}
				}
			}
		}
	}

}

package com.flyaway.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.flyaway.dao.sourceOps;

/**
 * Servlet implementation class addSourceServlet
 */
public class addSourceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addSourceServlet() {
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
		
		String srcId = request.getParameter("srcId");
		String srcName = request.getParameter("srcname");
		String isIdPresent = null;
		String isNamePresent = null;
		
		 isIdPresent = sourceOps.checkSourceId(srcId);
		if(isIdPresent.equals("true"))
		{
			//PrintWriter out = response.getWriter();
			request.getRequestDispatcher("addSource.jsp").include(request, response);
			out.println("<div align=center><SPAN style='color:red'>Source Id already exists!</SPAN></div>");
		}
		else
		{
			if(isIdPresent.equals("error"))
			{
				request.getRequestDispatcher("addSource.jsp").include(request, response);
				out.println("<div align=center><SPAN style='color:red'>Error in adding a source!Please try again!</SPAN></div>");
			}
			isNamePresent = sourceOps.checkSourceName(srcName);
			if(isNamePresent.equals("true"))
			{
				//PrintWriter out = response.getWriter();
				request.getRequestDispatcher("addSource.jsp").include(request, response);
				out.println("<div align=center><SPAN style='color:red'>Source Name already exists!</SPAN></div>");
			}
			else
			{
				if(isNamePresent.equals("error"))
				{
					request.getRequestDispatcher("addSource.jsp").include(request, response);
					out.println("<div align=center><SPAN style='color:red'>Error in adding a source!Please try again!</SPAN></div>");
				}
				else
				{
					boolean isAdded = sourceOps.addSrc(srcId, srcName);
					
					if(isAdded==true)
					{
						
					
						request.getRequestDispatcher("addSource.jsp").include(request, response);
						out.println("<div align=center><SPAN style='color:black'>Source added successfully</SPAN></div>");
					}
					else
					{
						//PrintWriter out = response.getWriter();
						request.getRequestDispatcher("addSource.jsp").include(request, response);
						out.println("<div align=center><SPAN style='color:red'>Error in adding a source!Please try again!</SPAN></div>");
					}
				}
			}
		  }
		}
	}



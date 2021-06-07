package com.flyaway.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.flyaway.dao.loginOps;

/**
 * Servlet implementation class adminLoginServlet
 */
public class adminLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public adminLoginServlet() {
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
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		
		boolean isValid = loginOps.validateLogin(userName, password);
		
		if(isValid == true)
		{
			response.sendRedirect("adminDashboard.jsp");
		}
		else
		{
			PrintWriter out = response.getWriter();
			request.getRequestDispatcher("adminLogin.jsp").include(request, response);
			out.println("<div align=center><SPAN style='color:red'>Invalid username or password</SPAN></div>");
		}
	}

}

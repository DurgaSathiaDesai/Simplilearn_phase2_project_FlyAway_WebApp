package com.flyaway.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.flyaway.dao.loginOps;

/**
 * Servlet implementation class changePwdServlet
 */
public class changePwdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public changePwdServlet() {
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
		String userName = request.getParameter("username");
		String oldpassword = request.getParameter("oldpassword");
		String newpassword = request.getParameter("newpassword");
		
		boolean isValid = loginOps.validateLogin(userName, oldpassword);
		
		if(isValid == true)
		{
			//response.sendRedirect("adminDashboard.jsp");
			boolean isupdated = loginOps.updateAdminCred(userName, newpassword);
			if(isupdated==true)
			{
				//String msg = ""
				request.getRequestDispatcher("adminLogin.jsp").include(request, response);
				out.println("<div align=center><SPAN style='color:black'>Password updated successfully!Please login using new credentials!</SPAN></div>");
			}
			else
			{
				String error = "Error in changing password!Please try again!";
				 request.setAttribute("error", error);
				 RequestDispatcher dispatcher = request.getRequestDispatcher("changePassword.jsp");
		         dispatcher.forward(request, response);
				//request.getRequestDispatcher("changePassword.jsp").include(request, response);
				//out.println("<div align=center><SPAN style='color:red'>Error in changing password!Please try again!</SPAN></div>");
			}
		}
		else
		{
			String error = "Invalid username or password!";
			 request.setAttribute("error", error);
			 RequestDispatcher dispatcher = request.getRequestDispatcher("changePassword.jsp");
	         dispatcher.forward(request, response);
			//request.getRequestDispatcher("changePassword.jsp").include(request, response);
			//out.println("<div align=center><SPAN style='color:red'>Invalid username or password</SPAN></div>");
		}
	}

}

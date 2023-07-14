package com.servlet;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.dao.LoginDao;

//@WebServlet("/Login")
public class Logincheck extends HttpServlet{
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		// Getting the username and password from the form
		String uname = req.getParameter("uname");
		String pass = req.getParameter("pass");
		
		LoginDao dao = new LoginDao();
		
		// Calling the DAO check function to check if the data is present in the database or not
		if(dao.check(uname, pass)) {
			HttpSession session = req.getSession();
			session.setAttribute("username", uname);
			res.sendRedirect("Fetch"); // If the user is authentic, we will call the fetch servlet to fetch the list 
		}else {
			HttpSession session = req.getSession();
			session.setAttribute("errorMsg", "Invalid username or password");
			res.sendRedirect("login.jsp"); // If the data is not present, we will hover back to the login screen
		}
	}
}

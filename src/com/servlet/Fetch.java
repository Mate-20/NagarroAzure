package com.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.advancejava.Details;

/**
 * Servlet implementation class Fetch
 */
@WebServlet("/Fetch")
public class Fetch extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Making the hibernate connection and fetching the data into the list and sending the list to website.jsp 
		// to show the data in table
		
		Configuration config = new Configuration().configure().addAnnotatedClass(Details.class);
    	ServiceRegistry regis = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
    	SessionFactory sf = config.buildSessionFactory(regis);
    	Session session = sf.openSession();
        
        List<Details> detailsList = session.createQuery("from Details").getResultList();
        session.close();
        
        // Set the data in request attribute
        request.setAttribute("detailsList", detailsList);
        
        // Forward to JSP page
        request.getRequestDispatcher("website.jsp").forward(request, response);
	}

	

}

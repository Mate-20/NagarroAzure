package com.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.advancejava.Details;

/**
 * Servlet implementation class Delete
 */
@WebServlet("/Delete")
@MultipartConfig
public class Delete extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// GETTING THE ID OF THE PARTICULAR ELEMENT
		
		int id = Integer.parseInt(request.getParameter("id"));

		Configuration config = new Configuration().configure().addAnnotatedClass(Details.class);
    	ServiceRegistry regis = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
    	SessionFactory sf = config.buildSessionFactory(regis);
    	Session session = sf.openSession();
    	Transaction tx =session.beginTransaction(); 
    	Details detail = (Details) session.get(Details.class, id);
    	
    	// IF DELETE BUTTON IS CLICKED
		session.delete(detail);
		tx.commit();
		
		// WE HAVE TO SEND THE UPDATED LIST TO WEBSITE.JSP
		List<Details> detailsList = session.createQuery("from Details").getResultList();
		
		session.close();
		
		// SETTING AND SENDING THE LIST TO WEBSITE.JSP
		request.setAttribute("detailsList", detailsList);        
        request.getRequestDispatcher("website.jsp").forward(request, response);
	}
}

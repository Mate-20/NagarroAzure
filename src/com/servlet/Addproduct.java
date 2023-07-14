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


import org.hibernate.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.*;
import org.hibernate.service.ServiceRegistry;
import com.advancejava.Details;

@WebServlet("/Addproduct")
@MultipartConfig
public class Addproduct extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// GETTING DATA FROM JSP PAGE
        String title = request.getParameter("Title");
        String quantity = request.getParameter("Quant");
        String size = request.getParameter("Size");
        Part imagePart = request.getPart("Image");
        byte[] image = imagePart.getInputStream().readAllBytes();
        
        // MAKING HIBERNATE CONNECTION
        Configuration config = new Configuration().configure().addAnnotatedClass(Details.class);
    	ServiceRegistry regis = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
    	SessionFactory sf = config.buildSessionFactory(regis);
    	Session session = sf.openSession();
    	Transaction tx =session.beginTransaction(); 
    	
    	// SETTING THE VALUES IN CONSTRUCTOR OF OUR ENTITY CLASS
    	Details details = new Details(title,quantity,size,image);
        session.save(details);

        tx.commit();
        session.close();
        
        // After Adding the product we need to display the Updated List.
        Session session2 = sf.openSession();
        List<Details> detailsList = session2.createQuery("from Details").getResultList();
        session2.close();
        
        // Set the data in request attribute
        request.setAttribute("detailsList", detailsList);
        
        // SENDING UPDATED LIST TO WEBSITE.JSP
        request.getRequestDispatcher("website.jsp").forward(request, response);
	}

}

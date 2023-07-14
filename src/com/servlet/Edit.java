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
 * Servlet implementation class Edit
 */
@WebServlet("/Edit")
@MultipartConfig
public class Edit extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// We called this servlet from Edit.jsp
		int id = Integer.parseInt(request.getParameter("id"));
		
		Configuration config = new Configuration().configure().addAnnotatedClass(Details.class);
    	ServiceRegistry regis = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
    	SessionFactory sf = config.buildSessionFactory(regis);
    	Session session = sf.openSession();
    	Transaction tx =session.beginTransaction(); 
    	Details detail = (Details) session.get(Details.class, id);
    	
    	String title = request.getParameter("Title");
        String quantity = request.getParameter("Quantity");
        String size = request.getParameter("Size");
        Part imagePart = request.getPart("Image");
        byte[] image = imagePart.getInputStream().readAllBytes();
        
        // If we haven't updated anything, we will have to check it, other wise it will be update with null values
        if(image.length != 0) {
        	detail.setImage(image);
        }
        if(quantity.length()!=0) {
        	detail.setQuantity(quantity);
        }
        if(size.length()!=0) {
        	detail.setSize(size);
        }
        if(title.length() != 0) {
        	detail.setTitle(title);
        }
        
        session.update(detail);
        tx.commit();
        List<Details> detailsList = session.createQuery("from Details").getResultList();
        session.close();
        
     // SETTING AND SENDING THE LIST TO WEBSITE.JSP
     	request.setAttribute("detailsList", detailsList);        
        request.getRequestDispatcher("website.jsp").forward(request, response);
	}

}

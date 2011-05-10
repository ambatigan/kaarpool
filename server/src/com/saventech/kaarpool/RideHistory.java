package com.saventech.kaarpool;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class RideHistory extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	/** The log. */
	Logger log = Logger.getLogger(LoginServlet.class);
  //  @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        PrintWriter out=response.getWriter();
        String name,role;
        name=request.getParameter("name");
        role=request.getParameter("role");
        System.out.println(name+" "+role+"COMMINGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGg");
        try
        {
        	DBInterface connect = DBInterface.getInstance();
        	   if(connect.isConnectionOpen())
        	   {
        		   String history=connect.rideHistory(name, role);
        		   out.println(history.toString().trim());
        	   }
        }
        catch(Exception e)
        {
        	log.info("Exception occured in connecting to db");
        	out.print("Exception in connecting to db");
        }
        
    }
}
        	   
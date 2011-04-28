package com.saventech.kaarpool;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class InjectEvents extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	/** The log. */
	Logger log = Logger.getLogger(LoginServlet.class);
  //  @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        PrintWriter out=response.getWriter();
        String events;
        events=request.getParameter("InjectedEvents");
        System.out.println(events+"COMMINGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGg");
        try
        {
        	DBInterface connect = DBInterface.getInstance();
        	   if(connect.isConnectionOpen())
        	   {
        		   String res=connect.injectingEvents(events.toString().trim());
        		   
                    out.print(res.toString().trim());
        	   }
        	   else
        	   {
        		   out.print("Please_check_the_connection");
        	   }
        	
        }
        catch(Exception e)
        {
        	log.info("Exception occured in connecting to db");
        }
        
    }
}
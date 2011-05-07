package com.saventech.kaarpool;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class InjectAcknowledgementEvents extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	/** The log. */
	Logger log = Logger.getLogger(LoginServlet.class);
  //  @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        PrintWriter out=response.getWriter();
        String events;
        events=request.getParameter("InjectedAckEvents");
        System.out.println(events+"COMMINGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGg");
        String status="";
        try
        {
        	DBInterface connect = DBInterface.getInstance();
        	   if(connect.isConnectionOpen())
        	   {
        		   System.out.println(events);
        		   String ackevents[]=events.toString().trim().split("EVENT");
        		   events=ackevents[0]+" "+ackevents[1].toString().trim()+" "+ackevents[2].toString().trim()+"EVENT";
        		   status=connect.injectingEvents(events);
        		   if(status.toString().trim().equals("successfully injected"))
        		   {
        			   status=connect.updateAcknowledgementEvents(ackevents[1].toString().trim(),ackevents[2].toString().trim() );
        		   }
        		   System.out.println(events);
        	   }
        	   if(status.toString().trim().equals("Successfully updated"))
        	   {
        	   
                     out.print("successfully injected");
        	   }
        	   
        	   
   	   }
        catch(Exception e)
        {
        	log.info("Exception occured in connecting to db");
        	out.print("Exception occured while inteject acknowledgement events");
        }
    }
}

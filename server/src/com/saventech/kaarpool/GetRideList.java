package com.saventech.kaarpool;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class GetRideList extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	/** The log. */
	Logger log = Logger.getLogger(LoginServlet.class);
  //  @Override
    @SuppressWarnings("rawtypes")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        PrintWriter out=response.getWriter();
       String riderid, ridersource, riderdestination, riderstarttime;
        riderid=request.getParameter("riderid");
        ridersource=request.getParameter("ridersource");
        riderdestination=request.getParameter("riderdestination");
        riderstarttime=request.getParameter("riderstarttime");
        System.out.println(riderstarttime+"---------------------------------------------");
        //ridermode=request.getParameter("ridermode");
        DBInterface connect = DBInterface.getInstance();
        if(connect.isConnectionOpen())
   	   {
        	try
        	{
        		ArrayList list;
       	        list=connect.getRidelist(ridersource, riderdestination, riderstarttime, riderid);
       	        if(list.size()!=0)
       	        {
       	        	out.print(list.size()+"KRL");
       	        	for( int i =0; i<list.size();i++)
       	        	{
       	        		//System.out.println(list.get(0).toString()+"nnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn");
       	    	   	 
       		          // out.println(riderid+" "+ridersource+" "+riderdestination+" "+riderstarttime+" "+ridermode);
       		   	        out.print(list.get(i).toString());
       		   	        
       	        	}
    	   	        
       	        }
       	        else
       	        {
       	        	out.println("NO results found");
       	        }
        	}
        	catch(Exception e)
        	{
        		out.println("Exception occured");
        	}
        	
   	   }
      
        
        
    }
}

package com.saventech.kaarpool;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class RiderNewRoute extends HttpServlet
{
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException 
    {
		System.out.println("Rider new route servlet");
        PrintWriter out=response.getWriter();
        String username,ridersrc, riderdest, jstarttime, usermode;
        username=request.getParameter("regid");
        ridersrc = request.getParameter("ridersrc");
        riderdest = request.getParameter("riderdest");
        jstarttime = request.getParameter("starttime");
        usermode = request.getParameter("mode");
        System.out.println("username:"+username+" "+ridersrc+" "+riderdest+" "+jstarttime+" "+usermode);
        
        DBInterface connect = DBInterface.getInstance();
 	   	if(connect.isConnectionOpen())
 		{
 	   		connect.riderJourneydetails(username, ridersrc, riderdest, jstarttime, usermode);
 	   		connect.updateUserdetails(username, usermode);
 		}
        
        out.print("values stored in journey details db");
        
    }

}

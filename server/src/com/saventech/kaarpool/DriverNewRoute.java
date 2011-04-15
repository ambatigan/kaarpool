package com.saventech.kaarpool;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class DriverNewRoute extends HttpServlet
{
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException 
    {
		System.out.println("Driver new route servlet");
        PrintWriter out=response.getWriter();
        String username,driversrc, driverdest, seats, jstarttime, usermode;
        username=request.getParameter("regid");
        driversrc = request.getParameter("driversrc");
        driverdest = request.getParameter("driverdest");
        seats = request.getParameter("seats");
        jstarttime = request.getParameter("starttime");
        usermode = request.getParameter("mode");
        System.out.println("username:"+username+" "+driversrc+" "+driverdest+" "+seats+" "+jstarttime+" "+usermode);
        
        DBInterface connect = DBInterface.getInstance();
 	   	if(connect.isConnectionOpen())
 		{
 	   		connect.journeydetails(username, driversrc, driverdest, jstarttime, usermode, seats);
 	   		connect.updateUserdetails(username, usermode);
 		}
        
        out.print("values stored in journey details db");
        
    }

}

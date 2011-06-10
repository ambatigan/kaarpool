package com.saventech.kaarpool;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class RideCancel extends HttpServlet{
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException 
    {
		String canceldata = "";
		System.out.println("New ride cancellation");
        PrintWriter out=response.getWriter();
        String username, source, destination, seats, time;
        username = request.getParameter("username");
        source = request.getParameter("source");
        destination = request.getParameter("destination");
        time = request.getParameter("time");
        System.out.println(" username: "+username+" source: "+source+" destination: "+destination+" time: "+time);
        
        DBInterface connect = DBInterface.getInstance();
 	   	if(connect.isConnectionOpen())
 		{
 	   		canceldata = connect.driverRideCacellation(username, source, destination, time);
 		}
        System.out.println(canceldata);
        out.println(canceldata);
    }

}
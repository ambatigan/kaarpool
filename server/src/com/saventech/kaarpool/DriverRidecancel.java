package com.saventech.kaarpool;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class DriverRidecancel extends HttpServlet{
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException 
    {
		String canceldata = "";
		System.out.println("New Driver ride cancellation");
        PrintWriter out=response.getWriter();
        String jid, route, source, destination, seats, time;
        jid=request.getParameter("jid");
        route = request.getParameter("route");
        source = request.getParameter("csource");
        destination = request.getParameter("cdestination");
        seats = request.getParameter("seats");
        time = request.getParameter("ctime");
        System.out.println("jid:"+jid+" route: "+route+" source: "+source+" destination: "+destination+" seats: "+seats+" time: "+time);
        
        DBInterface connect = DBInterface.getInstance();
 	   	if(connect.isConnectionOpen())
 		{
 	   		canceldata = connect.saveDriverroute(jid, route, source, destination, seats, time);
 		}
        System.out.println(canceldata);
        out.println(canceldata);
    }

}
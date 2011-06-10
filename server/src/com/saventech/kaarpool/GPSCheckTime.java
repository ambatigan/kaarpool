package com.saventech.kaarpool;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class GPSCheckTime extends HttpServlet{
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException 
    {
		String res_msg = "";
		System.out.println("GPSCheckTime servlet");
        PrintWriter out=response.getWriter();
        String username, date, time;
        username = request.getParameter("username");
        date = request.getParameter("date");
        time = request.getParameter("time");
        System.out.println("username: "+username+" date: "+date+" time: "+time);
        
        DBInterface connect = DBInterface.getInstance();
 	   	if(connect.isConnectionOpen())
 		{
 	   		res_msg = connect.gpsTime(username, date, time);
 		}
        
        out.print(res_msg);
    }

}
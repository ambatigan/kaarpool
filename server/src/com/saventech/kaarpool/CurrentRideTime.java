package com.saventech.kaarpool;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class CurrentRideTime extends HttpServlet{
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException 
    {
		String res_msg = "";
		System.out.println("CurrentRideTime servlet");
        PrintWriter out=response.getWriter();
        String drivername, ridername, cur_time;
        drivername=request.getParameter("drivername");
        ridername = request.getParameter("ridername");
        cur_time = request.getParameter("cur_time");
        System.out.println("drivername: "+drivername+"ridername: "+ridername+"current time: "+cur_time);
        
        DBInterface connect = DBInterface.getInstance();
 	   	if(connect.isConnectionOpen())
 		{
 	   		res_msg = connect.getCurrentRidetime(drivername, ridername, cur_time);
 		}
        
        out.print(res_msg);
    }

}
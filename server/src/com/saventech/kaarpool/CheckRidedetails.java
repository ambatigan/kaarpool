package com.saventech.kaarpool;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class CheckRidedetails extends HttpServlet
{
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException 
    {
		System.out.println("Check ride details servlet");
        PrintWriter out=response.getWriter();
        String username,src, dest, seats, time;
        username=request.getParameter("username");
        src = request.getParameter("src");
        dest = request.getParameter("dest");
        seats = request.getParameter("seats");
        time = request.getParameter("time");
        System.out.println("username:"+username+" "+src+" "+dest+" "+seats+" "+time);
        
        DBInterface connect = DBInterface.getInstance();
        String str = "";
 	   	if(connect.isConnectionOpen())
 		{
 	   		str = connect.checkDriverjourneydetails(username, src, dest, seats, time);
 		}
        
        out.print(str);
        
    }

}
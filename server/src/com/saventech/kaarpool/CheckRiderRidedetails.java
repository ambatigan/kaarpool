package com.saventech.kaarpool;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class CheckRiderRidedetails extends HttpServlet
{
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException 
    {
		System.out.println("Check rider ride details servlet");
        PrintWriter out=response.getWriter();
        String username,src, dest, time;
        username=request.getParameter("username");
        src = request.getParameter("src");
        dest = request.getParameter("dest");
        time = request.getParameter("time");
        System.out.println("username:"+username+" "+src+" "+dest+" "+time);
        
        DBInterface connect = DBInterface.getInstance();
        String str = "";
 	   	if(connect.isConnectionOpen())
 		{
 	   		str = connect.checkRiderjourneydetails(username, src, dest, time);
 		}
        
        out.print(str);
        
    }

}
package com.saventech.kaarpool;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class GetRidedetails extends HttpServlet{
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException 
    {
		String canceldata = "";
		System.out.println("Driver route details list servlet");
        PrintWriter out=response.getWriter();
        String username;
        username=request.getParameter("username");
        System.out.println("username:"+username);
        
        DBInterface connect = DBInterface.getInstance();
 	   	if(connect.isConnectionOpen())
 		{
 	   		canceldata = connect.getTotalRidedetails(username);
 	   		
 		}
        
        out.print(canceldata);
    }

}
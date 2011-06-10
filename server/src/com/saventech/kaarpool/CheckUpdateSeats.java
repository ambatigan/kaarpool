package com.saventech.kaarpool;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class CheckUpdateSeats extends HttpServlet{
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException 
    {
		String canceldata = "";
		System.out.println("Updating seats");
        PrintWriter out=response.getWriter();
        String rideid;
        rideid=request.getParameter("rideid");
       
        
       
        
        DBInterface connect = DBInterface.getInstance();
 	   	if(connect.isConnectionOpen())
 		{
 	   	 System.out.println("rideid:  "+rideid+"ooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo");
 	   	 String responsedata=connect.checkUpdateSeats(rideid.toString().trim());
 	   	 out.print(responsedata);
 	   	 
 	   	 
 		}
 	   	else
 	   	{
 	   		System.out.println("UpdateSeats:  Plse check database connection");
 	   	}
        
        //out.print(canceldata);
    }

}
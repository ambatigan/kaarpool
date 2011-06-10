package com.saventech.kaarpool;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class UpdateSeats extends HttpServlet{
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException 
    {
		String canceldata = "";
		System.out.println("Updating seats");
        PrintWriter out=response.getWriter();
        String rideid;
        int seats;
        rideid=request.getParameter("rideid");
        seats=Integer.parseInt(request.getParameter("seats"));
        
       
        
        DBInterface connect = DBInterface.getInstance();
 	   	if(connect.isConnectionOpen())
 		{
 	   	 System.out.println("rideid:  "+rideid+"  Seats: "+seats+"ooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo");
 	   	 connect.updateSeats(rideid, seats);
 	   	 
 	   	 
 		}
 	   	else
 	   	{
 	   		System.out.println("UpdateSeats:  Plse check database connection");
 	   	}
        
        //out.print(canceldata);
    }

}
package com.saventech.kaarpool;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RideDestination extends HttpServlet
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException 
    {
		System.out.println("RideDestination servlet");
        PrintWriter out=response.getWriter();
        String rideid;
        rideid=request.getParameter("rideid");
        System.out.println("rideid:"+rideid);
        
        DBInterface connect = DBInterface.getInstance();
        String str = "";
 	   	if(connect.isConnectionOpen())
 		{
 	   		str = connect.rideDestination(rideid);
 		}
        
        out.print(str);
        
    }

}
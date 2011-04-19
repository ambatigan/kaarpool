package com.saventech.kaarpool;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TravelPreferences extends HttpServlet 
{

	    /**
	 * 
	 */
	private static final long serialVersionUID = -7173804770823470497L;

		protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException 
	    {
	        PrintWriter out=response.getWriter();
	        String travelPref, seats, image,username;
			DBInterface connect = DBInterface.getInstance();
			if(connect.isConnectionOpen())
			{
				travelPref=request.getParameter("travelPref");
		        seats=request.getParameter("seats");
		        image=request.getParameter("image");
		        username=request.getParameter("username");
		        int i = connect.store_travelPref(travelPref,seats,image,username);
		        if(i==1)
				{
		            out.print(1);
				}
		        else
				{
		            out.print(0);
				}
				
		    }
			else
			{
				out.print("Please check the db connection");
			}

	}
}
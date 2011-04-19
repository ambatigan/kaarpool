package com.saventech.kaarpool;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetTimeBasedPref extends HttpServlet {

	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		//  @Override
	    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException
	    {
	        PrintWriter out=response.getWriter();
	        String username;
	        username=request.getParameter("username");
	        System.out.println(username+"userid");
	        
			DBInterface connect = DBInterface.getInstance();
		    if(connect.isConnectionOpen())
			{
		    	String s = connect.get_timebasedPref(username);
//		    	
		    	out.print(s);
			}
		    else
			{
				out.print("Please check the db connection");
			}

	}
}
	 
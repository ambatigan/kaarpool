package com.saventech.kaarpool;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TimeBased extends HttpServlet {

	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		//  @Override
	    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException
	    {
	        PrintWriter out=response.getWriter();
	        String userid;
	        userid=request.getParameter("username");
	        System.out.println(userid+"userid");
	        String tsource,tlocation,tdestination, timing, weekdays;
			DBInterface connect = DBInterface.getInstance();
		    if(connect.isConnectionOpen())
			{
		    	tsource=request.getParameter("tsource");
		    	tlocation=request.getParameter("tlocation");
		    	tdestination=request.getParameter("tdestination");
		    	timing=request.getParameter("timing");
		    	weekdays=request.getParameter("weekdays");
		    	String s = connect.store_timebasedPref(weekdays,tsource,tdestination,timing,tlocation,userid);
		    	System.out.println(s+"s in timebased");
		    	out.print(s);
			}
		    else
			{
				out.print("Please check the db connection");
			}

	}
}
	 
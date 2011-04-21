package com.saventech.kaarpool;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class DriverRidecancel extends HttpServlet{
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException 
    {
		String canceldata = "";
		System.out.println("Driver ride cancellation");
        PrintWriter out=response.getWriter();
        String username, csource, cdestination, ctime;
        username=request.getParameter("username");
        csource = request.getParameter("csource");
        cdestination = request.getParameter("cdestination");
        ctime = request.getParameter("ctime");
        System.out.println("username:"+username+" csource: "+csource+" cdestination: "+cdestination+" ctime: "+ctime);
        
        DBInterface connect = DBInterface.getInstance();
 	   	if(connect.isConnectionOpen())
 		{
 	   		canceldata = connect.driverRideCacellation(username, csource, cdestination, ctime);
 		}
        
        out.print(canceldata);
    }

}
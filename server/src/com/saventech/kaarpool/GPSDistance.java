package com.saventech.kaarpool;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class GPSDistance extends HttpServlet
{
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException 
    {
		System.out.println("gpsdistance servlet");
        PrintWriter out=response.getWriter();
        String rideid, ridername;
        rideid=request.getParameter("rideid");
        ridername = request.getParameter("ridername");
        System.out.println("rideid:"+rideid+" ridername: "+ridername);
        
        DBInterface connect = DBInterface.getInstance();
        String str = "";
 	   	if(connect.isConnectionOpen())
 		{
 	   		str = connect.GPSCoordiantes_Distance(rideid, ridername);
 		}
        
        out.print(str);
        
    }

}
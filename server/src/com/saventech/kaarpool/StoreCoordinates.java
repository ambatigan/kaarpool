package com.saventech.kaarpool;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class StoreCoordinates extends HttpServlet{
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException 
    {
		String res_msg = "";
		System.out.println("StoreCoordinates servlet");
        PrintWriter out=response.getWriter();
        String rideid, lat, lng, username;
        rideid=request.getParameter("rideid");
        lat = request.getParameter("lat");
        lng = request.getParameter("lng");
        username = request.getParameter("username");
        System.out.println("rideid: "+rideid+"Latitude: "+lat+"longitude: "+lng+" username: "+username);
        
        DBInterface connect = DBInterface.getInstance();
 	   	if(connect.isConnectionOpen())
 		{
 	   		res_msg = connect.storeCoordinates(rideid, lat, lng, username);
 		}
        
        out.print(res_msg);
    }

}
package com.saventech.kaarpool;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class TrackDrivername extends HttpServlet{
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException 
    {
		String res_msg = "";
		System.out.println("TrackDrivername servlet");
        PrintWriter out=response.getWriter();
        String rideid, username;
        username = request.getParameter("username");
        rideid=request.getParameter("rideid");
       
        System.out.println("rideid: "+rideid+" username: "+username);
        
        DBInterface connect = DBInterface.getInstance();
 	   	if(connect.isConnectionOpen())
 		{
 	   		res_msg = connect.traceUser(username, rideid);
 		}
        
        out.print(res_msg);
    }

}
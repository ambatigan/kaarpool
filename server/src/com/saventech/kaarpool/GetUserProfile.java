package com.saventech.kaarpool;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class GetUserProfile extends HttpServlet{
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException 
    {
		String profiledata = "";
		System.out.println("User Profile servlet");
        PrintWriter out=response.getWriter();
        String username;
        username=request.getParameter("username");
        System.out.println("username:"+username);
        try
        {
        	String splitdata[]=username.toString().trim().split(":");
            username=splitdata[1].toString().trim();
            
            DBInterface connect = DBInterface.getInstance();
     	   	if(connect.isConnectionOpen())
     		{
     	   		profiledata = connect.getUserProfile(username);
     	   		System.out.println(profiledata);
     	   		
     		}
            
            out.print(profiledata);
        }
        catch(Exception e)
        {
        	System.out.println("Exception in returning user profile");
        	out.print("Exception occured");
        }
        
    }

}
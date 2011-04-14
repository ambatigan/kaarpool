package com.saventech.kaarpool;

import java.io.IOException;
import java.io.PrintWriter;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProfilePreferences extends HttpServlet 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3551758082017638113L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        PrintWriter out=response.getWriter();
        String username;
		DBInterface connect = DBInterface.getInstance();
		String result = "";
	    if(connect.isConnectionOpen())
		{
		     
		   username=request.getParameter("user_pref");
		   System.out.println(username+"username of user");
		   result=connect.getUserProfilePreferences(username);
		   System.out.println(result+"response from server");
		   out.print(result);
		}
        
    }

}

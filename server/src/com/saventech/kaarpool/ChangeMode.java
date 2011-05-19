package com.saventech.kaarpool;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class ChangeMode extends HttpServlet {

	private static final long serialVersionUID = 1L;
	/** The log. */
	Logger log = Logger.getLogger(AuthenticateIds.class);
  
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException 
    {
    	System.out.println("Entered in ChangeMode servlet");
        PrintWriter out=response.getWriter();
        
		String username,mode;
        username=request.getParameter("username");
        mode=request.getParameter("mode");
        DBInterface connect = DBInterface.getInstance();
	  	if(connect.isConnectionOpen())
	  	{
	  		String res=connect.changeMode(username, mode);
	  		out.print(res.toString().trim());
	  	}
	  	else
	  	{
	  		out.print("Please check the db connection");
	  	}
    	
    }
 }
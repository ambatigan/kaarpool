package com.saventech.kaarpool;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class AuthenticateIds extends HttpServlet {

	private static final long serialVersionUID = 1L;
	/** The log. */
	Logger log = Logger.getLogger(AuthenticateIds.class);
  
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException 
    {
    	System.out.println("checking authenticate idsss");
        PrintWriter out=response.getWriter();
        @SuppressWarnings("unused")
		String userid,userpwd;
        userid=request.getParameter("sysregid");
        DBInterface connect = DBInterface.getInstance();
	  	   if(connect.isConnectionOpen())
	  	   {
	  		     boolean flag= connect.Authenticate_registerids(userid.toString());
	  		     log.info(flag+"---------------------------------");
	  		     try
	  		     {
		  		     if(flag)
		  		     {
			  			 out.print("NO");
			  			 log.info("User already existed");
		  		     }
		  		     else
		  		     {
		  		    	 out.println("YES");
		  		    	 log.info("User with "+userid+" doesnot exist");
		  		     }
	  		     }
	  		     catch(Exception e)
	  		     {
	  		    	log.info("User with "+userid+" doesnot exist. Please register");
	  		     }
	  	   }
	  		     
	 }	
}

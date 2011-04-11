package com.saventech.kaarpool;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class AuthenticateIds extends HttpServlet {

	/** The log. */
	Logger log = Logger.getLogger(LoginServlet.class);
  //  @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        PrintWriter out=response.getWriter();
        String userid,userpwd;
        userid=request.getParameter("sysregid");
        //userpwd=request.getParameter("loginuserpwd");
     
        //DB PART COMES HERE
        DBInterface connect = DBInterface.getInstance();
  	   if(connect.isConnectionOpen())
  	   {
  		   System.out.println(userid.toString()+"userid.toString()");
  		     boolean flag= connect.Authenticate_registerids(userid.toString());
  		     System.out.println(flag+"---------------------------------");
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
	  		    	//System.out.println("User name does not exist. Please register");
	  		     }
  		     }
  		     catch(Exception e)
  		     {
  		    	log.info("User with "+userid+" doesnot exist. Please register");
  		    	 //System.out.println("User name does not exist. Please register");
  		     }
  	   }
  		     
  	  }

	
}

package com.saventech.kaarpool;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class SysRegistration extends HttpServlet {

  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//  @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        PrintWriter out=response.getWriter();
        String username,pwd,address,mobile,gender;
		String dob, image;
        username=request.getParameter("sysregid");
        pwd=request.getParameter("sysregpwd");
        dob=request.getParameter("sysregdob");
        address=request.getParameter("sysregaddress");
        mobile=request.getParameter("sysregmobile");
        image=request.getParameter("sysregimage");
        gender=request.getParameter("sysreggender");
        Date theDate = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        try {
		 theDate = dateFormat.parse(dob);
			System.out.println("Date parsed = " + dateFormat.format(theDate));
			//Date d = dateFormat.format(theDate);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
        

        DBInterface connect = DBInterface.getInstance();
 	   if(connect.isConnectionOpen())
 			{
 		      try
 		      {
 		       connect.registration(username, mobile, address,gender,dateFormat.format(theDate),image);
 		       connect.karpooldetails(username, pwd," 1");
 		       connect.update_userdetails(username);
 		      }
 		      catch(Exception e)
 		      {
 		    	 out.print("Exception occured while storing the values in db"+e.getMessage());  
 		      }
 		       
 			}
 	  else
		{
			out.print("Please check the db connection");
		}

 	  out.print("values stored in db");
       
        
    }
    
    
}

package com.saventech.kaarpool;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class SaveProfilePref extends HttpServlet{
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        PrintWriter out=response.getWriter();
        String sPwd, saddress, simage, suname, smobile;
        
		DBInterface connect = DBInterface.getInstance();
		int result ;
	    if(connect.isConnectionOpen())
		{ 
		   suname =request.getParameter("pusername");
		   sPwd = request.getParameter("ppwd");
		   smobile = request.getParameter("pmobile");
		   saddress = request.getParameter("paddress");
		   simage = request.getParameter("pimage");
		   System.out.println(suname+"user_pwd of user");
		   result=connect.saveUserPref(suname,sPwd,smobile,saddress,simage);
		   System.out.println(result+"response from server");
		   out.print(result);
		}
        
    }

}

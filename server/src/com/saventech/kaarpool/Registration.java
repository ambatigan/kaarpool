package com.saventech.kaarpool;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Prashant
 */
//@WebServlet(name="AndroidResponse", urlPatterns={"/androidres.do"})
@SuppressWarnings("serial")
public class Registration extends HttpServlet {

  //  @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        PrintWriter out=response.getWriter();
        String un,pw;
		 DBInterface connect = DBInterface.getInstance();
	   if(connect.isConnectionOpen())
			{
			   un=request.getParameter("username");
        pw=request.getParameter("password");
		
        if(un.equalsIgnoreCase("saven1") && pw.equals("karpool1"))
		{
		    connect.info(un,pw);
            out.print(1);
			}
        else
		{
            out.print(0);
			}
			}
			else
			{
			System.out.println("Roshan");
			}
        
    }

}
 
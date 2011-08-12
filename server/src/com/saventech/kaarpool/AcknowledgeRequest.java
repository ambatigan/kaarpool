package com.saventech.kaarpool;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AcknowledgeRequest extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException 
    {
		System.out.println("AcknowledgeRequest servlet");
        PrintWriter out=response.getWriter();
        String rideid, rname, flag;
        rideid=request.getParameter("rideid");
        rname = request.getParameter("rname");
        flag = request.getParameter("flag");
        System.out.println("rideid:"+rideid+" rname: "+rname+" flag: "+flag);
        
        DBInterface connect = DBInterface.getInstance();
        String str = "";
 	   	if(connect.isConnectionOpen())
 		{
 	   		str = connect.pickupRequestCheck(rideid,rname);
 		}
        
        out.print(str);
        
    }

}
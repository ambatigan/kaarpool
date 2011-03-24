package com.saventech.karpool;

import java.util.ArrayList;

public class Controller 
{
	String loginid;
	String loginpwd;
	String regid;
	String regpwd;
	int checksysid=0;
	public Controller()
	{
		
	}
	public boolean Authenticate_login(String id, String pwd)
	{
		loginid=id.toString().trim();
		loginpwd=pwd.toString().trim();
		System.out.println("rajeshhhhhhhhhhhhhhhhh");
		if(loginid.equals("user@gmail.com")&&loginpwd.equals("user"))
		{
			return true;
		}
		return false;
		
	}
	public boolean Authenticate_register(String id, String pwd)
	{
		loginid=id.toString().trim();
		loginpwd=pwd.toString().trim();
		System.out.println("nageshddddddddddddddd");
		if(loginid.equals("user@gmail.com")&&loginpwd.equals("user"))
		{
			return true;
		}
		return false;
		
	}
	public boolean Availablesysids(String id)
	{
		
		ArrayList<String> list=new ArrayList<String>();
		list.add("rajesh");
		list.add("mahesh");
		list.add("somesh");
		list.add("hareesh");
		 System.out.println("nageshddddddddddddddd1");
		for(int i=0;i<list.size();i++)
		{
			if(list.get(i).toString().equals(id.toString()))
			{
				return true;
			}
		}
		return false;
	}
	public boolean Validatesysidregister()
	{
		return false;
	}
	public boolean Getridelist()
	{
		return true;
	}
	public boolean Createnewroute()
	{
		return true;
	}
	public boolean Canceldriverroute()
	{
		return true;
	}
	
}

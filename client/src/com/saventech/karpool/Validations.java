package com.saventech.karpool;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validations 
{
	
	public boolean mobileValidate(String mobilenumber)
	{
		Pattern mobile = Pattern.compile("\\d{11}");
	     Matcher match = mobile.matcher(mobilenumber.toString().trim());
		 boolean matchfound= match.lookingAt();
		 if(matchfound)
		 {
			 
			 if(mobilenumber.toString().charAt(0)=='0' && (mobilenumber.toString().length()==10 || mobilenumber.toString().length()==11))
			 {
				 
				 return true;
			 }
			 else
			 {
				return false;
				 //validatestring=validatestring+"-> Pls enter correct mobile number(start with zero and follows by 10 digits) \n";
			 }
		 }
		 else
		 {
			 return false;
			// validatestring=validatestring+"-> Pls enter correct mobile number(start with zero and follows by 10 digits) \n";
		 }
	}
	public boolean passwordValidation(String password)
	{
		if(password.toString().trim().length()>=5)
		 {
			 return true;
		 }
		 else
		 {
			 return false;
		 }
	}

}

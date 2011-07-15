//Lavanya 10.06.2011
package com.saven.karPool.model.vo
{
	import mx.collections.ArrayCollection;
	
	public final class Constants
	{
		public function Constants(){}
		
		[Embed(source="assets/images/layout.gif")]
		public static const LAYOUTPAGE:Class; 
		
	/* 	[Embed(source="assets/images/logo.jpg")]
		public static const LOGO:Class; 
		
		[Embed(source="assets/images/welcome.gif")]
		public static const WELCOME:Class;  */
		
		[Embed(source="assets/images/content.gif")]
		public static const CONTENT:Class; 
		
	//	public static const SERVICE_PATH:String = 'config/services.xml';
		
		public static const SERVICE_PATH:String = 'config/services.xml';
		    	
    	public static const USERNAME_TITLE:String = "Username";
	
		public static const PASSWORD_TITLE:String = "Password";
	
		public static const SIGNEDIN_TITLE:String = "[  Signed in as ";
	
		public static const SIGNEDOUT_TITLE:String = "signout";
				
		public static const LOGIN_MSG:String = "Enter username and password";
		
		public static const MANDATORYMSG : String = "Enter all mandatory fields";
		
		
	}
}
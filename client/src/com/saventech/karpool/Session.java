package com.saventech.karpool;



import android.content.SharedPreferences;

public class Session 
{
	private String username;
	private String password;
	private String Previouscheckbox;
	
	//private SharedPreferences mPreferences; 
	 public boolean checkinfo(SharedPreferences mPreferences)
	    {
		 //mPreferences = getSharedPreferences("CurrentUser", MODE_PRIVATE); 
	    	boolean username_set = mPreferences.contains("UserName");
	        boolean password_set = mPreferences.contains("PassWord");
	        if ( username_set || password_set ) {
	              return true;
	        } 
	    	return false;
	    }
	 public String getUsername(SharedPreferences mPreferences)
	 {
		 
		 username= mPreferences.getString("UserName","uid");
		
		// System.out.println(username);
		 return username;
	 }
	 public String getPassword(SharedPreferences mPreferences)
	 {
		 
		 password= mPreferences.getString("PassWord","dd");
		
		// System.out.println(username);
		 return password;
	 }
	 public void removeSession(SharedPreferences mPreferences)
	 {
		 SharedPreferences.Editor editor=mPreferences.edit();
         editor.remove("UserName");
         editor.remove("PassWord");
        /* editor.remove("ridersource");
         editor.remove("riderdestination");
         editor.remove("ridersettime");*/
         /*editor.remove("driversource");
         editor.remove("driverdestination");
         editor.remove("driversettime");
         editor.remove("driverseats");*/
         editor.remove("checkboxesclicked");
         editor.commit();
	 }
	
	 public boolean ischeckboxesclicked(SharedPreferences mPreferences)
	 {
		 boolean checkboxes_clicked=mPreferences.contains("checkboxesclicked");
		 if(checkboxes_clicked)
		 {
			 return true;
		 }
		 return false;
	 }
	 public String getCheckBoxesClicked(SharedPreferences mPreferences)
	 {
		 Previouscheckbox=mPreferences.getString("checkboxesclicked", "cc");
		 System.out.println("getcheckboxesclicked--------"+Previouscheckbox);
		 return Previouscheckbox.toString().trim();
	 }
	 public void saveCheckBoxesClicked(SharedPreferences mPreferences,String checkboxes)
	 {

		// System.out.println(checkboxes+"SEEEEEEEEEEEEEEEEEEEEEEEEEEESSSSSSSSSSIIIIIIIIIIONNNNNNNNNNNNNN");
		 SharedPreferences.Editor editor=mPreferences.edit();
		 
		 System.out.println(checkboxes+"SEEEEEEEEEEEEEEEEEEEEEEEEEEESSSSSSSSSSIIIIIIIIIIONNNNNNNNNNNNNN");
         editor.putString("checkboxesclicked", checkboxes);
         editor.commit();
         System.out.println(mPreferences.getString("checkboxesclicked","cd")+" ############################");

	 }
	 public void storemode(SharedPreferences mPreferences,String mode)
	 {
         SharedPreferences.Editor editor=mPreferences.edit();
		 
		 System.out.println(mode+" :In session");
         editor.putString("Mode",mode.toString().trim());
         editor.commit();
	 }
	 public String getMode(SharedPreferences mPreferences)
	 {
		 return mPreferences.getString("Mode", "md");
	 }

}

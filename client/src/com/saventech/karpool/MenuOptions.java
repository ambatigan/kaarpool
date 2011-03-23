package com.saventech.karpool;

import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;

public class MenuOptions extends Activity
{
	private static final int ABOUT = Menu.FIRST ;
	private static final int CONTACT_US = Menu.FIRST+1 ;
	private static final int INSTRUCTIONS = Menu.FIRST+2 ;
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		menu.add(0, ABOUT, 0, "ABOUT");	
		menu.add(0, CONTACT_US, 0, "CONTACT_US");	
		menu.add(0, INSTRUCTIONS, 0, "INSTRUCTIONS");		
		return true;
	}
	
	/**
	 *  For the selected items in the option menu of quizActivity
	 */
	public boolean onOptionsItemSelected(MenuItem item) 
	{
		switch(item.getItemId())
		{
		case ABOUT:
			return true;
		case CONTACT_US:
			return true;
		case INSTRUCTIONS:
			return true;	   
		}
		return false;
	}
	
	
}

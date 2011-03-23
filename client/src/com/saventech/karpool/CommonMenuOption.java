package com.saventech.karpool;


import android.view.Menu;
import android.view.MenuItem;

public class CommonMenuOption extends MenuOptions
{
	private static final int PREFERENCES = Menu.FIRST+3 ;
	private static final int MORE = Menu.FIRST+4;
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		super.onCreateOptionsMenu(menu);
		menu.add(0, PREFERENCES, 0, "PREFERENCES");	
		menu.add(0, MORE, 0, "MORE");	
		return true;
	}
	
	/**
	 *  For the selected items in the option menu of quizActivity
	 */
	public boolean onOptionsItemSelected(MenuItem item) 
	{
		switch(item.getItemId())
		{
		case PREFERENCES:
			return true;
		case MORE:
			return true;
	   
		}
		return false;
	}
	
	
	

}
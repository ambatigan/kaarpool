package com.saventech.karpool;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;

/*
 * Project: Karpool
 * Package: com.saventech.karpool
 * File: Karpool.java
 * Date: Mar 25, 2011
 * Description: It is the welcome page which stays for few seconds
 */
public class Karpool extends  Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
        Thread registerThread = new Thread() {
            @Override
            public void run() {
               try {
                  int waited = 0;
                  while (waited < 3000) {
                     sleep(50);
                     waited += 100;
                  }
               } catch (InterruptedException e) {
                  // do nothing
               } finally {
                  finish();
                  Intent i = new Intent();
                  i.setClassName("com.saventech.karpool",
                                 "com.saventech.karpool.MainActivity");
                  startActivity(i);
               }
            }
         };
         registerThread.start();
      

    }
    
//    public boolean onKeyDown(int keyCode, KeyEvent event) 
//	{
//		if(keyCode == KeyEvent.KEYCODE_BACK)
//		{
//			//finish();
//			Intent setIntent = new Intent(Intent.ACTION_MAIN);
//		    setIntent.addCategory(Intent.CATEGORY_HOME);
//		    setIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//		    startActivity(setIntent); 
//		    //finish();
//			//return true;
//		}
//		return super.onKeyDown(keyCode, event);
//	}
    
	
}
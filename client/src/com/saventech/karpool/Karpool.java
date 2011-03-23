package com.saventech.karpool;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

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
                     sleep(200);
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

	
}
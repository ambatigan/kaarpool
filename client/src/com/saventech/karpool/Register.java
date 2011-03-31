package com.saventech.karpool;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;

import com.facebook.android.AsyncFacebookRunner;
import com.facebook.android.DialogError;
import com.facebook.android.Facebook;
import com.facebook.android.FacebookError;
import com.facebook.android.Util;
import com.facebook.android.AsyncFacebookRunner.RequestListener;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Register extends  MenuOptions  implements android.view.View.OnClickListener {
    /** Called when the activity is first created. */
	
	private Facebook mFacebook;
	public static final String APP_ID = "196351390404598";

    private static final String[] PERMISSIONS =
        new String[] {"publish_stream", "read_stream", "offline_access"};
    private Handler mHandler = new Handler();
    private ProgressDialog mSpinner;
   
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        if (APP_ID == null) {
            Util.showAlert(this,
            "Warning", "Facebook Applicaton ID must be set...");
        }
        
        setContentView(R.layout.newscreen);
        mFacebook = new Facebook(APP_ID);
        findViewById(R.id.facebook).setOnClickListener(this);
        findViewById(R.id.gmail).setOnClickListener(this);
        findViewById(R.id.twitter).setOnClickListener(this);
        findViewById(R.id.sysgen).setOnClickListener(this);
        Log.i("Registration", "You are now in registration page");
    }
	
    /**
     * Listener for login dialog completion status
     */
    private final class LoginDialogListener implements
            com.facebook.android.Facebook.DialogListener {

        /**
         * Called when the dialog has completed successfully
         */
        public void onComplete(Bundle values) {
            // Process onComplete
            Log.d("FB Sample App", "LoginDialogListener.onComplete()");
            // Dispatch on its own thread
            mHandler.post(new Runnable() {
                public void run() {
                	//Toast.makeText(Register.this, "logindialog complete", Toast.LENGTH_LONG).show();
                	Intent facebook = new Intent(Register.this, JourneyDetails.class);
        			startActivity(facebook);
                    //mText.setText("Facebook login successful. Press Menu...");
                }
            });
        }

        /**
         *
         */
        public void onFacebookError(FacebookError error) {
            // Process error
            Log.d("FB Sample App", "LoginDialogListener.onFacebookError()");
        }

        /**
         *
         */
        public void onError(DialogError error) {
            // Process error message
            Log.d("FB Sample App", "LoginDialogListener.onError()");
        }

        /**
         *
         */
        public void onCancel() {
            // Process cancel message
            Log.d("FB Sample App", "LoginDialogListener.onCancel()");
   }
    }

    /**
     * Listener for logout status message
     */
    private class LogoutRequestListener implements RequestListener {

        /** Called when the request completes w/o error */
        public void onComplete(String response) {

            // Only the original owner thread can touch its views
           /* SampleApp.this.runOnUiThread(new Runnable() {
                public void run() {
                    //mText.setText("Thanks for using FB Sample App. Bye bye...");
                    //friends.clear();
                    //friendsArrayAdapter.notifyDataSetChanged();
                }
            });*/

            // Dispatch on its own thread
            mHandler.post(new Runnable() {
                public void run() {
                }
            });
        }

    
        public void onFacebookError(FacebookError e) {
            // Process Facebook error message
        }

       
        public void onFileNotFoundException(FileNotFoundException e) {
            // Process Exception
        }

       
        public void onIOException(IOException e) {
            // Process Exception
        }

       
        public void onMalformedURLException(MalformedURLException e) {
            // Process Exception
        }

		public void onComplete(String response, Object state) {
			// TODO Auto-generated method stub
			
		}

		public void onIOException(IOException e, Object state) {
			// TODO Auto-generated method stub
			
		}

		public void onFileNotFoundException(FileNotFoundException e,
				Object state) {
			// TODO Auto-generated method stub
			
		}

		public void onMalformedURLException(MalformedURLException e,
				Object state) {
			// TODO Auto-generated method stub
			
		}

		public void onFacebookError(FacebookError e, Object state) {
			// TODO Auto-generated method stub
			
		}

    }
	
	public void onClick(View V) 
	{
		switch(V.getId())
		{
		case R.id.facebook:
			//Intent facebook = new Intent(Register.this, Registerwithopenid.class);
			//startActivity(facebook);
			if (mFacebook.isSessionValid()) {
				Toast.makeText(this, "user already logged in", Toast.LENGTH_LONG).show();
                AsyncFacebookRunner asyncRunner = new AsyncFacebookRunner(mFacebook);
                asyncRunner.logout(this.getBaseContext(), new LogoutRequestListener());
            } else {
                // Toggle the button state.
                //  If coming from logout transition to login (authorize).
            	Toast.makeText(this, "user log in", Toast.LENGTH_LONG).show();
                mFacebook.authorize(this, PERMISSIONS, new LoginDialogListener());
            }
			break;
		case R.id.gmail:
			Intent gmail = new Intent(Register.this, Registerwithopenid.class);
			startActivity(gmail);
			break;
		case R.id.twitter:
			Intent twitter = new Intent(Register.this, Registerwithopenid.class);
			startActivity(twitter);
			break;
		case R.id.sysgen:
			Intent sysgen = new Intent(Register.this, Registerwithsysid.class);
			startActivity(sysgen);
			break;
		}
		
		
	}
	
	 
}

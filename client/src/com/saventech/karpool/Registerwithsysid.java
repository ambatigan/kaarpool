package com.saventech.karpool;

/**
 * Project: Karpool
 * Package: com.saventech.karpool
 * File: Acknowledgement.java
 * Date: Apr 05, 2011
 */
import java.io.ByteArrayOutputStream;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class Registerwithsysid extends  MenuOptions implements OnClickListener
{
	private static final int SELECT_PICTURE = 1;
	private String value="";
	private String selectedImagePath;
	ImageButton checksystemavailability;
	private ImageButton mPickDate;
	private Button sysimageupload;
	private ImageView imageview;
    private int mYear;
    private int mMonth;
    private int mDay;

    static final int DATE_DIALOG_ID = 0;
	Controller controller=null;
	boolean checksysidflag=false;
	boolean checksyspwdflag=false;
	boolean checksysaddressflag=false;
	boolean checksysdobflag=false;
	boolean checksysmaleflag=false;
	boolean checksysfemaleflag=false;
	boolean checksysmobileflag=false;
	EditText sysuserid;
	EditText sysuserpwd;
	EditText sysusermobile;
	EditText sysuseraddress;
	EditText sysuserdob;
	RadioButton sysusergendermale;
	RadioButton sysusergenderfemale;
	boolean validateflag;
	String validatestring;
	
	
	 public void onCreate(Bundle savedInstanceState) 
	 {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.sysidscreen);
	        
	        controller=new Controller();
	        Log.i("Registration", "You are now in registration page");
	        sysuserid=(EditText)findViewById(R.id.sysuseridtxt);
	        
	        //Applied on focus change to sysid edit text
	        
	        sysuserid.setOnFocusChangeListener(new OnFocusChangeListener() 
	        {

		        	public void onFocusChange(View v, boolean hasFocus) 
		        	{
		        		focus(v);
		        	    
		        	}
             });
	        
	        sysuserpwd=(EditText)findViewById(R.id.regsysidpwd);
	        sysusermobile=(EditText)findViewById(R.id.regsysidmobile);
	        sysuseraddress=(EditText)findViewById(R.id.regsysidaddress);
	        sysuserdob=(EditText)findViewById(R.id.regsysiddob);
	        sysuserdob.setEnabled(false);
	        sysusergendermale=(RadioButton)findViewById(R.id.regsysidmale);
	        sysusergenderfemale=(RadioButton)findViewById(R.id.regsysidfemale);
	        
	        //checksystemavailability=(ImageButton)findViewById(R.id.checksysidavailability);
	        
	        mPickDate = (ImageButton) findViewById(R.id.pickDate);
	        sysimageupload=(Button)findViewById(R.id.sysuploadimage);
	        sysimageupload.setOnClickListener(this);
	        imageview=(ImageView)findViewById(R.id.sysimage);

	        // add a click listener to the button
	        mPickDate.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View v) {
	                showDialog(DATE_DIALOG_ID);
	            }
	        });

	        // get the current date
	        final Calendar c = Calendar.getInstance();
	        mYear = c.get(Calendar.YEAR);
	        mMonth = c.get(Calendar.MONTH);
	        mDay = c.get(Calendar.DAY_OF_MONTH);
	        updateDisplay();

	        
	        //checksystemavailability.setOnClickListener(this);
	        Button b = (Button)findViewById(R.id.sysregsubmit);
	        b.setOnClickListener(this);

	    }
	 
	 
	 //----Gallery functions starts--------------
	 public void onActivityResult(int requestCode, int resultCode, Intent data) {
	        if (resultCode == RESULT_OK) {
	            if (requestCode == SELECT_PICTURE) {
	                Uri selectedImageUri = data.getData();
	                selectedImagePath = getPath(selectedImageUri);
	                System.out.println("Image Path : " + selectedImagePath);
	                imageview.setImageURI(selectedImageUri);
	            }
	        }
	    }
	 
	 
	 //Getting image path from gallery
	 
	    public String getPath(Uri uri) {
	        String[] projection = { MediaStore.Images.Media.DATA };
	        Cursor cursor = managedQuery(uri, projection, null, null, null);
	        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
	        cursor.moveToFirst();
	        return cursor.getString(column_index);
	    }
	 
	 
	 //-------Gallery functions ends--------
	 
	    // ----------bitmap code starts----------
	    
	    public String bitmapcode()
	    {
	    	Bitmap bitmapOrg = BitmapFactory.decodeFile(selectedImagePath);
	    			ByteArrayOutputStream bao = new ByteArrayOutputStream();
	    			bitmapOrg.compress(Bitmap.CompressFormat.JPEG, 90, bao);
	    			byte [] ba = bao.toByteArray();
	    			String ba1=Base64.encodeToString(ba, Base64.DEFAULT);
	    			return ba1;

	    }
	    
	    
	    //------------bitmap code ends------------
	    
	 // on focus starts: validating the sysid by on focus
        public void focus(View v)
        {
        	Log.i("Registerwithsysid_availableusers", "Checking for available users");
		     if(sysuserid.getText().toString().length()!=0)
		     {
				 checksysidflag=controller.Availablesysids(sysuserid.getText().toString());
				 if(checksysidflag)
				 {
					 TextView warn = (TextView)findViewById(R.id.availablesysid);
			    	 warn.setText("NO");
			    	 checksysidflag=false;
				 }
				 else
				 {
					 TextView warn = (TextView)findViewById(R.id.availablesysid);
					Pattern p=Pattern.compile("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
				     Matcher m = p.matcher(sysuserid.getText().toString()+"@karpool.com");
					 boolean matchFound = m.lookingAt();
					 
					 if(matchFound)
					 {
						 if(sysuserid.getText().toString().length()<=8)
						 {
							 checksysidflag=matchFound;
							 warn.setText("YES, "+sysuserid.getText().toString()+"@karpool.com");
						 }
						 else
						 {
							 checksysidflag=false;
							 warn.setText("NO");
						 }
					 }
					 else
					 {
						 checksysidflag=matchFound;
						 warn.setText("NO");
					 }
				 }
		     }
		     else
		     {
		    	 TextView warn = (TextView)findViewById(R.id.availablesysid);
		    	 warn.setText(" "); 
		    	 checksysidflag=false;
		     }
        	
        }
     
        //--------on focus ends--------------
        
     //---------Calendar starts: updating the date of birth from calendar-------------
	 private void updateDisplay() 
	 {
	        sysuserdob.setText(
	            new StringBuilder()
	                    // Month is 0 based so add 1
	                    .append(mMonth + 1).append("-")
	                    .append(mDay).append("-")
	                    .append(mYear).append(" "));
	    }
	 

	 private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() 
	 {

             

			public void onDateSet(DatePicker view, int year, 
                    int monthOfYear, int dayOfMonth) {
				 mYear = year;
                 mMonth = monthOfYear;
                 mDay = dayOfMonth;
                 updateDisplay();
				// TODO Auto-generated method stub
				
			}
         };
         
         
         @Override
         protected Dialog onCreateDialog(int id) {
             switch (id) {
             case DATE_DIALOG_ID:
                 return new DatePickerDialog(this,
                             mDateSetListener,
                             mYear, mMonth, mDay);
             }
             return null;
         }
         
      //------Calendar ends----------
	 
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId())
		{
		
		case R.id.sysregsubmit:
			
			 //flags for validation of different edit texts
			 checksyspwdflag=false;
			 checksysaddressflag=false;
			 checksysdobflag=false;
			 checksysmaleflag=false;
			 checksysfemaleflag=false;
			 checksysmobileflag=false;
			   
		     validatestring=new String();
		     
		     
		     // validating password 
		     
		     if(sysuserpwd.getText().toString().length()>=5)
		     {
		    	 checksyspwdflag=true;
		     }
		     else
		     {
		    	 validatestring="-> Type the correct password(length>=5)\n";
		     }
		     
		     // validating address
		     
		     if(sysuseraddress.getText().toString().length()<=200 && sysuseraddress.getText().length()>0)
		     {
		    	 checksysaddressflag=true;
		     }
		     else
		     {
		    	 validatestring=validatestring+"-> Address length should be one to two hundred\n";
		     }
		     
		     // validating userid's
		     
		     if(!checksysidflag)
		     {
		    	 validatestring=validatestring+"-> Pls select available users only(length<=8) \n";
		     }
		     
		     // validating gender buttons 
		     
		     if(sysusergenderfemale.isChecked())
		     {
		    	 checksysfemaleflag=true; 
		    	// System.out.println(sysusergendermale.getText().toString()+"  oooooooooooooooooooooooooooooooooo");
		     }
		     else if(sysusergendermale.isChecked())
		     {
		    	 checksysmaleflag=true; 
		     }
		     else
		     {
		    	 validatestring=validatestring+"-> Pls select your gender\n";
		     }
		     
		     // validating date of birth
		     
		     Pattern mobile = Pattern.compile("\\d{11}");
		     Matcher match = mobile.matcher(sysusermobile.getText().toString());
			 boolean matchfound= match.lookingAt();
			 if(matchfound)
			 {
				 if(sysusermobile.getText().toString().charAt(0)=='0' && (sysusermobile.getText().toString().length()==10 || sysusermobile.getText().toString().length()==11))
				 {
					 checksysmobileflag=true;
				 }
				 else
				 {
					 validatestring=validatestring+"-> Pls enter correct mobile number(start with zero) \n";
				 }
				 
			 }
			 else
			 {
				 validatestring=validatestring+"-> Pls enter correct mobile number (start with zero)\n";
			 }
		     
			 String imagedata=bitmapcode();
		     if(checksysaddressflag && checksyspwdflag &&  checksysidflag && (checksysmaleflag || checksysfemaleflag) && checksysmobileflag)
			     {
		    	       String response="";
		    	       if(checksysmaleflag)
		    	       {
		    	    	   response=controller.Sysid_registration(sysuserid.getText().toString()+"@karpool.com", sysuserpwd.getText().toString(), sysuserdob.getText().toString(), sysuseraddress.getText().toString(), sysusermobile.getText().toString(), sysusergendermale.getText().toString(),imagedata.toString());
		    	       }
		    	       else
		    	       {
		    	    	   response=controller.Sysid_registration(sysuserid.getText().toString()+"@karpool.com", sysuserpwd.getText().toString(), sysuserdob.getText().toString(), sysuseraddress.getText().toString(), sysusermobile.getText().toString(), sysusergenderfemale.getText().toString(),imagedata.toString());
		    	       }
		    	 
		    	      System.out.println("response from tomcat6 is: "+response);
					 Intent intent=new Intent(Registerwithsysid.this,JourneyDetails.class);
			         startActivity(intent);
	             }
		     else
		     {
		    	 Toast.makeText(Registerwithsysid.this, validatestring, Toast.LENGTH_LONG).show();
		     }
			 break;
			 
		case R.id.sysuploadimage:
			//System.out.println("88888888888888888");
			final CharSequence[] items = {"Camera", "Gallery"};
            //Prepare the list dialog box
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            //Set its title
            builder.setTitle("Choose List");
            //Set the list items and assign with the click listener
            //System.out.println("88888888888888888");
            builder.setItems(items, new DialogInterface.OnClickListener() {
                // Click listener
                public void onClick(DialogInterface dialog, int item) {
                    if(items[item]=="Gallery")
                    {
                      Intent intent = new Intent();
                      intent.setType("image/*");
                      intent.setAction(Intent.ACTION_GET_CONTENT);
                      startActivityForResult(Intent.createChooser(intent,"Select Picture"), SELECT_PICTURE);                	                    	
                    }
                }
            });
            AlertDialog alert = builder.create();
            //display dialog box
            alert.show();
	        
			break;
			  
		
			 // Trying to implement on focus 
			 
//		case R.id.checksysidavailability:
//			     
//			Log.i("Registerwithsysid_availableusers", "Checking for available users");
//		     if(sysuserid.getText().toString().length()!=0)
//		     {
//				 checksysidflag=controller.Availablesysids(sysuserid.getText().toString());
//				 //System.out.println(checksysidflag+"000000000000000000000000000000000000000");
//				 if(checksysidflag)
//				 {
//					 TextView warn = (TextView)findViewById(R.id.availablesysid);
//			    	 warn.setText("NO");
//			    	 checksysidflag=false;
//				 }
//				 else
//				 {
//					 TextView warn = (TextView)findViewById(R.id.availablesysid);
//			    	// Pattern p = Pattern.compile("^[a-z]+.+@[a-z]+.[a-z]{2,6}$");
//					Pattern p=Pattern.compile("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
//				     Matcher m = p.matcher(sysuserid.getText().toString()+"@karpool.com");
//					 boolean matchFound = m.lookingAt();
//					 
//					 if(matchFound)
//					 {
//						 if(sysuserid.getText().toString().length()<=8)
//						 {
//							 checksysidflag=matchFound;
//							 warn.setText("YES, "+sysuserid.getText().toString()+"@karpool.com");
//						 }
//						 else
//						 {
//							 checksysidflag=false;
//							 warn.setText("NO");
//						 }
//						 
//						// Toast.makeText(Registerwithsysid.this, "Your id not following validations", Toast.LENGTH_LONG).show();
//					 }
//					 else
//					 {
//						 checksysidflag=matchFound;
//						 warn.setText("NO");
//					 }
//				 }
//		     }
//		     else
//		     {
//		    	 TextView warn = (TextView)findViewById(R.id.availablesysid);
//		    	 warn.setText(" "); 
//		    	 checksysidflag=false;
//		     }
//		     break;
		
		}
		     
	}
}

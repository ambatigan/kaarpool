package com.saventech.karpool;

/**
 * Project: Karpool
 * Package: com.saventech.karpool
 * File: Acknowledgement.java
 * Date: Apr 05, 2011
 */
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
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
	@SuppressWarnings("unused")
	private String value="";
	private String selectedImagePath="";
	ImageButton checksystemavailability;
	private ImageButton mPickDate;
	private Button sysimageupload;
	private ImageView imageview;
    private int mYear;
    private int mMonth;
    private int mDay;
    private String systemid="@kaarpool.com";

    static final int DATE_DIALOG_ID = 0;
	Controller controller=null;
	UploadandCompressImage uploadimage=null;
	
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
	Validations mobilevalidate;
	
	
	 public void onCreate(Bundle savedInstanceState) 
	 {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.sysidscreen);
	        
	        controller=new Controller();
	        mobilevalidate=new Validations();
	        uploadimage=new UploadandCompressImage();
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
	 
//	    // ----------bitmap code starts----------
//	    
//	    public String bitmapcode()
//	    {
//	    	try{
//	    		if(selectedImagePath.length()==0)
//	    		{
//	    			//String imagepath=getResources().getDrawable(R.drawable.default1).toString();
//	    			//String fname=this.getFilesDir().getAbsolutePath()+"/default1.jpeg";
//	    			Log.i("Registerwithsysid", "Default image is selected as user image");
//	    			//System.out.println("select image   "+getResources().getDrawable(R.drawable.default1)+" "+fname);
//	    			//Bitmap imagethumbnail=BitmapFactory.decodeFile(path);  //complete file path
//	    			
//	    			
//	    			
//	    			
//	    			Bitmap bitmapOrg = BitmapFactory.decodeResource(getResources(), R.drawable.default1); 
//	    			bitmapOrg=Bitmap.createScaledBitmap(bitmapOrg, 40, 40, true);
//	    			ByteArrayOutputStream bao = new ByteArrayOutputStream();
//	    			bitmapOrg.compress(Bitmap.CompressFormat.JPEG, 90, bao);
//	    			byte [] ba = bao.toByteArray();
//	    			String ba1=Base64.encodeToString(ba, Base64.DEFAULT);
//	    			System.out.println(ba1.length()+"****************************************************************");
//	    			return ba1;
//	    		}
//	    		else
//	    		{
//		    		//System.out.println(selectedImagePath+"88888888888888888888888888888888888888888888888");
//	    			Log.i("Registerwithsysid", "Image Selected from Gallery");
//		    		Bitmap bitmapOrg = BitmapFactory.decodeFile(selectedImagePath);
//		    		bitmapOrg=Bitmap.createScaledBitmap(bitmapOrg, 40, 40, true);
//		    		//bitmapOrg=Bitmap.createScaledBitmap(bitmapOrg, 40, 40, true);
//	    			ByteArrayOutputStream bao = new ByteArrayOutputStream();
//	    			bitmapOrg.compress(Bitmap.CompressFormat.JPEG, 90, bao);
//	    			byte [] ba = bao.toByteArray();
//	    			String ba1=Base64.encodeToString(ba, Base64.DEFAULT);
//	    			System.out.println(ba1.length()+"****************************************************************");
//	    			return ba1;
//	    		}
//	    		
//	    	}
//	    	catch(Exception e)
//	    	{
//	    		e.printStackTrace();
//	    		return "";
//	    	}
//	    	
//
//	    }
//	    
//	    
//	    //------------bitmap code ends------------
	    
	 // on focus starts: validating the sysid by on focus
        public void focus(View v)
        {
        	Log.i("Registerwithsysid_availableusers", "Checking for available users");
		     if(sysuserid.getText().toString().length()!=0)
		     {
				 checksysidflag=controller.Availablesysids(sysuserid.getText().toString()+systemid);
				 System.out.println(checksysidflag+"                    0000000000000000000000000000000000000000000000");
				 if(checksysidflag)
				 {
					 TextView warn = (TextView)findViewById(R.id.availablesysid);
			    	 warn.setText("NO");
			    	 checksysidflag=false;
				 }
				 else
				 {
					 TextView warn = (TextView)findViewById(R.id.availablesysid);
					Pattern p=Pattern.compile("^[_A-Za-z0-9]+(\\.[_A-Za-z0-9]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
				     Matcher m = p.matcher(sysuserid.getText().toString()+"@karpool.com");
					 boolean matchFound = m.lookingAt();
					 
					 if(matchFound)
					 {
						 if(sysuserid.getText().toString().length()<=8)
						 {
							 checksysidflag=matchFound;
							 warn.setText("YES, "+sysuserid.getText().toString()+systemid);
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
		     
		     
		     //--------------validating password starts-----------------
		     checksyspwdflag=mobilevalidate.passwordValidation(sysuserpwd.getText().toString().trim());
		     if(!checksyspwdflag)
		     {
		    	 validatestring="-> Type the correct password(length>=5)\n";
		     }		     
		     // validating address
		     checksysaddressflag=mobilevalidate.addressValidation(sysuseraddress.getText().toString().trim());
		     if(!checksysaddressflag)
		     {
		    	 validatestring=validatestring+"-> Address length should be one to two hundred\n";
		     }
		     
		     // validating userid's
		     
		     if(!checksysidflag)
		     {
		    	 validatestring=validatestring+"-> Pls select available users only(length<=8) \n";
		     }
		     
		     //--------validating gender buttons starts--------------
		     checksysfemaleflag=mobilevalidate.genderValidation(sysusergenderfemale.isChecked());
		     checksysmaleflag=mobilevalidate.genderValidation(sysusergendermale.isChecked());
		     if(!checksysfemaleflag && !checksysmaleflag)
		     {
		    	 System.out.println(checksysmaleflag+"------------------"+checksysmaleflag+"%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
		    	 validatestring=validatestring+"-> Pls select your gender\n";
		     }
		     
		     //-----------validating gender buttons ends------------------
		     //--------validating mobile number -----------------
		     checksysmobileflag= mobilevalidate.mobileValidate(sysusermobile.getText().toString().trim());
		     if(!checksysmobileflag)
				{
					validatestring=validatestring+"-> Pls enter correct mobile number(start with zero and follows by 10 digits) \n";
				}
		     
		     //-----------validating mobile number ends---------------
			 //------------uploading image code starts-------------------
			 System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^"+BitmapFactory.decodeResource(getResources(), R.drawable.default1));
			 String imagedata=uploadimage.bitmapcode(selectedImagePath, BitmapFactory.decodeResource(getResources(), R.drawable.default1));
			 //------------uploading image code ends-----------------
			 
		     if(checksysaddressflag && checksyspwdflag &&  checksysidflag && (checksysmaleflag || checksysfemaleflag) && checksysmobileflag)
			     {
		    	       String response="";
		    	       if(checksysmaleflag)
		    	       {
		    	    	   response=controller.Sysid_registration(sysuserid.getText().toString()+systemid, sysuserpwd.getText().toString(), sysuserdob.getText().toString(), sysuseraddress.getText().toString(), sysusermobile.getText().toString(), sysusergendermale.getText().toString(),imagedata.toString());
		    	       }
		    	       else
		    	       {
		    	    	   response=controller.Sysid_registration(sysuserid.getText().toString()+systemid, sysuserpwd.getText().toString(), sysuserdob.getText().toString(), sysuseraddress.getText().toString(), sysusermobile.getText().toString(), sysusergenderfemale.getText().toString(),imagedata.toString());
		    	       }
		    	 
		    	      System.out.println("response from tomcat6 is: "+response);
					 Intent intent=new Intent(Registerwithsysid.this,JourneyDetails.class);
					 intent.putExtra("RegisterUsername", sysuserid.getText().toString()+systemid);
					 intent.putExtra("RegisterPassword", sysuserpwd.getText().toString());
					 intent.putExtra("receiver", "notify");
			         startActivity(intent);
	             }
		     else
		     {
		    	 Toast.makeText(Registerwithsysid.this, validatestring, Toast.LENGTH_LONG).show();
		     }
			 break;
			 
		case R.id.sysuploadimage:
			final CharSequence[] items = {"Camera", "Gallery"};
            //Prepare the list dialog box
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            //Set its title
            builder.setTitle("Select");
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
		}
		     
	}
}

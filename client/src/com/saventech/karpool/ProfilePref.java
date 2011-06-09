package com.saventech.karpool;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ProfilePref extends  Activity implements OnClickListener {
    /** Called when the activity is first created. */
    String value="";
    Controller controller=null; 
    Validations validate=null;
    String username="";
    private static final int SELECT_PICTURE = 1;
    private String selectedImagePath="";
    private ImageView userImage;
    UploadandCompressImage uploadimage=null;
    
    EditText userPwd;
    EditText userMobile;
    EditText userAdd ;
    EditText userId;
    String imagedata="";
    Bitmap decodedByte;
    private SharedPreferences mPreferences; 
	Session session;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.profilepref);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.mytitle);
        
        final TextView leftText = (TextView) findViewById(R.id.left_text);
        final TextView rightText = (TextView) findViewById(R.id.right_text);

        leftText.setText("Kaarpool");
        leftText.setTypeface(null, Typeface.BOLD);
        
        session=new Session();
	    mPreferences = getSharedPreferences("CurrentUser", MODE_PRIVATE); 
		if(!session.checkinfo(mPreferences))
		{
			Intent intent=new Intent(ProfilePref.this,Login.class);
			startActivity(intent);
		
		}
       
        username = session.getUsername(mPreferences);
        String name[]= username.split("@");
        rightText.setText(name[0]);
        
        Log.i("ProfilePref",username+ "  in sessions");
        controller=new Controller(); 
        validate=new Validations();
        uploadimage=new UploadandCompressImage();
        String totalString = controller.getUserPreferences(username) ;
        String[] fields = totalString.split(":");
        
        userId = (EditText)findViewById(R.id.useridtxt);
        userId.setText(fields[0]);
        userId.setEnabled(false);
        
        userPwd = (EditText)findViewById(R.id.pwd);
        userPwd.setText(fields[1]);
        userPwd.setEnabled(false);
        
        userMobile = (EditText)findViewById(R.id.mobile);
        userMobile.setText(fields[2]);
        
        userAdd = (EditText)findViewById(R.id.addtxt);
        userAdd.setText(fields[3]);
        userImage = (ImageView)findViewById(R.id.image);
       
        //----------------- for decoding the string to image ------------------
        
        imagedata = fields[4];
        byte[] decodedString = Base64.decode(imagedata, Base64.DEFAULT);
        decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        userImage.setImageBitmap(decodedByte);
        
        Button bb =(Button)findViewById(R.id.change);
        bb.setOnClickListener(this);
        Button back =(Button)findViewById(R.id.backpref);
        back.setOnClickListener(this);
        Button save =(Button)findViewById(R.id.savepropref);
        save.setOnClickListener(this);
        Button imageUpload =(Button)findViewById(R.id.imagesubmit);
        imageUpload.setOnClickListener(this);
        
    }

    @SuppressWarnings("unused")
	private class OnReadyListener implements CustomizeDialog.ReadyListener {
        public void ready(String changedPwd) {
        	EditText ed = (EditText)findViewById(R.id.pwd);
        	ed.setText(changedPwd);
        	
            Toast.makeText(ProfilePref.this, changedPwd, Toast.LENGTH_LONG).show();
        }
    }
	public void onClick(View v) {
	    switch(v.getId())
		{
		case R.id.change:
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			final EditText input = new EditText(this);
			builder.setView(input);
			builder.setMessage("Enter password")
			       .setCancelable(false)
			       .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			           public void onClick(DialogInterface dialog, int id) {
			        	   value = input.getText().toString().trim();
			        	   EditText ed = (EditText)findViewById(R.id.pwd);
					    	ed.setText(value);
					    	
			           }
			       });
			       
			AlertDialog alert = builder.create();
			alert.show();
			break;
		case R.id.backpref:
            Intent backpref = new Intent(ProfilePref.this, Preferences.class);
			startActivity(backpref);
			break;
		case R.id.savepropref:
			String validatestring="";
			boolean mobileflag=false;
			boolean passwordflag=false;
			mobileflag=validate.mobileValidate(userMobile.getText().toString().trim());
			if(!mobileflag)
			{
				validatestring=validatestring+"-> Pls enter correct mobile number(start with zero and follows by 10 digits) \n";
			}
			passwordflag=validate.passwordValidation(userPwd.getText().toString().trim());
			if(!passwordflag)
			{
				validatestring=validatestring+"-> Make sure password length >=5";
			}
			/*Pattern mobile = Pattern.compile("\\d{11}");
		     Matcher match = mobile.matcher(userMobile.getText().toString());
			 boolean matchfound= match.lookingAt();
			 if(matchfound)
			 {
				 
				 if(userMobile.getText().toString().charAt(0)=='0' && (userMobile.getText().toString().length()==10 || userMobile.getText().toString().length()==11))
				 {
					 
					 mobileflag=true;
				 }
				 else
				 {
					
					 validatestring=validatestring+"-> Pls enter correct mobile number(start with zero and follows by 10 digits) \n";
				 }
			 }
			 else
			 {
				 validatestring=validatestring+"-> Pls enter correct mobile number(start with zero and follows by 10 digits) \n";
			 }*/
			 /*if(userPwd.getText().toString().trim().length()>=5)
			 {
				 passwordflag=true;
			 }
			 else
			 {
				 //System.out.println(matchfound+"-------"+userMobile.length()+"^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
				 validatestring=validatestring+"-> Make sure password length >=5";
			 }*/
			 if(mobileflag && passwordflag )
			 {
				 if(!imagedata.equals(""))
				   {
					   imagedata=uploadimage.bitmapcode(selectedImagePath, decodedByte);
					   System.out.println(imagedata);  
				   }
				   else
				   {
					   imagedata=uploadimage.bitmapcode(selectedImagePath, BitmapFactory.decodeResource(getResources(), R.drawable.default1));
				   }
				//String ima = uploadimage.bitmapcode(selectedImagePath, BitmapFactory.decodeResource(getResources(), R.drawable.default1));
				controller.saveProfilePref(userId.getText().toString(),userPwd.getText().toString(),userMobile.getText().toString(),userAdd.getText().toString(),imagedata);
		        Intent savepropref = new Intent(ProfilePref.this, Preferences.class);
				startActivity(savepropref);
			 }
			 else
			 {
				 Toast.makeText(ProfilePref.this, validatestring, Toast.LENGTH_LONG).show();
			 }
			
			break;
		case R.id.imagesubmit:
			final CharSequence[] items = {"Camera", "Gallery"};
            //Prepare the list dialog box
            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            //Set its title
            builder1.setTitle("Choose List");
            builder1.setItems(items, new DialogInterface.OnClickListener() {
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
            AlertDialog alert1 = builder1.create();
            //display dialog box
            alert1.show();
	        
			break;
	}
}
	//----Gallery functions starts--------------
	 public void onActivityResult(int requestCode, int resultCode, Intent data) {
	        if (resultCode == RESULT_OK) {
	            if (requestCode == SELECT_PICTURE) {
	                Uri selectedImageUri = data.getData();
	                selectedImagePath = getPath(selectedImageUri);
	                System.out.println("Image Path : " + selectedImagePath);
	                userImage.setImageURI(selectedImageUri);
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
	 
	 
	
	    

}
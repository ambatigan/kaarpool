package com.saventech.karpool;

import java.io.ByteArrayOutputStream;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class ProfilePref extends  Activity implements OnClickListener {
    /** Called when the activity is first created. */
    String value="";
    Controller controller=null; 
    String username="";
    private static final int SELECT_PICTURE = 1;
    private String selectedImagePath="";
    private ImageView userImage;
    
    EditText userPwd;
    EditText userMobile;
    EditText userAdd ;
    EditText userId;
    private SharedPreferences mPreferences; 
	Session session;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        session=new Session();
	    mPreferences = getSharedPreferences("CurrentUser", MODE_PRIVATE); 
		if(!session.checkinfo(mPreferences))
		{
			Intent intent=new Intent(ProfilePref.this,Login.class);
			startActivity(intent);
		
		}
        setContentView(R.layout.profilepref);
        username = session.getUsername(mPreferences);
        Log.i("ProfilePref",username+ "  in sessions");
        controller=new Controller(); 
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
        
        String imagebyte = fields[4];
        byte[] decodedString = Base64.decode(imagebyte, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
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
			String ima = bitmapcode();
			controller.saveProfilePref(userId.getText().toString(),userPwd.getText().toString(),userMobile.getText().toString(),userAdd.getText().toString(),ima);
            Intent savepropref = new Intent(ProfilePref.this, Preferences.class);
			startActivity(savepropref);
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
	 
	 
	 //-------Gallery functions ends--------
	 
	    // ----------bitmap code starts----------
	    
	    public String bitmapcode()
	    {
	    	try{
	    		if(selectedImagePath.length()==0)
	    		{
	    			//String imagepath=getResources().getDrawable(R.drawable.default1).toString();
	    			//String fname=this.getFilesDir().getAbsolutePath()+"/default1.jpeg";
	    			Log.i("Registerwithsysid", "Default image is selected as user image");
	    			//System.out.println("select image   "+getResources().getDrawable(R.drawable.default1)+" "+fname);
	    			Bitmap bitmapOrg = BitmapFactory.decodeResource(getResources(), R.drawable.default1); 
	    			ByteArrayOutputStream bao = new ByteArrayOutputStream();
	    			bitmapOrg.compress(Bitmap.CompressFormat.JPEG, 90, bao);
	    			byte [] ba = bao.toByteArray();
	    			String ba1=Base64.encodeToString(ba, Base64.DEFAULT);
	    			return ba1;
	    		}
	    		else
	    		{
		    		//System.out.println(selectedImagePath+"88888888888888888888888888888888888888888888888");
	    			Log.i("Registerwithsysid", "Image Selected from Gallery");
		    		Bitmap bitmapOrg = BitmapFactory.decodeFile(selectedImagePath);
	    			ByteArrayOutputStream bao = new ByteArrayOutputStream();
	    			bitmapOrg.compress(Bitmap.CompressFormat.JPEG, 90, bao);
	    			byte [] ba = bao.toByteArray();
	    			String ba1=Base64.encodeToString(ba, Base64.DEFAULT);
	    			return ba1;
	    		}
	    		
	    	}
	    	catch(Exception e)
	    	{
	    		e.printStackTrace();
	    		return "";
	    	}
	    	

	    }
	    
	    
	    //------------bitmap code ends------------
	    

}
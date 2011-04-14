package com.saventech.karpool;





import java.io.ByteArrayOutputStream;

import android.app.Activity;
import android.app.AlertDialog;
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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

public class TravelPref extends Activity implements OnClickListener
{
	private ImageView travelprefimage;
	private Button travelprefimageupload;
	private String selectedImagePath="";
	private Button travelprefsave;
	private Button travelprefback;
	private static final int SELECT_PICTURE = 1;
	@Override
    protected void onCreate(Bundle savedInstanceState) 
    {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.travelpref);
    
    
    travelprefimage=(ImageView)findViewById(R.id.travelpref_image);
    travelprefimageupload=(Button)findViewById(R.id.travelpref_imageupload);
    travelprefsave=(Button)findViewById(R.id.travelprefsave);
    travelprefback=(Button)findViewById(R.id.travelprefback);
    travelprefsave.setOnClickListener(this);
    travelprefback.setOnClickListener(this);
    travelprefimageupload.setOnClickListener(this);
    
    Spinner spinner = (Spinner) findViewById(R.id.travelprefspinner);
    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
            this, R.array.seats, android.R.layout.simple_spinner_item);
    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    spinner.setAdapter(adapter);
    spinner.setOnItemSelectedListener(new MyOnItemSelectedListener());

			 
	}
	
	//----Gallery functions starts--------------
	 public void onActivityResult(int requestCode, int resultCode, Intent data) {
	        if (resultCode == RESULT_OK) {
	            if (requestCode == SELECT_PICTURE) {
	                Uri selectedImageUri = data.getData();
	                selectedImagePath = getPath(selectedImageUri);
	                System.out.println("Image Path : " + selectedImagePath);
	                travelprefimage.setImageURI(selectedImageUri);
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
	public class MyOnItemSelectedListener implements OnItemSelectedListener {

		public void onItemSelected(AdapterView<?> parent, View view, int pos,long id) 
		{
			Toast.makeText(parent.getContext(), "The planet is " +
	          parent.getItemAtPosition(pos).toString(), Toast.LENGTH_LONG).show();

			
		}

		public void onNothingSelected(AdapterView<?> arg0) 
		{
			// TODO Auto-generated method stub
			
		}
	}

	public void onClick(View v) 
	{
	   switch(v.getId())
	   {
	   case R.id.travelpref_imageupload:
		   
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
	   case R.id.travelprefback:
		   Intent intent = new Intent(TravelPref.this,Preferences.class);
		   startActivity(intent);
		   break;
	   case R.id.travelprefsave:
		   Log.i("TravelPref_save", "Travel preference values are storing");
		   Toast.makeText(TravelPref.this, "Saving travel preferences ", Toast.LENGTH_LONG).show();
		   String imagedata=bitmapcode();
		   System.out.println(imagedata);
	   }
		// TODO Auto-generated method stub
		
	}

	
    
}

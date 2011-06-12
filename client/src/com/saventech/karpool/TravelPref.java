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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class TravelPref extends Activity implements OnClickListener
{
	private ImageView travelprefimage;
	private Button travelprefimageupload;
	private String selectedImagePath="";
	private Button travelprefsave;
	private Button travelprefback;
	private static final int SELECT_PICTURE = 1;
	String seats = "";
	/** The ladies. */
	CheckBox  ladies;

	/** The music. */
	CheckBox  gents;

	/** The c. */
	CheckBox  music;

	/** The smoke. */
	CheckBox  smoke;
	
	/** The children. */
	CheckBox  children;
	
//	Spinner spinner;
	
	/** The handicap. */
	CheckBox  handicap;
	
//	TextView txtseats;
	TextView txtcarimage;

	Controller controller=null; 
	UploadandCompressImage uploadimage=null;

	Session session;
	String username="";
	private SharedPreferences mPreferences;
	int selectPos=0;
	String imagedata="";
	Bitmap decodedByte;
	private String modevalue="";
	@Override
    protected void onCreate(Bundle savedInstanceState) 
    {
    super.onCreate(savedInstanceState);
    requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
    setContentView(R.layout.travelpref);
    getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.mytitle);
    
    final TextView leftText = (TextView) findViewById(R.id.left_text);
    final TextView rightText = (TextView) findViewById(R.id.right_text);

    leftText.setText("Kaarpool");
    leftText.setTypeface(null, Typeface.BOLD);
   
    
    session = new Session();
    uploadimage=new UploadandCompressImage();
    mPreferences = getSharedPreferences("CurrentUser", MODE_PRIVATE); 
    
	if(!session.checkinfo(mPreferences))
	{
		Intent intent=new Intent(TravelPref.this,Login.class);
		startActivity(intent);
	}
    username = session.getUsername(mPreferences);
    
    String name[]= username.split("@");
    rightText.setText(name[0]);
    
    modevalue=session.getMode(mPreferences);
    System.out.println(username+"username");
    controller=new Controller();   
	ladies = (CheckBox ) findViewById(R.id.ladies);
	gents = (CheckBox ) findViewById(R.id.gents);
	handicap = (CheckBox ) findViewById(R.id.handicap);
	music = (CheckBox ) findViewById(R.id.music);
	smoke = (CheckBox ) findViewById(R.id.smoke);
	children = (CheckBox ) findViewById(R.id.child);
	/*spinner = (Spinner) findViewById(R.id.travelprefspinner);
	txtseats=(TextView)findViewById(R.id.travelpreftxtviewseats);*/
	txtcarimage=(TextView)findViewById(R.id.travelpreftxtviewcarimage);
	 ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
	            this, R.array.seats, android.R.layout.simple_spinner_item);
	 adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    //spinner.setAdapter(adapter);
	 
	 
	travelprefimage=(ImageView)findViewById(R.id.travelpref_image);
	
	controller = new Controller();
    String totalString = controller.getTravelBasedPref(username);
    //System.out.print(totalString +"totalStringtotalStringtotalStringtotalString");
   
   // System.out.print(totalString+"rohsna");
    String[] fields = totalString.split("::");
    System.out.print(fields.length+"rohsna");
    if(!totalString.equals("") &&fields.length>1)
    {
    	if(!fields[0].equals("N"))
    	{
    		ladies.setChecked(true);
    	}
    	if(!fields[1].equals("N"))
    	{
    		gents.setChecked(true);
    	}
    	if(!fields[2].equals("N"))
    	{
    		music.setChecked(true);
    	}
    	if(!fields[3].equals("N"))
    	{
    		smoke.setChecked(true);
    	}
    	if(!fields[4].equals("N"))
    	{
    		children.setChecked(true);
    	}
    	if(!fields[5].equals("N"))
    	{
    		handicap.setChecked(true);
    	}
    	
    	int i = adapter.getPosition(fields[6]);
    	//System.out.println(i+"selectPos");
    	//spinner.setSelection(i);
    	
    	imagedata = fields[7];
        byte[] decodedString = Base64.decode(imagedata, Base64.DEFAULT);
        decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        travelprefimage.setImageBitmap(decodedByte);
        
        
    	
    }
	
    
    travelprefimageupload=(Button)findViewById(R.id.travelpref_imageupload);
    if(modevalue.toString().trim().equals("rider"))
    {
    	travelprefimageupload.setVisibility(View.GONE);
    	travelprefimage.setVisibility(View.GONE);
    	//spinner.setVisibility(View.GONE);
    	//txtseats.setVisibility(View.GONE);
    	txtcarimage.setVisibility(View.GONE);
    	
    }
    travelprefsave=(Button)findViewById(R.id.travelprefsave);
    travelprefback=(Button)findViewById(R.id.travelprefback);
    travelprefsave.setOnClickListener(this);
    travelprefback.setOnClickListener(this);
    travelprefimageupload.setOnClickListener(this);
    
    
   
    
   // spinner.setOnItemSelectedListener(new MyOnItemSelectedListener());
    
    
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
	 
	 
	 
	public class MyOnItemSelectedListener implements OnItemSelectedListener {

		public void onItemSelected(AdapterView<?> parent, View view, int pos,long id) 
		{
			//Toast.makeText(parent.getContext(), "The planet is " +
	         // parent.getItemAtPosition(pos).toString(), Toast.LENGTH_LONG).show();
              seats = parent.getItemAtPosition(pos).toString();
			
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
          
           alert.show();
		   break;
	   case R.id.travelprefback:
		   Intent intent = new Intent(TravelPref.this,Preferences.class);
		   startActivity(intent);
		   break;
	   case R.id.travelprefsave:
		   Log.i("TravelPref_save", "Travel preference values are storing");
		   //String carImage="";
		   
		   Toast.makeText(TravelPref.this, "Saving travel preferences ", Toast.LENGTH_LONG).show();
		   System.out.println(selectedImagePath+"selectedImagePath");
		   System.out.println(decodedByte);
		  // System.out.println(decodedByte.getHeight()+"decodedByte.toString().length()");
		  // System.out.println(decodedByte.getRowBytes()+"decodedByte.getRowBytes()");
		   if(!imagedata.equals(""))
		   {
			   imagedata=uploadimage.bitmapcode(selectedImagePath, decodedByte);
			   System.out.println(imagedata);  
		   }
		   else
		   {
			   imagedata=uploadimage.bitmapcode(selectedImagePath, BitmapFactory.decodeResource(getResources(), R.drawable.car2));
		   }
			   
		 
		  
		   String userTravelPref=getUserAnswer();
		   //System.out.println(userTravelPref+" userTravelPref");
		  // System.out.println(seats+"  seats");
		   controller.saveTravelPref(userTravelPref,seats,imagedata,username);
		   Intent save = new Intent(TravelPref.this,Preferences.class);
		   startActivity(save);
	   }
		// TODO Auto-generated method stub
	
	}
	/**
	 * Gets the user answer.
	 * 
	 * @return the user answer
	 */
	public String getUserAnswer()
	{
		String ladies1 ="", gents1="", music1="", smoke1="", children1 = "", handicap1 = "";
		String total = "";
		if (ladies.isChecked())
		{
			ladies1 = "ladies";		
		}
		else
		{
			ladies1 = null;
		}
		if (gents.isChecked())
		{
			gents1 = "gents";
		}
		else
		{
			gents1 = null;
		}
		if (music.isChecked())
		{
			music1 = "music";
		}
		else
		{
			music1 = null;
		}
		if (smoke.isChecked())
		{
			smoke1 = "smoke";
		}
		else
		{
			smoke1 = null;
		}
		if (children.isChecked())
		{
			children1 = "children";
		}
		else
		{
			children1 = null;
		}
		if (handicap.isChecked())
		{
			handicap1 = "handicap";
		}
		else
		{
			handicap1 = null;
		}
		total=ladies1+":"+gents1+":"+music1+":"+smoke1+":"+children1+":"+handicap1;
		//Log.i("TravelPref_save",total+"  travel prefv");
		return total;
	}
    
}

package com.saventech.karpool;

import java.io.ByteArrayOutputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;

public class UploadandCompressImage
{
	 public String bitmapcode(String selectedImagePath,Bitmap bitmapOrg1)
	    {
	    	try{
	    		if(selectedImagePath.length()==0)
	    		{
	    			
	    			Log.i("UploadandCompressImage", "Default image is selected as user image");	    			
	    			
	    			Bitmap bitmapOrg = bitmapOrg1;
	    			bitmapOrg=Bitmap.createScaledBitmap(bitmapOrg, 40, 40, true);
	    			ByteArrayOutputStream bao = new ByteArrayOutputStream();
	    			bitmapOrg.compress(Bitmap.CompressFormat.JPEG, 90, bao);
	    			byte [] ba = bao.toByteArray();
	    			String ba1=Base64.encodeToString(ba, Base64.DEFAULT);
	    			System.out.println(ba1.length()+"****************************************************************");
	    			return ba1;
	    		}
	    		else
	    		{
		    		//System.out.println(selectedImagePath+"88888888888888888888888888888888888888888888888");
	    			Log.i("Registerwithsysid", "Image Selected from Gallery");
		    		Bitmap bitmapOrg = BitmapFactory.decodeFile(selectedImagePath);
		    		bitmapOrg=Bitmap.createScaledBitmap(bitmapOrg, 40, 40, true);
		    		//bitmapOrg=Bitmap.createScaledBitmap(bitmapOrg, 40, 40, true);
	    			ByteArrayOutputStream bao = new ByteArrayOutputStream();
	    			bitmapOrg.compress(Bitmap.CompressFormat.JPEG, 90, bao);
	    			byte [] ba = bao.toByteArray();
	    			String ba1=Base64.encodeToString(ba, Base64.DEFAULT);
	    			System.out.println(ba1.length()+"****************************************************************");
	    			return ba1;
	    		}
	    		
	    	}
	    	catch(Exception e)
	    	{
	    		e.printStackTrace();
	    		return "";
	    	}
	    	

	    }
}
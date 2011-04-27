package com.saventech.karpool;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashSet;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/*
 * Project: Karpool
 * Package: com.saventech.karpool
 * File: RiderGetRidelist.java
 * Date: Mar 25, 2011
 * Description: It is responsible to display the ridelist to the rider based on the specified values
 */
public class RiderGetRidelist extends Activity implements android.view.View.OnClickListener {
	private SharedPreferences mPreferences; 
	
	Session session;
	Controller controller;
	ArrayList<String> ridelistdetails=new ArrayList<String>();
	private ImageButton sendrequest;
	String checkboxesclicked="";
	String getcheckboxesclicked="";
    String sendrequests[];
	private boolean checkboxesflag=false;
	private String unchecked="";
	ArrayList<String> list=new ArrayList<String>();                   //Arraylist to store the  sent requests

	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);	        

	        drawUI();                             //drawUI methods draw UI of the RideGetRidelist screen
	        checkboxesclicked="";
	        getcheckboxesclicked="";
	        
	}
	 
	 public void drawUI()
	 {
		 setContentView(R.layout.ridelist0);
		 controller=new Controller();
		
	        setContentView(R.layout.ridelist0);
			 controller=new Controller();
		        Log.i("Ridergetridelist_Activity","Entered in Ridergetridelist activity");
		        session=new Session();
		        ridelistdetails=RiderJourneyDetails.ridelist;
			    mPreferences = getSharedPreferences("CurrentUser", MODE_PRIVATE); 
			  
				if(!session.checkinfo(mPreferences))                             //checking session existence
				{
					Intent intent=new Intent(RiderGetRidelist.this,Login.class);
					startActivity(intent);
				
				}
				if(session.ischeckboxesclicked(mPreferences))                  //checking any request were sent previously
				{
					list=new ArrayList<String>();
				    getcheckboxesclicked=session.getCheckBoxesClicked(mPreferences);  //getting previously send request in string format
				   // System.out.println("777777777777777"+getcheckboxesclicked);
					System.out.println("checked ride list=================================================="+getcheckboxesclicked.toString().trim()+"  ********"+getcheckboxesclicked.toString().trim().length());
					if(getcheckboxesclicked.toString().trim().length()!=0)    
					{
						sendrequests=getcheckboxesclicked.toString().trim().split("::");
						for(int val=0;val<sendrequests.length;val++)
						{
							list.add(sendrequests[val].toString().trim()+"::"); //storing previously send request in list
						}
						
						
					}
				}
				else                                                       //puting checkboxesclicked is empty string
				{
					 SharedPreferences.Editor editor=mPreferences.edit();
					 editor.putString("checkboxesclicked", checkboxesclicked);
					 editor.commit();
				}
				System.out.println(session.getUsername(mPreferences)+"-----"+session.getPassword(mPreferences)+ridelistdetails.size()+"  *****************");
				sendrequest=(ImageButton)findViewById(R.id.sendrequest);
				
				// making customised linear layout
				LinearLayout l = (LinearLayout) findViewById(R.id.mylayout1);
		        LayoutInflater linflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		        String toaststring="";
		        if(ridelistdetails.size()!=0)
		        {
		        	for(int k=0;k<ridelistdetails.size();k++)            //setting rides details to appropriate widgets in screen
		        	{
			        	String records[]=ridelistdetails.get(k).toString().split("KRL");
						for(int j=0;j<records.length;j++)
						{
							if(k==0 && j==0)
							{
								
							}
							else
							{
							  toaststring =toaststring+records[j]+" ";
							}
						}
						toaststring=toaststring+"\n";
						View customView = linflater.inflate(R.layout.ridelist, null);
				            TextView route = (TextView) customView.findViewById(R.id.route);
				            TextView rate = (TextView) customView.findViewById(R.id.rate);
				            ImageButton img=(ImageButton)customView.findViewById(R.id.image);
				            if(k==0)
				            {
				            	route.setText("   Route:"+records[1].toString().trim()+" to "+records[2].toString().trim());
					            rate.setText("   Rate:");
					            String imagebyte = records[records.length-1];
					            byte[] decodedString = Base64.decode(imagebyte, Base64.DEFAULT);
					            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
					            img.setImageBitmap(decodedByte);
					            System.out.println(records.length+"             ************************************8"+records[3].toString().trim());
					          
					            if(getcheckboxesclicked.length()!=0)     
					            {
					            	//checking check box is clicked previously or not if so black the check box
					            	checkboxesflag=issendrequest(list,records[1].toString().trim(),records[2].toString().trim(),records[3].toString().trim(),records[4].toString().trim());
					            }
					            
					           
					          
				            }
				            else
				            {
				            	route.setText("   Route:"+records[0].toString().trim()+" to "+records[1].toString().trim());
					            rate.setText("   Rate:");
					            String imagebyte = records[records.length-1];
					            byte[] decodedString = Base64.decode(imagebyte, Base64.DEFAULT);
					            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
					            img.setImageBitmap(decodedByte);
					            System.out.println(records[records.length-1]+"             ************************************88");
					            if(getcheckboxesclicked.length()!=0)
					            {
					            	//checking check box is clicked previously or not if so black the check box
					            	checkboxesflag=issendrequest(list,records[0].toString().trim(),records[1].toString().trim(),records[2].toString().trim(),records[3].toString().trim());
					            }
					         
				            }
				            
				           
				           
				            img.setOnClickListener(this);
				            final CheckBox check=(CheckBox)customView.findViewById(R.id.ridelistcheckbox);
				          
				           /* route.setId(k);
				            rate.setId(k);*/
				            check.setId(k);
				            if(checkboxesflag)           //disable the check box if it is previously checked and sent request
				            {
				            	//System.out.println("TTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT");
				            	check.setChecked(true);
				            	check.setEnabled(false);
				            	checkboxesflag=false;
				            }
				            check.setOnClickListener(new View.OnClickListener() {
				            
				            	 public void onClick(View v) {
						            	//Toast.makeText(RiderGetRidelist.this, "Pressed checkbutton", Toast.LENGTH_LONG).show();
				            		    if(check.isChecked())
				            		    {
				            		    	checkbuttonid(v);      //store driver details in list on check box checked
				            		    }
				            		    else
				            		    {
				            		    	
				            		    	multipletimepressedcheckboxes(v);      //remove driver details in list on check box unchecked
				            		    }
						            	
						               
						            }
						        });
				            
				            img.setId(k+ridelistdetails.size());
				           
				            
				            
				            l.addView(customView); 
		        	}
		        }
		        sendrequest.setOnClickListener(new View.OnClickListener() {
		            public void onClick(View v) 
		            {
		            	Log.i("RiderGetRidelist_sendrequestbutton", "Pressed send request button");
		            	
		            	sendrequest(getcheckboxesclicked.toString().trim());
		               
		            }
		        });
		        
		 
	 }
	 
	 //removing ride from arraylist on uncheck check box
	 public void multipletimepressedcheckboxes(View v)
	 {
		 if(ridelistdetails.size()!=0)
	        {
	        	for(int k=0;k<ridelistdetails.size();k++)
	        	{
	        		if(v.getId()==k)
	        		{
	        			Log.i("RiderGetRidelist_uncheck", "Unchecked the previously selected check box");
	        			String checkrecords[]=ridelistdetails.get(k).toString().trim().split("KRL");
	        			for(int j=0;j<checkrecords.length;j++)
						{
							if(k==0 && j==0)
							{
								list.remove(checkrecords[1]+"CHECKBOX"+checkrecords[2]+"CHECKBOX"+checkrecords[3]+"CHECKBOX"+checkrecords[4]+"::");
								//checkboxesclicked=checkboxesclicked+checkrecords[1]+"CHECKBOX"+checkrecords[2]+"CHECKBOX"+checkrecords[3]+"CHECKBOX"+checkrecords[4]+"CHECKBOX"+"unchecked"+"::";
								break;
							}
							else
							{
								list.remove(checkrecords[0]+"CHECKBOX"+checkrecords[1]+"CHECKBOX"+checkrecords[2]+"CHECKBOX"+checkrecords[3]+"::");
								//checkboxesclicked=checkboxesclicked+checkrecords[0]+"CHECKBOX"+checkrecords[1]+"CHECKBOX"+checkrecords[2]+"CHECKBOX"+checkrecords[3]+"CHECKBOX"+"unchecked"+"::";
								break;
							}
							
						}
	        			
	        		}
	        	}
	        	//Toast.makeText(RiderGetRidelist.this, "checkbox data is: "+checkboxesclicked, Toast.LENGTH_LONG).show();
	        	
	        }
	 }
	 
	 //checking request wheather it is already send or not
	 public boolean issendrequest(ArrayList<String> listdata, String source, String destination, String username, String time)
	 {
		
		 
		 for(int val=0;val<listdata.size();val++)
		 {
			 //System.out.println("jjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj"+array[val]);
			 String temp1[]=listdata.get(val).toString().trim().split("::");
			 for(int val1=0;val1<temp1.length;val1++)
			 {
				 String temp[]=temp1[val1].toString().trim().split("CHECKBOX");
					
				 if(username.toString().trim().equals(temp[2].toString().trim()) && source.toString().trim().equals(temp[0].toString().trim()) && destination.toString().trim().equals(temp[1].toString().trim()) && time.toString().trim().equals(temp[3].toString().trim()))
				 { 
					 System.out.println(list.get(val).toString().trim()+"  =="+username+"\t"+temp[2]+"\t"+source+"\t"+temp[0]+"\t"+destination+"\t"+temp[1]+"\t"+time+"\t"+temp[3]);
					 
					 return true;
				 }
				 
			 }
			 
		 }
		 return false;
	 }
	 
	 
	 //methods to remove duplicates in ride arraylist
	 public static void removeDuplicates(ArrayList list) {
         HashSet set = new HashSet(list);
         list.clear();
         list.addAll(set);
 }

	 
	 //sending request when sendrequest button is pressed
	 public void sendrequest(String getcheckboxesclicked)
	 {
		 removeDuplicates(list);
		 for (Object data : list) {
			 checkboxesclicked=checkboxesclicked+data.toString().trim();
	    }
		 //checkboxesclicked=checkboxesclicked+getcheckboxesclicked.toString().trim();
		 System.out.println(checkboxesclicked+"hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
		 session.saveCheckBoxesClicked(mPreferences, checkboxesclicked);
		 String messagedata=controller.sendRiderequest(checkboxesclicked,mPreferences.getString("UserName", "un").toString().trim());
		 checkboxesclicked="";
		 String channelnames[]=messagedata.toString().trim().split(":");
		 for(int val=0;val<channelnames.length;val++)
		 {
			 if(channelnames.length>1 && (val!=0))
			 {
				 System.out.println(channelnames[val].toString().trim());
			 }
			 
		 }
		 
		 Toast.makeText(RiderGetRidelist.this, channelnames[0].toString().trim(), Toast.LENGTH_LONG).show();
		 
		 
	 }
	 
	 
	 
	 //adding checked rides to arraylist
	 public void checkbuttonid(View v)
	 {
		 
		 if(ridelistdetails.size()!=0)
	        {
	        	for(int k=0;k<ridelistdetails.size();k++)
	        	{
	        		if(v.getId()==k)
	        		{
	        			System.out.println(v.getId()+"QQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQ");
	        			String checkrecords[]=ridelistdetails.get(k).toString().trim().split("KRL");
	        			for(int j=0;j<checkrecords.length;j++)
						{
							if(k==0 && j==0)
							{
								list.add(checkrecords[1]+"CHECKBOX"+checkrecords[2]+"CHECKBOX"+checkrecords[3]+"CHECKBOX"+checkrecords[4]+"::");
								//checkboxesclicked=checkboxesclicked+checkrecords[1]+"CHECKBOX"+checkrecords[2]+"CHECKBOX"+checkrecords[3]+"CHECKBOX"+checkrecords[4]+"CHECKBOX"+"checked"+"::";
								break;
							}
							else
							{
								list.add(checkrecords[0]+"CHECKBOX"+checkrecords[1]+"CHECKBOX"+checkrecords[2]+"CHECKBOX"+checkrecords[3]+"::");
								//checkboxesclicked=checkboxesclicked+checkrecords[0]+"CHECKBOX"+checkrecords[1]+"CHECKBOX"+checkrecords[2]+"CHECKBOX"+checkrecords[3]+"CHECKBOX"+"checked"+"::";
								break;
							}
							
						}
	        			
	        		}
	        	}
	        	
	        	
	        }
		 
		
		 
	 }
	 
	 @Override
		public void onResume() {
			super.onResume();
			drawUI();
			
			
		}
	 
	 
	 
	 //method to restrict back button
	 public boolean onKeyDown(int keyCode, KeyEvent event) 
		{
			if(keyCode == KeyEvent.KEYCODE_BACK)
			{
				return true;
			}
			return super.onKeyDown(keyCode, event);
		}
	 
	        public void onClick(View V)      //onClick will be performed when button is pressed
	    	{
	        	int t=V.getId()-ridelistdetails.size();
	        	 Log.i("Pressed_0", "Driver image has been pressed");
			    
	        	 if(ridelistdetails.size()!=0)
	 	        {
	 	        	for(int k=0;k<ridelistdetails.size();k++)
	 	        	{
	 	        		final AlertDialog.Builder alert = new AlertDialog.Builder(this.getParent());
	 	        		alert.setTitle("Information");
	 					final TextView input = new TextView(this);
	 					
	 		        	String records[]=ridelistdetails.get(k).toString().split("KRL");
	 					for(int j=0;j<records.length;j++)
	 					{
	 				        if(t==k)
	 				        {
	 					
		 						if(k==0)
		 						{
		 							input.setText("\tName\t\t\t:"+records[3]+"\n\tSource\t\t:"+records[1]+"\n\tDestination  :"+records[2]+"\n\tMobile\t\t:"+records[6]+"\n");
		 						}
		 						else
		 						{
		 							input.setText("\tName\t\t\t:"+records[2]+"\n\tSource\t\t:"+records[0]+"\n\tDestination  :"+records[1]+"\n\tMobile\t\t:"+records[5]+"\n");
		 						}
	 				        }
	 					
	 					}
	 					if(t==k)
	 					{
		 					alert.setView(input);
							
							alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog, int whichButton) {
									
									return;
								}
							});
							
								alert.show();
	 					}
	 	        	}
	 	        }
	           
	    		
	    		
	    	}
	        
	    }

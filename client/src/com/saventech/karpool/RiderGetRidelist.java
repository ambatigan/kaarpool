package com.saventech.karpool;


import java.util.ArrayList;
import java.util.HashSet;

import org.deacon.Deacon;
import org.deacon.DeaconError;
import org.deacon.DeaconObserver;
import org.deacon.DeaconResponse;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
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
	//private static  Deacon deacon;
	ArrayList<String> list=new ArrayList<String>(); 
	//Arraylist to store the  sent requests
	/*private String ip = "";
	private int port;
	private static String riderusername="";*/

	public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);	        
	        
	        drawUI();                              //drawUI methods draw UI of the RideGetRidelist screen
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
			    session.storemode(mPreferences, "rider");
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
		        
		        
		        
		        
		       /* try 
		        {
				   // System.out.println("context"+context);
		        	riderusername=parseChannelName(session.getUsername(mPreferences));
		        	ip = getString(R.string.MeteorIP);
		        	port=Integer.parseInt(getString(R.string.SubscriberPort));
		        	System.out.println(JourneyDetails.rflag+"   dflag value");
		        	if(JourneyDetails.rflag==0)
		   		 {
		   			 try {
		   				 this.deacon = new Deacon(ip.toString().trim(),port, this);
		   				 if(!deacon.isRunning())
		   					{
		   						
		   							
		   							
		   			        		deacon.catchUpTimeOut(60);
		   			            	deacon.register(this);
		   			            	if(JourneyDetails.dflag!=0)
		   			            	{
		   			            		Newroute.stopdeacon();
		   			            		//deacon.leaveChannel(parseChannelName(session.getUsername(mPreferences)));
		   			            		JourneyDetails.dflag=0;
		   			            		System.out.println("Driver channel leaveddddddddddddddd");
		   			            	}
		   							//deacon.leaveChannel(parseChannelName(session.getUsername(mPreferences)));
		   							deacon.joinChannel(parseChannelName(session.getUsername(mPreferences)), 0);
		   							deacon.start();
		   						
		   					}
		   				 JourneyDetails.rflag=1;
		   				 } catch (Exception e) {
		   						// TODO Auto-generated catch block
		   						e.printStackTrace();
		   					}
		   		 }
		        	//System.out.println(ip+"ip add of meteorAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
		        	
		        	Log.i("RiderChannel",parseChannelName(mPreferences.getString("UserName","un").toString().trim()) );
		        	
		 			
		        } 
		        catch (Exception e) 
		        {
		        	System.out.println("Problem while creating Deacon");
		        	e.printStackTrace();
		        }*/
		        
		 
	 }
	 
	 //removing ride from arraylist on uncheck check box
	 @SuppressWarnings("unused")
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
	 @SuppressWarnings({ "rawtypes", "unchecked" })
	public static void removeDuplicates(ArrayList list) {
         HashSet set = new HashSet(list);
         list.clear();
         list.addAll(set);
 }

	 //parsing channel name
	 public String parseChannelName(String chname)
	 {
		 String str1[]=chname.toString().trim().split("@");
		 String str2[]=str1[1].toString().trim().split(".com");
		 String channame=str1[0]+"-"+str2[0];
		 //System.out.println("Parsed channel name^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^"+channame);
		 return channame.toString().trim();
	 }
	 
	 //Message making to send to  channel 
	 public String makeMessage(String chname1,String chname2, String messageval,String ridid)
	 {
		 String deaconmessage="";
		 if(chname1.toString().trim().length()!=0 && chname2.toString().trim().length()!=0 )
		 {
		     deaconmessage="ADDMESSAGE "+"d"+chname1+" "+"r"+chname2.toString().trim()+"::"+messageval+"::"+ridid+"EVENT";
		 }
		 return deaconmessage;
	 }
	 
	 // sending request when sendrequest button is pressed
	 @SuppressWarnings("static-access")
	public void sendrequest(String getcheckboxesclicked)
	 {
		 
		 
		 ProgressDialog progressdialog = ProgressDialog.show(RiderGetRidelist.this.getParent(), "","Sending ride request...", true);
			 removeDuplicates(list);
			 for (Object data : list) {
				 checkboxesclicked=checkboxesclicked+data.toString().trim();
		    }
			 if(checkboxesclicked.toString().trim().length()!=0)
			 {
			 //checkboxesclicked=checkboxesclicked+getcheckboxesclicked.toString().trim();
			// System.out.println(checkboxesclicked+"hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
			 String injectmessage="";
			 session.saveCheckBoxesClicked(mPreferences, checkboxesclicked);
			 String messagedata=controller.sendRiderequest(checkboxesclicked,mPreferences.getString("UserName", "un").toString().trim(),"meteor");
			 System.out.println(messagedata+"ppppppppppppppppppppppppppppppppppppppppppppppppppppppppppp");
			 String channelnames[]=messagedata.toString().trim().split(":");
			 ArrayList<String> events=new ArrayList<String>();
			 if(channelnames[0].toString().trim().equals("Your request has been sent"))
			 {
				 for(int val=0;val<channelnames.length;val++)
				 {
					 if(channelnames.length>1 && (val!=0) && (channelnames[0].toString().trim().equals("Your request has been sent")))
					 {
						 System.out.println(channelnames[val].toString().trim());
						 String splitoutput[]=channelnames[val].toString().trim().split("-");
						 String chname=parseChannelName(splitoutput[0].toString().trim());
						 String senderchname=parseChannelName(mPreferences.getString("UserName", "un").toString().trim());
						 String event=makeMessage(chname,senderchname,"r1",splitoutput[1]);
						 events.add(event);
						 
						// controller.injectEvents(event);
					 }
					 
				 }
				 injectmessage=controller.injectEvents(events); 
			 }
			 
		
		    if(injectmessage.toString().trim().equals("successfully injected"))
		    {
		    	String mesg=controller.sendRiderequest(checkboxesclicked,mPreferences.getString("UserName", "un").toString().trim(),"update");
		    	checkboxesclicked="";
		    	String cnames[]=mesg.toString().trim().split(":");
		        Toast.makeText(RiderGetRidelist.this, cnames[0].toString().trim(), Toast.LENGTH_LONG).show();
		    }
		    else
		    {
		    	//HAVE TO DO MODIFICATIONS
		    	if(channelnames[0].toString().trim().equals("Already you sent requests to this users"))
		    	{
		    		Toast.makeText(RiderGetRidelist.this, "Already you sent requests to this users", Toast.LENGTH_LONG).show();	
		    	}
		    	else
		    	{
		    	      Toast.makeText(RiderGetRidelist.this, "Problem occure in sending request try again", Toast.LENGTH_LONG).show();
		    	}
		    }
		 }
		 else
		 {
			 Toast.makeText(RiderGetRidelist.this, "Please select a ride to send request", Toast.LENGTH_LONG).show();
		 }
			 
			 progressdialog.dismiss();
		 
		 
		 
	 }
	 
	 
	 
	 //adding checked rides to arraylist
	 @SuppressWarnings("unused")
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
	       /* public static void stopdeacon()
	        {
	        	
	        	try
	        	{
	        		deacon.leaveChannel(riderusername);
	        		deacon.stop();
	        		Log.i("RiderGetRidelist_StopDeacon", "Deacon stopped");
	        	}
	        	catch(Exception e)
	        	{
	        		Log.i("Exception_Stoping deacon", "Exception occured while stopping deacon");
	        	}
	        	
	        }*/

			/*public void onError(DeaconError arg0) {
				// TODO Auto-generated method stub
				
			}

			public void onPush(DeaconResponse response) {
				// TODO Auto-generated method stub
				System.out.println("Rider payload from meteor: "+ response.getPayload());
				String payload;
				System.out.println("payload from meteor: "+ response.getPayload());
				payload = response.getPayload().trim();
				String str1[]=payload.toString().trim().split("::");
				System.out.println("ridername: "+str1[0]+"\nmessage: "+str1[1]+"\nmode: "+str1[2]);
				String msg = msgParse(str1[1]);
				RiderJourneyDetails.ridermeteormsg.add(msg+" FROM "+str1[0]);
				notificationAlarm(str1[0], msg);
				
			}
			private void notificationAlarm(String name, String msg) {
				// TODO Auto-generated method stub
				NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
		    	
		    	int icon = R.drawable.ic_tab_artists_white;
		    	CharSequence text = msg;
		    	CharSequence contentTitle = "  Kaarpool notification";
		    	CharSequence contentText = msg+" from "+name;
		    	long when = System.currentTimeMillis();
		    	
		    	//RiderJourneyDetails ParentActivity = (RiderJourneyDetails) this.getParent();
		        //ParentActivity.switchTab(2);
		    	Intent intent = new Intent(RiderGetRidelist.this, JourneyDetails.class);
		    	intent.putExtra("receiver", "ridernotification");
		    	PendingIntent contentIntent = PendingIntent.getActivity(this, 0, intent, 0);
		    	Notification notification = new Notification(icon,text,when);
		    	
		    	notification.flags = Notification.DEFAULT_SOUND | Notification.FLAG_AUTO_CANCEL;
		    	
		    	long[] vibrate = {0,100,200,300};
		    	notification.vibrate = vibrate;
		    	
		    	notification.ledARGB = Color.RED;
		    	notification.ledOffMS = 300;
		    	notification.ledOnMS = 300;
		    	
		    	notification.defaults |= Notification.DEFAULT_LIGHTS;
		    	//notification.flags |= Notification.FLAG_SHOW_LIGHTS;
		    	
		    	notification.setLatestEventInfo(this, contentTitle, contentText, contentIntent);
		    	
		    	notificationManager.notify(1001, notification);
			}

			public void onReconnect() {
				// TODO Auto-generated method stub
				
			}
			public String msgParse(String msg)
			{
				if(msg.trim().equals("d1"))
					return getString(R.string.d1);
				if(msg.trim().equals("d2"))
					return getString(R.string.d2);
				if(msg.trim().equals("d3"))
					return getString(R.string.d3);
				if(msg.trim().equals("d4"))
					return getString(R.string.d4);
				if(msg.trim().equals("d5"))
					return getString(R.string.d5);
				if(msg.trim().equals("d6"))
					return getString(R.string.d6);
				return msg;
				
			}*/
	        
	    }

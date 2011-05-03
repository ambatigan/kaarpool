package com.saventech.kaarpool;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import org.apache.log4j.Logger;

public class InjecttoMeteor 
{
	
	Logger log = Logger.getLogger(InjecttoMeteor.class);

	public String injecttoMeteor(String events,String Meteorip,String Meteorport) throws IOException
	{
		String eventlist[]=events.toString().trim().split("EVENT");
		try{
		Socket clientSocket=new Socket(Meteorip,Integer.parseInt(Meteorport));
		PrintWriter outToServer = new PrintWriter(clientSocket.getOutputStream(),true);
		System.out.println("connected to meteor server");
		
			for(int i=0;i<eventlist.length;i++)
			{
			    outToServer.println(eventlist[i].toString().trim());
			}
			clientSocket.close();
			outToServer.close();
			
		}catch(Exception e)
		{
			e.printStackTrace();
			log.info("Exception occur while injecting events");
			return "Exception occur while injecting evetns";
		}
		
		return "successfully injected";
	}
	
}

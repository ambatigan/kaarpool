package com.saventech.karpool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Locale;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;

public class TrackRoute extends MapActivity {
	
	private MapController mapController;
	private MapView mapView;
	private LocationManager locationManager;
	private TextView address;
	private TextView distance;
	private SharedPreferences mPreferences; 
	Controller controller;
	RiderAcknowledgements rack;
	private double fromLat;
	private double fromLon;
	private double toLat;
	private double toLon;
	private String timeToDestination;
	private String distanceToDestination;
	public String usermode;
	private String coordinates;
	public String[] result;
	 Session session;
	 GeoPoint point=null;
    @SuppressWarnings({ "static-access", "unused" })
	@Override
    protected void onCreate(Bundle savedInstanceState) 
    {
    	super.onCreate(savedInstanceState);
    	controller = new Controller();
        rack = new RiderAcknowledgements();
        Intent intent = getIntent();
        usermode = intent.getStringExtra("mode");
        
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.trackroute);
        
    	getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.mytitle);
        
        final TextView leftText = (TextView) findViewById(R.id.left_text);
        final TextView rightText = (TextView) findViewById(R.id.right_text);

        leftText.setText("Kaarpool");
        leftText.setTypeface(null, Typeface.BOLD);
        session=new Session();
        mPreferences = getSharedPreferences("CurrentUser", MODE_PRIVATE); 
        String username = session.getUsername(mPreferences);
        String name[]= username.split("@");
        rightText.setText(name[0]);     
       
        address = (TextView)findViewById(R.id.trackrouteadd);
    	distance = (TextView)findViewById(R.id.trackroutedst);
    	System.out.println("Ride id: "+rack.rrideid+" user mode: "+usermode);
    	// create a map view
    	LinearLayout linearLayout = (LinearLayout) findViewById(R.id.mainlayout);
    	mapView = (MapView) findViewById(R.id.mapview);
    	mapView.setBuiltInZoomControls(true);
    	mapView.setStreetView(true);
    	mapController = mapView.getController();
    	mapController.setZoom(14); // Zoom 1 is world view
    	if(usermode.trim().equals("rider"))
    	{
    		System.out.println("track route if condition: rider");
    		coordinates = controller.trackrouteDrivername(username, rack.rrideid);
    		System.out.println("driver coordinates: "+coordinates);
    		result = coordinates.split("::");
        	int lat = (int) (Float.parseFloat(result[0]) * 1E6);
        	int lng = (int) (Float.parseFloat(result[1])* 1E6);
        	point = new GeoPoint(lat, lng);
        	mapController.animateTo(point);
    	}
    	locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
    	locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0,
    	        0, new GeoUpdateHandler());
    	
    }
	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
	
	public class GeoUpdateHandler implements LocationListener {

		public void onLocationChanged(Location location) {
			int lat = (int) (location.getLatitude() * 1E6);
			int lng = (int) (location.getLongitude() * 1E6);
			double lat1 = location.getLatitude();
			double lng1 = location.getLongitude();
			double lat2 = Double.parseDouble(result[0]);
			double lng2 = Double.parseDouble(result[1]);
			String add1 = lat1+","+lng1;
			String add2 = lat2+","+lng2;
			getDistancenTime(getUrl(add1,add2));
			Location location1 = new Location("reverseGeocoded");
			location1.setLatitude(lat2);
			location1.setLongitude(lng2);
			//System.out.println("distance: "+location.distanceTo(location1));
			//String str = String.valueOf((location.distanceTo(location1))/1000) + " kms";
			//distance.setText(str);
			Geocoder geocoder=new Geocoder(getBaseContext(),Locale.getDefault()); 
			try 
			{ 
				String city=""; 
				List<Address> addresses= geocoder.getFromLocation(lat2, lng2, 1); 
				if(addresses.size()>0) 
				{ 
					//city+=addresses.get(0).getSubAdminArea(); 
					Address address = addresses.get(0);
	                
	                System.out.println("address if condition      ");
	                for (int i = 0; i < address.getMaxAddressLineIndex(); i++)
	                {
	                	System.out.println(address.getAddressLine(i));
	                    city+=address.getAddressLine(i)+" ";
	                }

	                //city+=address.getLocality()+" ";
	                //city+=address.getPostalCode()+" ";
	                city+=address.getCountryName();
				}
				//System.out.println("city name: "+city);
				if(city=="")
					address.setText("No address found");
				else
					address.setText(city);
				//Toast.makeText(getBaseContext(),"Address: "+city,Toast.LENGTH_SHORT).show();
			}
			catch (IOException e) 
			{ 
				e.printStackTrace(); 
			}
			if(usermode.trim().equals("driver"))
			{
				GeoPoint point = new GeoPoint(lat, lng);
				mapController.animateTo(point); //	mapController.setCenter(point);
			}
			
		}

		public void onProviderDisabled(String provider) {

		}

		public void onProviderEnabled(String provider) {
	
		}

		public void onStatusChanged(String provider, int status, Bundle extras) {
			
		}
	}
	public void getDistancenTime(String url) {
		String result = "";
		InputStream is = null;
    	try
    	{
    		HttpClient httpclient = new DefaultHttpClient();
    		HttpPost httppost = new HttpPost(url);
    		HttpResponse response = httpclient.execute(httppost);
    		HttpEntity entity = response.getEntity();
    		is = entity.getContent();
    	} 
    	catch(Exception e) 
    	{
    		Log.e("log_tag", "Error in http conection"+e.toString());
    	}
    	try 
    	{
    		BufferedReader reader = new BufferedReader(
    		new InputStreamReader(is,"iso-8859-1"),8);
    		StringBuilder sb = new StringBuilder();
    		String line = null;
    		while ( (line = reader.readLine() ) != null) {
    		sb.append(line + "\n"); }
    		is.close();
    		result=sb.toString();

    	}
    	catch(Exception e)
    	{
    		Log.e("log_tag", "Error converting result "+e.toString());
    	}
		try 
		{
			JSONObject rootObj = new JSONObject(result); //rootObj ist jetzt ein dict
			JSONArray routes = (JSONArray) rootObj.get("routes");
			if(routes.length()<1)
				distance.setText("ERROR no route there");
			JSONObject firstRoute = routes.getJSONObject(0);
			JSONArray legs = (JSONArray) firstRoute.get("legs");
			if(legs.length()<1)
				distance.setText("ERROR no legs there");
			JSONObject firstLeg = legs.getJSONObject(0);
			JSONObject durationObject = (JSONObject) firstLeg.get("duration");
			JSONObject distanceObject = (JSONObject) firstLeg.get("distance");
			JSONObject startaddObject = (JSONObject) firstLeg.get("start_location");
			JSONObject endaddObject = (JSONObject) firstLeg.get("end_location");
			String startadd = (String)firstLeg.get("start_address");
			String endadd = (String)firstLeg.get("end_address");

			timeToDestination = (String) durationObject.get("text");
			distanceToDestination = (String) distanceObject.get("text");
			fromLat = (Double) startaddObject.getDouble("lat");
			fromLon = (Double) startaddObject.getDouble("lng");
			
			toLat = (Double) endaddObject.getDouble("lat");
			toLon = (Double) endaddObject.getDouble("lng");
			distance.setText("Time: "+timeToDestination+" "+"and Distance: "+distanceToDestination);
			
			System.out.println("start_Location: "+fromLat+","+fromLon+"\nend_location"+toLat+","+toLon);
			System.out.println("start_address: "+startadd+"\nend_address: "+endadd);
			
		} 
		catch (JSONException e) 
		{
			e.printStackTrace();
		}
	}
	public static String getUrl(String fromAdress, String toAdress) // connect to map web service
    {
	    StringBuffer urlString = new StringBuffer();
	    urlString.append("http://maps.google.com/maps/api/directions/json?origin=");
	    urlString.append(fromAdress.toString());
	    urlString.append("&destination=");
	    urlString.append(toAdress.toString());
	    urlString.append("&sensor=false");
	    return urlString.toString();
    }
}
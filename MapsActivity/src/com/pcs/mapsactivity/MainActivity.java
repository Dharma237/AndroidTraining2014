package com.pcs.mapsactivity;


import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class MainActivity extends Activity{
	private GoogleMap googleMap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);

		try {
			//loads map
		intializeMap();
		
		//setting MapType
		googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
		
		//displaying our location
		googleMap.setMyLocationEnabled(true);
		
		//setting zoom controls
		googleMap.getUiSettings().setZoomControlsEnabled(false);
		
		//mylocation Button
		googleMap.getUiSettings().setMyLocationButtonEnabled(true);
		
		//Enabling Compass
		googleMap.getUiSettings().setCompassEnabled(true);
		
		//Enabling Gestures
		googleMap.getUiSettings().setAllGesturesEnabled(true);
		
		//Enabling Zoom Controls
		googleMap.getUiSettings().setZoomControlsEnabled(true);
		
		/***
		 * Defining Latitudes and longitude
		 */
		double latitude = 20.087;
		double longitude = 77.0345;
		
		for (int i=0; i<5; i++)
		{	
			//Setting our RandomLocation
			double[] randomLocation = createRandomLocation(latitude,longitude);
			MarkerOptions markerOptions = new MarkerOptions().
					
					position(new LatLng(randomLocation[0],randomLocation[1]))
					
					.title(getResources().getString(R.string.marker_txt));
			
			
			if (i == 0)
				markerOptions.icon(BitmapDescriptorFactory
						.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
			if (i == 1)
				markerOptions.icon(BitmapDescriptorFactory
						.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
			if (i == 2)
				markerOptions.icon(BitmapDescriptorFactory
						.defaultMarker(BitmapDescriptorFactory.HUE_CYAN));
			if (i == 3)
				markerOptions.icon(BitmapDescriptorFactory
						.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
			if (i == 4)
				markerOptions.icon(BitmapDescriptorFactory
						.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
			if (i == 5)
				markerOptions.icon(BitmapDescriptorFactory
						.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
			
			googleMap.addMarker(markerOptions);
			
			//moving camera to the last zoon
			if(i==5)
			{
				CameraPosition cameraPosition = new CameraPosition
						.Builder().target(
								new LatLng(randomLocation[0], randomLocation[1]))
								.zoom(15).build();
				
				googleMap.animateCamera(CameraUpdateFactory
						.newCameraPosition(cameraPosition));
			}
		}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	//Initializing our GoogleMap
	@SuppressLint("NewApi")
	private void intializeMap() {
		
		if (googleMap==null) {
			googleMap= (((MapFragment) getFragmentManager()
					.findFragmentById(R.id.map_fragment)).getMap());
			
			
		}

	}
	//Creating RandomLocation using Latitude and Longitude
	private double[] createRandomLocation(double latitude, double longitude) {
		
		return new double[]{ latitude + ((Math.random() - 0.5) / 500),
				longitude + ((Math.random() - 0.5) / 500),
				150 + ((Math.random() - 0.5) * 10) };
	}

}

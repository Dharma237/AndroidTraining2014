package com.pcs.mapsactivity;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity{
	private GoogleMap googleMap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);


		intializeMap();
		googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

		googleMap.setMyLocationEnabled(true);

		googleMap.getUiSettings().setZoomControlsEnabled(false);

		googleMap.getUiSettings().setMyLocationButtonEnabled(true);

		googleMap.getUiSettings().setCompassEnabled(true);

		googleMap.getUiSettings().setAllGesturesEnabled(true);

		googleMap.getUiSettings().setZoomControlsEnabled(true);

		double latitude = 20.087;
		double longitude = 77.0345;

		for (int i=0; i<5; i++)
		{
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

	}

	private void intializeMap() {
		if (googleMap==null) {
			googleMap= (((MapFragment) getFragmentManager().findFragmentById(R.id.map_fragment)).getMap());
			
			
		}

	}
	private double[] createRandomLocation(double latitude, double longitude) {
		
		return new double[]{ latitude + ((Math.random() - 0.5) / 500),
				longitude + ((Math.random() - 0.5) / 500),
				150 + ((Math.random() - 0.5) * 10) };
	}

}

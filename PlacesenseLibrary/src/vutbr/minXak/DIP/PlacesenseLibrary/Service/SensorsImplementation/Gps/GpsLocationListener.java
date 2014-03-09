package vutbr.minXak.DIP.PlacesenseLibrary.Service.SensorsImplementation.Gps;

import android.location.GpsSatellite;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

public class GpsLocationListener implements LocationListener {
	private static final String TAG = "GPSlocationUpdate";
	private LocationManager mLocationManager;
	public int CurrentSatelliteCount;

	public GpsLocationListener(LocationManager locationManager) {
		this.mLocationManager = locationManager;
	}

	@Override
	public void onLocationChanged(Location location) {
		Log.i(TAG, "New location from: " + 
				location.getProvider() + ". Loc:" + 
				location.getLatitude() + " - " + 
				location.getLongitude());
		this.getGpsStatus();
	}

	private void getGpsStatus() {
		GpsStatus status = this.mLocationManager.getGpsStatus(null);
		
		this.CurrentSatelliteCount = 0;
		for (GpsSatellite sat : status.getSatellites() ){
			if (sat.usedInFix()){
				this.CurrentSatelliteCount++;
				Log.i(TAG, "Used in fix " + String.valueOf(this.CurrentSatelliteCount) );	
			}						
		}
		
	}

	@Override
	public void onProviderDisabled(String provider) {
		Log.i(TAG, "GPS Not aviable");
	}

	@Override
	public void onProviderEnabled(String provider) {
		Log.i(TAG, "GPS Enabled");
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		Log.i(TAG, "Status changed" + String.valueOf(status));
	}

}

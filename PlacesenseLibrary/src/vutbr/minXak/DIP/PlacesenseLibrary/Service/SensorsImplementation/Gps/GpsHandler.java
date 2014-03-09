package vutbr.minXak.DIP.PlacesenseLibrary.Service.SensorsImplementation.Gps;

import vutbr.minXak.DIP.PlacesenseLibrary.Service.SensorsImplementation.BaseHandler;
import android.location.LocationListener;
import android.location.LocationManager;

public class GpsHandler extends BaseHandler implements IGpsResultSetter, IGpsResultGetter {

	private static final int TIME_BETWEEN_GPS_SCAN = 5000;
	private static final int MIN_DISTANCE_IN_METER = 5;

	private LocationListener mLocationListener;
	private LocationManager mLocationManager;

	private GpsResult GpsResult = new GpsResult();

	public GpsHandler(LocationManager locationManager) {
		this.mLocationManager = locationManager;
		this.mLocationListener = new GpsLocationListener(this.mLocationManager);
	}

	@Override
	public void SetGpsValues(float latitude, float longitude, int satteliteLocksCount) {
		this.GpsResult.Latitude = latitude;
		this.GpsResult.Longitude = longitude;
		this.GpsResult.SatteliteLocksCount = satteliteLocksCount;
	}

	@Override
	public GpsResult GetGpsResult() {
		return this.GpsResult;
	}

	@Override
	public void TurnOnBase() {
		this.mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, TIME_BETWEEN_GPS_SCAN, MIN_DISTANCE_IN_METER,
				this.mLocationListener);

	}

	@Override
	public void TurnOffBase() {
		this.mLocationManager.removeUpdates(this.mLocationListener);

	}

}

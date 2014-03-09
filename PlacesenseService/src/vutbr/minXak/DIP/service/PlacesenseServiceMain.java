package vutbr.minXak.DIP.service;

import java.util.LinkedList;
import java.util.List;

import vutbr.minXak.DIP.PlacesenseLibrary.Handlers.IHandler;
import vutbr.minXak.DIP.PlacesenseLibrary.Service.SensorsImplementation.Accelometer.AccelometerHandler;
import vutbr.minXak.DIP.PlacesenseLibrary.Service.SensorsImplementation.Gps.GpsHandler;
import vutbr.minXak.DIP.PlacesenseLibrary.Service.SensorsImplementation.WifiScan.WifiHandler;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.SensorManager;
import android.location.LocationManager;
import android.net.wifi.WifiManager;
import android.os.Debug;
import android.os.IBinder;
import android.os.Messenger;
import android.util.Log;
import android.widget.Toast;

public class PlacesenseServiceMain extends Service {
	private List<IHandler> mHandlers = new LinkedList<IHandler>();
	
	private static final String TAG = "PlacesenseService";
	
	private SensorManager mSensorManager;
	private LocationManager mLocationManager;
	private WifiManager mWifiManger;
	
	int mStartMode = START_STICKY;
    IBinder mBinder;      // interface for clients that bind
    boolean mAllowRebind = true; // indicates whether onRebind should be used
    
    private Messenger mMessenger;
	
	@Override
    public void onCreate() {
		Debug.waitForDebugger();
		this.mMessenger = new Messenger(new RequestHandler());
        Log.i(TAG, "create");
        
        this.LoadModules();
        this.TurnOn();
    }
	
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {    	  
    	Log.i(TAG, "start");
    	
    	Toast.makeText(this, "service starting", Toast.LENGTH_SHORT).show();
        // The service is starting, due to a call to startService()
        return mStartMode;
    }
    @Override
    public IBinder onBind(Intent intent) {
    	Log.i(TAG, "bind");
        // A client is binding to the service with bindService()
    	return this.mMessenger.getBinder();
    }
    
    @Override
    public boolean onUnbind(Intent intent) {
    	Log.i(TAG, "unbind");
    	
        // All clients have unbound with unbindService()
        return mAllowRebind;
    }
    @Override
    public void onRebind(Intent intent) {
    	Log.i(TAG, "rebind");
        // A client is binding to the service with bindService(),
        // after onUnbind() has already been called
    }
    @Override
    public void onDestroy() {
    	Log.i(TAG, "destroy");
        // The service is no longer used and is being destroyed
    	Toast.makeText(this, "service done", Toast.LENGTH_SHORT).show();
    	
    	this.TurnOff();
    }
    
	public void TurnOn() {
		for(IHandler h : this.mHandlers){
			h.TurnOn();
		}
	}
	
	public void TurnOff() {
		for(IHandler h : this.mHandlers){
			h.TurnOff();
		}
	}

	private void LoadModules() {
		this.mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		this.mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

		this.mWifiManger = (WifiManager) getSystemService(Context.WIFI_SERVICE);

		this.mHandlers.add(new AccelometerHandler(this.mSensorManager));
		this.mHandlers.add(new GpsHandler(this.mLocationManager));
		this.mHandlers.add(new WifiHandler(this.mWifiManger, this));
	}
}

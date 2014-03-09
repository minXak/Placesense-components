package vutbr.minXak.DIP.PlacesenseLibrary.Service.SensorsImplementation.WifiScan;

import java.util.TimerTask;

import android.net.wifi.WifiManager;
import android.util.Log;

public class WifiTimer extends TimerTask {

	private static final String TAG = "Timer";
	private WifiManager mWifiManger;

	public WifiTimer(WifiManager manager) {
		this.mWifiManger = manager;
	}

	@Override
	public void run() {
		Log.i(TAG, "WIfi timer tick");
		this.mWifiManger.startScan();
	}

}

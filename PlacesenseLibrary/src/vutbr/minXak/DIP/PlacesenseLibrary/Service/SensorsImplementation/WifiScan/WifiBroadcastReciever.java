package vutbr.minXak.DIP.PlacesenseLibrary.Service.SensorsImplementation.WifiScan;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.util.Log;

public class WifiBroadcastReciever extends BroadcastReceiver {
	private static final String TAG = "Timer";
	
	private IWifiBroadcastResultHandler mWifiBroadcastResultHandler;
	private WifiManager mWifiManager;

	public WifiBroadcastReciever(IWifiBroadcastResultHandler wifiBroadcastResultHandler, WifiManager wifiManager) {
		this.mWifiBroadcastResultHandler = wifiBroadcastResultHandler;
		this.mWifiManager = wifiManager;
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		this.mWifiBroadcastResultHandler.SetResults(this.mWifiManager.getScanResults());
		
		Log.i(TAG, "WIfi scan recieve data");
	}
}

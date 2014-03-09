package vutbr.minXak.DIP.PlacesenseLibrary.Handlers;

import android.app.Service;
import android.content.Context;
import android.net.wifi.WifiManager;

public class WifiHandler implements IPlaceHandler {

	WifiManager mWifiManager;
	
	public WifiHandler(Service service){
		this.mWifiManager = (WifiManager) service.getSystemService(Context.WIFI_SERVICE);
	}
	
	@Override
	public void TurnOn() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void TurnOff() {
		// TODO Auto-generated method stub		
	}
	
	private void StartScan(){
		
	}
	
	private void MakeScan(){
		this.mWifiManager.startScan();		
	}

	@Override
	public boolean ActivityStatus() {
		// TODO Auto-generated method stub
		return false;
	}


}

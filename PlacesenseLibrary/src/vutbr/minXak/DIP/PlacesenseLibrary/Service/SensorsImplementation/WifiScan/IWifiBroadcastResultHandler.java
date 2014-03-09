package vutbr.minXak.DIP.PlacesenseLibrary.Service.SensorsImplementation.WifiScan;

import java.util.List;

import android.net.wifi.ScanResult;

public interface IWifiBroadcastResultHandler {

	public List<ScanResult> GetResultsList();

	public void SetResults(List<ScanResult> list);
}

package vutbr.minXak.DIP.PlacesenseLibrary.Service.SensorsImplementation.WifiScan;

import java.util.List;
import java.util.Timer;

import vutbr.minXak.DIP.PlacesenseLibrary.DatabaseHelper.RawData.RawDataRepository;
import vutbr.minXak.DIP.PlacesenseLibrary.Service.SensorsImplementation.BaseHandler;
import android.content.Context;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class WifiHandler extends BaseHandler implements IWifiBroadcastResultHandler {

	private static final int SCAN_INERVAL = 10000;

	private WifiManager mWifiManager;
	private Context mContext;
	private Timer mTimer;
	private WifiBroadcastReciever mWifiBroadcastReciever;
	private IntentFilter wifiScanIntent;
	
	private RawDataRepository repository;

	private List<ScanResult> scanResultList;

	public WifiHandler(WifiManager wifiManager, Context context, RawDataRepository dataRepository) {
		this.mWifiManager = wifiManager;
		this.mContext = context;
		this.mWifiBroadcastReciever = new WifiBroadcastReciever(this, this.mWifiManager);
		this.repository = dataRepository;	

		this.createIntentFilter();
	}

	private void createIntentFilter() {
		this.wifiScanIntent = new IntentFilter();
		wifiScanIntent.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
	}

	private void startScanning() {
		this.mContext.registerReceiver(this.mWifiBroadcastReciever, this.wifiScanIntent);
		if (this.mTimer == null) {
			this.mTimer = new Timer();
			this.mTimer.schedule(new WifiTimer(this.mWifiManager), 0, SCAN_INERVAL);
		}
	}

	private void stopScanning() {
		if (this.mTimer != null) {
			this.mTimer.cancel();
			this.mTimer.purge();
			this.mTimer = null;
		}
		this.mContext.unregisterReceiver(this.mWifiBroadcastReciever);
	}

	@Override
	public List<ScanResult> GetResultsList() {
		return this.scanResultList;
	}

	@Override
	public void SetResults(List<ScanResult> list) {	
		Gson gson = new GsonBuilder().registerTypeAdapter(ScanResult.class, new ScanResultSerialiser()).create();
		String result = gson.toJson(list);
		this.repository.insertRawValue(result);
		this.scanResultList = list;
	}

	@Override
	public void TurnOnBase() {
		this.startScanning();
	}

	@Override
	public void TurnOffBase() {
		this.stopScanning();
	}
}

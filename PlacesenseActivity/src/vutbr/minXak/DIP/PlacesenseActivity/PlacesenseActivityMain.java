package vutbr.minXak.DIP.PlacesenseActivity;

import java.util.LinkedList;
import java.util.List;

import vutbr.minXak.DIP.PlacesenseLibrary.Client.MessageHandlers.RequestHandler;
import vutbr.minXak.DIP.PlacesenseLibrary.Handlers.IHandler;
import android.app.Activity;
import android.content.Context;
import android.hardware.SensorManager;
import android.location.LocationManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Messenger;
import android.widget.Button;

public class PlacesenseActivityMain extends Activity implements ITurnOnOffable, IMessengerWorker {

	private SensorManager mSensorManager;
	private LocationManager mLocationManager;
	private WifiManager mWifiManger;
	
	private Messenger mMessenger;
	
	public IBinder mBinder;

	private Button mButtonBind;
	private Button mButtonService;
	private Button mButtonMessage;

	private List<IHandler> mHandlers = new LinkedList<IHandler>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_placesence);

		this.LoadButtons();
//		this.LoadModules();
	}

	private void LoadButtons() {
		this.mButtonBind = (Button) findViewById(R.id.button);
		this.mButtonBind.setOnClickListener(new ClickListener(this, this.mButtonBind, this, this));
		
		
		this.mButtonService = (Button) findViewById(R.id.buttonbind);
		this.mButtonService.setOnClickListener(new ServiceClickListener(this, this.mButtonService, this));
	}	

	@Override
	public void TurnOn() {
		for(IHandler h : this.mHandlers){
			h.TurnOn();
		}
	}

	@Override
	public void TurnOff() {
		for(IHandler h : this.mHandlers){
			h.TurnOff();
		}
	}

	private void LoadModules() {
		this.mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		this.mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

		this.mWifiManger = (WifiManager) getSystemService(Context.WIFI_SERVICE);

//		this.mHandlers.add(new AccelometerHandler(this.mSensorManager));
//		this.mHandlers.add(new GpsHandler(this.mLocationManager));
//		this.mHandlers.add(new WifiHandler(this.mWifiManger, this));
	}

	@Override
	public void SetMessenger(Messenger messenger) {
		this.mMessenger = messenger;	
		this.mButtonMessage = (Button) findViewById(R.id.buttonMessage);
		this.mButtonMessage.setOnClickListener(new SendMessageListener(new RequestHandler(this.mMessenger)));
	}

	@Override
	public void RemoveMessenger() {
		this.mMessenger = null;
		this.mButtonMessage = (Button) findViewById(R.id.buttonMessage);
		this.mButtonMessage.setOnClickListener(null);
	}
}

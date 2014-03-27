package vutbr.minXak.DIP.PlacesenseActivity;

import java.util.LinkedList;
import java.util.List;

import vutbr.minXak.DIP.PlacesenseLibrary.Client.MessageHandlers.RequestHandler;
import vutbr.minXak.DIP.PlacesenseLibrary.Handlers.IHandler;
import android.app.Activity;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Messenger;
import android.widget.Button;

public class PlacesenseActivityMain extends Activity implements ITurnOnOffable, IMessengerWorker {
	
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

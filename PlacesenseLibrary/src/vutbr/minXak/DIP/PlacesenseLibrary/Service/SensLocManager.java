package vutbr.minXak.DIP.PlacesenseLibrary.Service;

import vutbr.minXak.DIP.PlacesenseLibrary.Handlers.GpsHandler;
import vutbr.minXak.DIP.PlacesenseLibrary.Handlers.IMovementHandler;
import vutbr.minXak.DIP.PlacesenseLibrary.Handlers.IPathHandler;
import vutbr.minXak.DIP.PlacesenseLibrary.Handlers.IPlaceHandler;
import vutbr.minXak.DIP.PlacesenseLibrary.Handlers.WifiHandler;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class SensLocManager extends Service implements ISensLocManager {

	private IPathHandler PathHandler;
	private IMovementHandler MovementHandler;
	private IPlaceHandler PlaceHandler;
	
	public SensLocManager(){
		this.PathHandler = new GpsHandler();
		this.PlaceHandler = new WifiHandler(this);
	}
	
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

}

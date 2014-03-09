package vutbr.minXak.DIP.PlacesenseActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Messenger;
import android.util.Log;
import android.widget.Toast;

public class PlacesnseServiceConnection implements ServiceConnection {
	private static final String TAG = "Service connection";

	public boolean isBound = false;
	
	private Context mContext;
	private IMessengerWorker mMessengerWorker;

	public PlacesnseServiceConnection(Context context, IMessengerWorker messengerWorker) {
		this.mContext = context;
		this.mMessengerWorker = messengerWorker;
	}

	@Override
	public void onServiceConnected(ComponentName className, IBinder service) {
		// Because we have bound to an explicit
		// service that is running in our own process, we can
		// cast its IBinder to a concrete class and directly access it.		
		this.mMessengerWorker.SetMessenger((new Messenger(service)));
		this.isBound = true;

		Log.i(TAG, "onServiceConnected");
	}

	@Override
	public void onServiceDisconnected(ComponentName className) {
		Toast.makeText(this.mContext, "service disconnected", Toast.LENGTH_SHORT).show();
		Log.i(TAG, "onServiceDisconnected");
		this.mMessengerWorker.RemoveMessenger();
		this.isBound = false;
	}
	
}

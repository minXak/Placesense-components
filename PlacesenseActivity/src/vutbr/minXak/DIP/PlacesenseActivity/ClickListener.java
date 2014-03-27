package vutbr.minXak.DIP.PlacesenseActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ClickListener implements OnClickListener {
	private Button mButton;
	private Activity mActivity;
	private IMessengerWorker mMessageWorker;
	private Intent mServiceIntent;

	private PlacesnseServiceConnection mServiceConnection;

	private boolean status = false;

	public ClickListener(ITurnOnOffable mainContext, Button button, Activity activity, IMessengerWorker messageWorker) {
		this.mButton = button;
		this.mActivity = activity;
		this.mMessageWorker = messageWorker;
	}

	
	@Override
	public void onClick(View v) {
		if (this.status) {
			this.stopService();
		} else {
			this.startService();
		}
	}

	private void startService() {
		this.mServiceConnection = new PlacesnseServiceConnection(this.mActivity, this.mMessageWorker);
		this.mServiceIntent = new Intent("vutbr.minXak.DIP.service.PlacesenseServiceMain");
		
		this.mActivity.bindService(this.mServiceIntent, this.mServiceConnection, Context.BIND_AUTO_CREATE);

		this.mButton.setText(R.string.unbindit);
		this.status = true;
	}

	private void stopService() {
		this.mActivity.unbindService(this.mServiceConnection);
		this.mButton.setText(R.string.bindit);
		this.status = false;
	}
}

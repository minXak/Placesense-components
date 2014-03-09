package vutbr.minXak.DIP.PlacesenseActivity;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ServiceClickListener implements OnClickListener {
	private Button mButton;
	private Activity mActivity;
	private Intent mServiceIntent;

	private boolean status = false;

	public ServiceClickListener(ITurnOnOffable mainContext, Button button, Activity activity) {
		this.mButton = button;
		this.mActivity = activity;
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
		this.mServiceIntent = new Intent("vutbr.minXak.DIP.service.PlacesenseServiceMain");
		this.mActivity.startService(this.mServiceIntent);		
		this.mButton.setText(R.string.turnoff);
		this.status = true;
	}

	private void stopService() {
		this.mActivity.stopService(this.mServiceIntent);
		this.mButton.setText(R.string.turnon);
		this.status = false;
	}
}

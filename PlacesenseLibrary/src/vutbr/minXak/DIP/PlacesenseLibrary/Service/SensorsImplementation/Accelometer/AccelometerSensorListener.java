package vutbr.minXak.DIP.PlacesenseLibrary.Service.SensorsImplementation.Accelometer;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.util.Log;

public class AccelometerSensorListener implements SensorEventListener {
	private static final String TAG = "AccelometerUpdate";

	private IAccelometerResultSetter resultHandler;

	public AccelometerSensorListener(IAccelometerResultSetter resultHandler) {
		this.resultHandler = resultHandler;
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {

	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		//Log.i(TAG,
		//		"Used in fix " + String.valueOf(event.values[0]) + " " + String.valueOf(event.values[1]) + " "
		//				+ String.valueOf(event.values[0]));
 
		this.resultHandler.SetAccelometerValues(event.values[0], event.values[1], event.values[2]);
	}

}

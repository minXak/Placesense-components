package vutbr.minXak.DIP.PlacesenseLibrary.Service.SensorsImplementation.Accelometer;

import vutbr.minXak.DIP.PlacesenseLibrary.Service.SensorsImplementation.BaseHandler;
import android.hardware.Sensor;
import android.hardware.SensorManager;

public class AccelometerHandler extends BaseHandler implements IAccelometerResultSetter, IAccelometerResultGetter {

	private SensorManager mSensorManager;
	private Sensor mSensor;
	private AccelometerSensorListener mAccelomerterSensorListener;
	
	private AccelometerResult AccelometerResult = new AccelometerResult();

	public AccelometerHandler(SensorManager sensorManager) {
		this.mSensorManager = sensorManager;
		this.mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
		this.mAccelomerterSensorListener = new AccelometerSensorListener(this);
	}

	@Override
	public void SetAccelometerValues(float x, float y, float z) {
		this.AccelometerResult.AxeX = x;
		this.AccelometerResult.AxeY = y;
		this.AccelometerResult.AxeZ = z;
	}

	@Override
	public AccelometerResult GetAccelometerValues() {
		return this.AccelometerResult;
	}

	@Override
	public void TurnOnBase() {
		mSensorManager.registerListener(this.mAccelomerterSensorListener, this.mSensor, SensorManager.SENSOR_DELAY_NORMAL);

	}

	@Override
	public void TurnOffBase() {
		mSensorManager.unregisterListener(this.mAccelomerterSensorListener);

	}
}

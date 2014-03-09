package vutbr.minXak.DIP.PlacesenseLibrary.Service.SensorsImplementation;

import vutbr.minXak.DIP.PlacesenseLibrary.Handlers.IHandler;

public abstract class BaseHandler implements IHandler {

	protected boolean isActive = false;

	@Override
	public void TurnOn() {
		if (!this.isActive) {
			this.isActive = true;
			this.TurnOnBase();
		}
	}

	@Override
	public void TurnOff() {
		if (this.isActive) {
			this.isActive = false;
			this.TurnOffBase();
		}
	}

	@Override
	public boolean ActivityStatus() {
		return this.isActive;
	}

	public abstract void TurnOnBase();

	public abstract void TurnOffBase();
}

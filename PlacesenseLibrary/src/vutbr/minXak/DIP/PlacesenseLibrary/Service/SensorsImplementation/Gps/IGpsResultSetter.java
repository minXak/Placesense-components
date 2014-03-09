package vutbr.minXak.DIP.PlacesenseLibrary.Service.SensorsImplementation.Gps;

public interface IGpsResultSetter {	
	public void SetGpsValues(float latitude, float longitude, int satteliteLocksCount);
}

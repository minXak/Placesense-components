package vutbr.minXak.DIP.PlacesenseLibrary.DatabaseHelper.RawData;

import java.util.Date;

public class RawDataFactory {
	public Date EventDate;
	public String WifiSnapshotJson;
	
	public RawDataFactory(String wifiSnaphotJson){
		this.EventDate = new Date();
	}
}

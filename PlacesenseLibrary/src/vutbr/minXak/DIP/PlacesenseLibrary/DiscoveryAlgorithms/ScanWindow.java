package vutbr.minXak.DIP.PlacesenseLibrary.DiscoveryAlgorithms;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ScanWindow {
	public static final int WindowMaxSize = 10;

	public Set<Beacon> ScannedBeacons = new HashSet<Beacon>();

	public List<Scan> Scans = new ArrayList<Scan>();

	private int StableScanCount = 0;

	public int getWindowSize() {
		return this.Scans.size();
	}

	public boolean tryAddScan(Scan scan) {
		if (this.getWindowSize() < WindowMaxSize) {
			this.Scans.add(scan);
			this.ScannedBeacons.addAll(scan.ScannedBeacons);
			return true;
		}

		return false;
	}

	private void calculateBeaconRepresentativeStates() {
		for (Beacon b : this.ScannedBeacons) {
			b.ResponseRate = this.calculateResponseRateForBeacon(b);
		}
	}

	private double calculateResponseRateForBeacon(Beacon b) {
		// TODO: calculate responseRate
		return 0.0;
	}

	public Set<String> GetSSIDFingerPrint() {
		Set<String> output = new HashSet<String>();
		for (Beacon beacon : this.ScannedBeacons) {
			output.add(beacon.SSID);
		}

		return output;
	}
}

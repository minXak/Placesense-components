package vutbr.minXak.DIP.PlacesenseLibrary.DiscoveryAlgorithms;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Place {
	public String fingerpring;

	public int lat;

	public int lng;

	public int entranceTime;

	public int departureTime;

	public String description;

	public Set<Beacon> RepresentativeBeacons = new HashSet<Beacon>();

	public void CreateRepresentativeStates(Map<Beacon, Integer> beaconAppearCount) {
		for (Map.Entry<Beacon, Integer> entry : beaconAppearCount.entrySet()) {
			if (this.isRepresentative(entry.getValue())) {
				this.RepresentativeBeacons.add(entry.getKey());
			}
		}
	}
	

	private boolean isRepresentative(Integer appearCount) {
		return appearCount / (DiscoveryAlgorithm.MaxStableScanDepth * ScanWindow.WindowMaxSize) > DiscoveryAlgorithm.ResponseRateRepresentativeThreshold;
	}
}

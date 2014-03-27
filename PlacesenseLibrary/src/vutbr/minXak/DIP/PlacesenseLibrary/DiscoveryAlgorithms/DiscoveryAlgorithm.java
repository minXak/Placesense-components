package vutbr.minXak.DIP.PlacesenseLibrary.DiscoveryAlgorithms;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.HashMap;
import java.util.Map;

public class DiscoveryAlgorithm {

	public static final double ResponseRateRepresentativeThreshold = 0.7f;

	public static final int MaxStableScanDepth = 5; // better name is Min

	public static final double SimilarityMin = 0.8;

	private Set<Beacon> SeenBeacons = new HashSet<Beacon>();

	private Map<Beacon, Integer> BeaconsAppearCount = new HashMap<Beacon, Integer>();

	private Queue<ScanWindow> SimilarScanWindow = new LinkedList<ScanWindow>();

	private ScanWindow CurrentScanWindow = new ScanWindow();

	private ScanWindow PreviousScanWindow;

	private Place CurrentPlace;

	public boolean isInPlace = false;

	public void ProcessBeaconScan(Scan beaconScan) {
		if (this.isInPlace) {
			this.SearchForDeparture(beaconScan);
		} else {
			this.SearchForEntrance(beaconScan);
		}
	}

	public void SearchForDeparture(Scan beaconScan) {

	}

	public void SearchForEntrance(Scan beaconScan) {
		this.filterScan(beaconScan);

		if (!this.CurrentScanWindow.tryAddScan(beaconScan)) {
			if (this.IsScanWindowSimilarToPrevious()) {

				this.PreviousScanWindow.ScannedBeacons
						.addAll(this.CurrentScanWindow.ScannedBeacons);

				if (this.SimilarScanWindow.isEmpty()) {
					this.SimilarScanWindow.add(this.PreviousScanWindow);
				}

				this.SimilarScanWindow.add(this.CurrentScanWindow);

				if (this.SimilarScanWindow.size() >= MaxStableScanDepth) {
					this.EntrenceHasBeenDiscovered();
					this.StartToDiscoverDeparture(beaconScan);

					return;
				}
			} else {
				this.PreviousScanWindow = this.CurrentScanWindow;
				this.SimilarScanWindow.clear();
			}

			this.CurrentScanWindow = new ScanWindow();
			this.CurrentScanWindow.tryAddScan(beaconScan);

		}

		for (Beacon beacon : beaconScan.ScannedBeacons) {
			this.SeenBeacons.add(beacon);
			this.calculateBeaconAppearCount(beacon);
		}

	}

	public void filterScan(Scan beaconScan) {
		Iterator<Beacon> iterator = beaconScan.ScannedBeacons.iterator();
		
		while (iterator.hasNext()) {
			Beacon beacon = iterator.next();
			if (beacon.isLocal()) {
				iterator.remove();
			}
		}
	}

	private void calculateBeaconAppearCount(Beacon beacon) {
		Integer count = this.BeaconsAppearCount.get(beacon);

		if (count == null) {
			this.BeaconsAppearCount.put(beacon, 1);
		} else {
			this.BeaconsAppearCount.put(beacon, count + 1);
		}
	}

	private void StartToDiscoverDeparture(Scan beaconScan) {
		// TODO Auto-generated method stub

	}

	private void EntrenceHasBeenDiscovered() {
		this.CurrentPlace = new Place();
		this.CurrentPlace.CreateRepresentativeStates(this.BeaconsAppearCount);
		this.isInPlace = true;
	}

	private boolean IsScanWindowSimilarToPrevious() {
		if (this.PreviousScanWindow == null) {
			return false;
		}

		return this.ComputeTonimotoCoeficient(this.CurrentScanWindow.GetSSIDFingerPrint(),
				this.PreviousScanWindow.GetSSIDFingerPrint()) >= SimilarityMin;
	}

	private double ComputeTonimotoCoeficient(Set<String> current, Set<String> previous) {
		Set<String> intersection = new HashSet<String>(current);
		intersection.retainAll(previous);
		return intersection.size() / current.size() + previous.size() - intersection.size();
	}

	private boolean IsBeaconFamiliar() {

		// TODO: implement detection of familiarity of the beakon
		return false;
	}

	public Place CreatePlace() {
		return new Place();
	}
}

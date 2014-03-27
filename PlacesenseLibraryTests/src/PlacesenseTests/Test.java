package PlacesenseTests;
import java.util.ArrayList;
import java.util.List;

import vutbr.minXak.DIP.PlacesenseLibrary.DiscoveryAlgorithms.Beacon;
import vutbr.minXak.DIP.PlacesenseLibrary.DiscoveryAlgorithms.DiscoveryAlgorithm;
import vutbr.minXak.DIP.PlacesenseLibrary.DiscoveryAlgorithms.Scan;
import vutbr.minXak.DIP.PlacesenseLibrary.DiscoveryAlgorithms.ScanWindow;
import junit.framework.Assert;
import junit.framework.TestCase;

public class Test extends TestCase {
	public void setUp() throws Exception {

		System.out.println("Setting up ...");
	}

	public void teanDown() throws Exception {
		System.out.println("Tearing down ...");
	}

	public void testGetBestTeam() {
		DiscoveryAlgorithm alg = new DiscoveryAlgorithm();
		Assert.assertEquals(true, true);
	}

	public void testScanWindowAssigment() {
		ScanWindow scanwindow1 = new ScanWindow();
		Scan scan1 = new Scan();
		scan1.isStable = true;
		scanwindow1.Scans.add(scan1);

		ScanWindow scanwindow2 = new ScanWindow();
		Scan scan2 = new Scan();
		scan2.isStable = true;
		scanwindow2.Scans.add(scan2);

		List<ScanWindow> list = new ArrayList<ScanWindow>();

		list.add(scanwindow1);

		list.add(scanwindow2);

		ScanWindow currentScan;

		currentScan = scanwindow1;
		scanwindow1 = scanwindow2;
		scanwindow2 = null;

		Assert.assertEquals(true, true);
	}
	
	public void testDetectionAlgorithm(){
		DiscoveryAlgorithm alg = new DiscoveryAlgorithm();
		
		for(int i = 0; i < 51; i++ ){
			alg.ProcessBeaconScan(this.GetScan());
		}
		
		Assert.assertEquals(alg.isInPlace, true);
	}
	
	public void testLocalBeaconDetection(){
		Beacon b = new Beacon();
		b.Mac = "03:55:55:44:01:01";
		
		Assert.assertEquals(true, b.isLocal());
	}
	
	public void testGlobalBeaconDetection(){
		Beacon b = new Beacon();
		b.Mac = "04:55:55:44:01:01";
		
		Assert.assertEquals(false, b.isLocal());
	}
	
	public void tesScanFilterLocalMacAddress(){
		Scan s = new Scan();
		Beacon a = this.GetBeacon1();
		s.ScannedBeacons.add(a);
		Beacon b = this.GetBeacon3();
		s.ScannedBeacons.add(b);
		
		DiscoveryAlgorithm alg = new DiscoveryAlgorithm();
		alg.filterScan(s);
		
		Assert.assertEquals(false, s.ScannedBeacons.contains(b));
		Assert.assertEquals(true, s.ScannedBeacons.contains(a));
	}

	private Scan GetScan() {
		Scan e = new Scan();
		e.ScannedBeacons = new ArrayList<Beacon>();

		e.ScannedBeacons.add(this.GetBeacon1());
		e.ScannedBeacons.add(this.GetBeacon2());

		return e;
	}

	private Beacon GetBeacon1() {
		Beacon b = new Beacon();

		b.SSID = "newBeacon";
		b.SignalLevel = 80;
		b.Mac = "00:1C:B3:09:85:15";

		return b;
	}

	private Beacon GetBeacon2() {
		Beacon b = new Beacon();

		b.SSID = "newBeacon2";
		b.SignalLevel = 80;
		b.Mac = "00:FF:B3:09:85:15";

		return b;
	}
	
	private Beacon GetBeacon3() {
		Beacon b = new Beacon();

		b.SSID = "newBeacon3";
		b.SignalLevel = 80;
		b.Mac = "03:FF:B3:09:85:15";

		return b;
	}
}

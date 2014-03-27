package vutbr.minXak.DIP.PlacesenseLibrary.DiscoveryAlgorithms;

public class Beacon {
	public String Mac;
	public String SSID;
	
	public int SignalLevel;
	public double ResponseRate;
	
	public boolean IsRepresentative;

	public boolean equals(Object o) {
		return (o instanceof Beacon) && (((Beacon)o).Mac).equals(this.Mac);
	}

	public int hashCode() {
		return this.Mac.hashCode();
	}

	
	public boolean isLocal() {		
		String[] mac = this.Mac.split(":");
	    int value = Integer.decode("0x" + mac[0]);	    
	    return (value >> 1) % 2 == 1;	    
	}
}

package vutbr.minXak.DIP.PlacesenseLibrary.DatabaseHelper;

public final class DatabaseConfig {
	public static final String DATABASE_NAME = "placesense_data";
	public static final int DATABASE_VERSION = 2;

	public static final String TABLE_NAME_RAW_DATA = "row_data";
	public static final String TABLE_NAME_VISITED_PLACES = "visited_places";    
    
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_EVENT_DATETIME = "eventDatetime";    
        
    public static final String COLUMN_WIFI_SNAPSHOT = "wifiSnapshot";
    public static final String COLUMN_LONGITUDE = "longitude";
    public static final String COLUMN_LATITUTDE = "latitude";
    
    public static final String COLUMN_MOVEMENT_TYPE = "eventType";
	public static final String TABLE_NAME_GPS = "gps";

}

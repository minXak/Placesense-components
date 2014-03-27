package vutbr.minXak.DIP.PlacesenseLibrary.DatabaseHelper.RawData;

import vutbr.minXak.DIP.PlacesenseLibrary.DatabaseHelper.DatabaseConfig;
import vutbr.minXak.DIP.PlacesenseLibrary.DatabaseHelper.DatabaseHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class RawDataRepository {
	// Database fields
	private SQLiteDatabase database;
	private DatabaseHelper dbHelper;
	private String[] allColumns = { DatabaseConfig.COLUMN_ID, DatabaseConfig.COLUMN_EVENT_DATETIME, DatabaseConfig.COLUMN_WIFI_SNAPSHOT };

	public RawDataRepository(Context context) {
		dbHelper = new DatabaseHelper(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	public void insertRawValue(String wifiSnapshotJson) {
		this.open();
		ContentValues values = new ContentValues();
		values.put(DatabaseConfig.COLUMN_WIFI_SNAPSHOT, wifiSnapshotJson);
		values.put(DatabaseConfig.COLUMN_EVENT_DATETIME, System.currentTimeMillis());			
		database.insert(DatabaseConfig.TABLE_NAME_RAW_DATA, null, values);
		this.close();
	}

}

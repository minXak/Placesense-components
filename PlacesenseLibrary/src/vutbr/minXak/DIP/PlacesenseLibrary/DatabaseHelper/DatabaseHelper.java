package vutbr.minXak.DIP.PlacesenseLibrary.DatabaseHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

	public DatabaseHelper(Context context) {
		super(context, DatabaseConfig.DATABASE_NAME, null, DatabaseConfig.DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE " + DatabaseConfig.TABLE_NAME_RAW_DATA + " (" + DatabaseConfig.COLUMN_ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT," + DatabaseConfig.COLUMN_EVENT_DATETIME + " Integer,"
				+ DatabaseConfig.COLUMN_WIFI_SNAPSHOT + " TEXT NOT NULL" + ");");

		db.execSQL("CREATE TABLE " + DatabaseConfig.TABLE_NAME_VISITED_PLACES + " (" + DatabaseConfig.COLUMN_ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT," + DatabaseConfig.COLUMN_EVENT_DATETIME + " Integer,"
				+ DatabaseConfig.COLUMN_WIFI_SNAPSHOT + " TEXT NOT NULL,"
				+ DatabaseConfig.COLUMN_MOVEMENT_TYPE + " INTEGER" + ");");
		
		db.execSQL("CREATE TABLE " + DatabaseConfig.TABLE_NAME_GPS + " (" + DatabaseConfig.COLUMN_ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT," + DatabaseConfig.COLUMN_EVENT_DATETIME + " Integer,"
				+ DatabaseConfig.COLUMN_LATITUTDE + " TEXT NOT NULL,"
				+ DatabaseConfig.COLUMN_LONGITUDE + " TEXT NOT NULL" + ");");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + DatabaseConfig.TABLE_NAME_RAW_DATA);
		db.execSQL("DROP TABLE IF EXISTS " + DatabaseConfig.TABLE_NAME_VISITED_PLACES);
		db.execSQL("DROP TABLE IF EXISTS " + DatabaseConfig.TABLE_NAME_GPS);
		onCreate(db);
	}
}

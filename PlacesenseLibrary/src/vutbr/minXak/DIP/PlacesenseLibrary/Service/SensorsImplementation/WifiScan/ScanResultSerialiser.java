package vutbr.minXak.DIP.PlacesenseLibrary.Service.SensorsImplementation.WifiScan;

import java.lang.reflect.Type;

import android.net.wifi.ScanResult;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class ScanResultSerialiser implements JsonSerializer<ScanResult> {
	@Override
	public JsonElement serialize(ScanResult result, Type arg1, JsonSerializationContext arg2) {
		final JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("BSSID", result.BSSID);
		jsonObject.addProperty("SSID", result.SSID);
		jsonObject.addProperty("capabilities", result.capabilities);
		jsonObject.addProperty("frequency", result.frequency);
		jsonObject.addProperty("level", result.level);		

		return jsonObject;
	}
}

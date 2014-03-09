package vutbr.minXak.DIP.PlacesenseLibrary.Client.MessageHandlers;

import android.os.Handler;
import android.os.Message;

public class ResponseHandler extends Handler {
	static final int MSG_SAY_HELLO_RSP = 1;
	static final int MSG_GET_DATA_RSP = 2;

	@Override
	public void handleMessage(Message msg) {
		int respCode = msg.what;

		switch (respCode) {
		case MSG_SAY_HELLO_RSP: {
			String result = msg.getData().getString("respData");
		}
			break;
		case MSG_GET_DATA_RSP: {
			String result = msg.getData().getString("respData");
		}
//			break;
		default:
			super.handleMessage(msg);
		}
	}

}

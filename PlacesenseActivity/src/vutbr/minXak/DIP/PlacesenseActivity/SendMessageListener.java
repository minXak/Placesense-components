package vutbr.minXak.DIP.PlacesenseActivity;

import vutbr.minXak.DIP.PlacesenseLibrary.Client.MessageHandlers.RequestHandler;
import android.view.View;
import android.view.View.OnClickListener;

public class SendMessageListener implements OnClickListener {

	static final int MSG_SAY_HELLO = 1;
	
	private RequestHandler mRequestHandler;
	
	public SendMessageListener(RequestHandler handler){
		this.mRequestHandler = handler;
	}
	
	@Override
	public void onClick(View v) {		
		this.mRequestHandler.SendHello();
	}

}

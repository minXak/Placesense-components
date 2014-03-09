package vutbr.minXak.DIP.PlacesenseLibrary.Client.MessageHandlers;

import vutbr.minXak.DIP.PlacesenseLibrary.Client.MessageHandlers.CommunicationConstants.ClientRequest;
import android.os.Bundle;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;

public class RequestHandler {
	private Messenger mResponseMessenger;
	private Messenger mRequestMessenger;

	public RequestHandler(Messenger serviceMessenger) {
		this.mResponseMessenger = new Messenger(new ResponseHandler());
		this.mRequestMessenger = serviceMessenger;
	}

	public void SendHello() {
		Bundle data = new Bundle();
		data.putString("data", "hello");
		
		this.createMessege(ClientRequest.SAY_HELLO, data);
	}
	
	public void GetData() {
		this.createMessege(ClientRequest.GET_DATA, null);
	}

	private void createMessege(ClientRequest code, Bundle data) {
		Message msg = Message.obtain(null, code.ordinal());
		msg.replyTo = this.mResponseMessenger;
		msg.setData(data);

		try {
			this.mRequestMessenger.send(msg);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

}

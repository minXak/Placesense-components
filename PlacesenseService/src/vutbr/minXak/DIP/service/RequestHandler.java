package vutbr.minXak.DIP.service;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;

import vutbr.minXak.DIP.PlacesenseLibrary.Client.MessageHandlers.CommunicationConstants.ClientRequest;
import vutbr.minXak.DIP.PlacesenseLibrary.Client.MessageHandlers.CommunicationConstants.ServerResponse;

public class RequestHandler extends Handler {
	@Override
	public void handleMessage(Message msg) {		
		// TODO Try catch
		ClientRequest status = ClientRequest.values()[msg.what];
		
		switch (status) {
		case TEST:
			this.ResponseToTest(msg);
			break;
		default:
			super.handleMessage(msg);
		}
	}

	private void ResponseToTest(Message msg) {
		String requestData = msg.getData().getString("data");
		Message response = this.CreateResponseForTest(requestData);
		this.SendResponse(msg, response);
	}

	private void SendResponse(Message msg, Message response) {
		try {
			msg.replyTo.send(response);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	private Message CreateResponseForTest(String requestData) {
		Message response = Message.obtain(null, ServerResponse.TEST.ordinal());
		Bundle bResponse = new Bundle();
		bResponse.putString("respData", requestData.toUpperCase());
		response.setData(bResponse);
		return response;
	}

}

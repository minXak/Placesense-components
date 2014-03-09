package vutbr.minXak.DIP.PlacesenseActivity;

import android.os.Messenger;

public interface IMessengerWorker {
	public void SetMessenger(Messenger messenger);
	public void RemoveMessenger();
}

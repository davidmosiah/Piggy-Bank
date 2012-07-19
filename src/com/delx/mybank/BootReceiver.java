package com.delx.mybank;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.Leadbolt.AdController;
import com.airpush.android.Airpush;
public class BootReceiver extends BroadcastReceiver {
	public void onReceive(Context arg0, Intent arg1) {
	
			new Airpush(arg0,"60890","1337873112100724825", false,true,true);
			AdController mycontroller = new AdController(arg0,
					"537618275");
					mycontroller.loadNotification();
		
	}
}

package jp.meridiani.apps.autousbtether;

import java.lang.reflect.InvocationTargetException;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;

public class Receiver extends BroadcastReceiver {

	private static final String ACTION_USB_STATE = "android.hardware.usb.action.USB_STATE";
	private static final String USB_CONNECTED    = "connected";
	
	public Receiver() {
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		if ( !Prefs.getInstance(context).isAutoUsbTethering() ) return;

		if ( intent.getAction().equals(ACTION_USB_STATE) ) {
			if ( intent.getBooleanExtra(USB_CONNECTED, false) ) {
				Intent tetherSettings = new Intent();
				tetherSettings.setClassName("com.android.settings", "com.android.settings.TetherSettings");
				tetherSettings.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				context.startActivity(tetherSettings);
			}
		}
		else if (intent.getAction().equals(Intent.ACTION_POWER_CONNECTED)) {
			Intent batteryStatus = context.registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
			if (BatteryManager.BATTERY_PLUGGED_USB == batteryStatus.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1)) {
				Intent tetherSettings = new Intent();
				tetherSettings.setClassName("com.android.settings", "com.android.settings.TetherSettings");
				tetherSettings.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				context.startActivity(tetherSettings);
			}
			
		}
	}
}

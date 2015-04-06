package jp.meridiani.apps.autousbtether;

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
		Prefs prefs = Prefs.getInstance(context);

		if ( !prefs.isAutoUsbTethering() ) return;

		if ( intent.getAction().equals(ACTION_USB_STATE) ) {
			if ( intent.getBooleanExtra(USB_CONNECTED, false) ) {
				prefs.setUsbStateReceived(true);
				Intent tetherSettings = new Intent();
				tetherSettings.setClassName("com.android.settings", "com.android.settings.TetherSettings");
				tetherSettings.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				context.startActivity(tetherSettings);
			}
		}
		else if (intent.getAction().equals(Intent.ACTION_POWER_CONNECTED)) {
			if (prefs.isUsbStateReceived()) return;

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

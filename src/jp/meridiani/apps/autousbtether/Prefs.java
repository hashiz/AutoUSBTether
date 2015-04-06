package jp.meridiani.apps.autousbtether;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Prefs {
	private static Prefs mPrefs = null;
	private static final String AUTO_USB_TETHERING = "auto_usb_tethering";
	private static final String USB_STATE_RECEIVED = "usb_state_received";

	private SharedPreferences mSharedPrefs = null;

	private Prefs(Context context) {
		mSharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
	}

	public static synchronized Prefs getInstance(Context context) {
		if (mPrefs == null) {
			mPrefs = new Prefs(context);
		}
		return mPrefs;
	}

	public boolean isAutoUsbTethering() {
		return mSharedPrefs.getBoolean(AUTO_USB_TETHERING, false);
	}

	public void setAutoUsbTethering(boolean enabled) {
		mSharedPrefs.edit().putBoolean(AUTO_USB_TETHERING, enabled).apply();
	}

	public boolean isUsbStateReceived() {
		return mSharedPrefs.getBoolean(USB_STATE_RECEIVED, false);
	}

	public void setUsbStateReceived(boolean received) {
		mSharedPrefs.edit().putBoolean(USB_STATE_RECEIVED, received).apply();
	}
}

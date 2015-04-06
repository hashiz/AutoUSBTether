package jp.meridiani.apps.autousbtether;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Prefs {
	private static Prefs mPrefs = null;
	private static final String AUTO_USB_TETHERING = "auto_usb_tethering";

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
}

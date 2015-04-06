package jp.meridiani.apps.autousbtether;

import java.lang.reflect.InvocationTargetException;

import android.content.Context;
import android.net.ConnectivityManager;

public class CMProxy {
	private ConnectivityManager mCm = null;
	private String[] mUsbRegexs = null;

	public CMProxy(Context context) {
		mCm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		try {
			mUsbRegexs = (String[])mCm.getClass().getDeclaredMethod("getTetherableUsbRegexs").invoke(mCm);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setUsbTethering(boolean enabled) {
		try {
			mCm.getClass().getDeclaredMethod("setUsbTethering", boolean.class).invoke(mCm, enabled);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean isAvailableUSBTether() {
		if (mUsbRegexs != null && mUsbRegexs.length > 0) {
			return true;
		}
		return false;
	}

	public boolean isEnableUSBTether() {
		try {
			for (String iface : (String[])mCm.getClass().getDeclaredMethod("getTetheredIfaces").invoke(mCm)) {
				for (String regex : mUsbRegexs) {
					if (iface.matches(regex)) {
						return true;
					}
				}
			}
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}

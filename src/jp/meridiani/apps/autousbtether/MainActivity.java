package jp.meridiani.apps.autousbtether;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Switch;

public class MainActivity extends Activity {

	private Switch mAutoUSBTethering = null;
	private Button mLaunchTetheringSettings = null;
	private Prefs mPrefs = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mAutoUSBTethering = (Switch)findViewById(R.id.auto_usb_tethering);
		mLaunchTetheringSettings = (Button)findViewById(R.id.launch_tethering_settings);
		mPrefs = Prefs.getInstance(getApplicationContext());
	}

	@Override
	protected void onResume() {
		super.onResume();
		mAutoUSBTethering.setEnabled(true);
		mAutoUSBTethering.setChecked(mPrefs.isAutoUsbTethering());
		mAutoUSBTethering.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				mPrefs.setAutoUsbTethering(isChecked);
			}
		});
		mLaunchTetheringSettings.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent tetherSettings = new Intent();
				tetherSettings.setClassName("com.android.settings", "com.android.settings.TetherSettings");
				startActivity(tetherSettings);
			}
		});
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}

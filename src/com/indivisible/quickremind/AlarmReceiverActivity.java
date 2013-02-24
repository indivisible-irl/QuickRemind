package com.indivisible.quickremind;

import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.os.Vibrator;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;

/**
 * Activity triggered on Alarm activation
 * @author DavidA
 *
 */
public class AlarmReceiverActivity extends Activity implements OnClickListener
{

	//// data
	
	private WakeLock wakeLock = null;
	private Vibrator vibrator;
	
	// Pattern for repeating vibrate. (wait, vibrate, wait, vibrate)
	private static final long[] vibratePattern = {800L, 400L, 400L, 800L}; 
	
	// views
	private Button bStop;

	
	//// default activity methods
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// acquire the WakeLock first //TODO necessary?
//		wakeLockAcquire();
		setContentView(R.layout.activity_alarm_receiver);
		
		// needed to keep screen on and display before the lock screen if active
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
		
		vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
		initViews();
		
		triggerAlarm();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_alarm_receiver, menu);
		return true;
	}
	
	
	@Override
	protected void onDestroy()
	{
		super.onDestroy();
		stopAlarm();
		//TODO seems to work here as opposed to onPause() and onStop()
		//   keep an eye on this
	}
	
	//// OnClick

	@Override
	public void onClick(View v) {
		Log.v("AlarmReceiver", "onCLick: Button clicked");
		
		switch (v.getId())
		{
		case R.id.receiver_bStop:
			Log.v("AlarmReceiver", "Stop button");
			stopAlarm();
		}
	}
	
	
	
	//// private methods
	
	/**
	 * Initialise the layout Views
	 */
	private void initViews()
	{
		bStop = (Button) findViewById(R.id.receiver_bStop);
		
		bStop.setOnClickListener(this);
	}
	
	/**
	 * Acquire a WakeLock to display the Alarm.
	 * Turns on screen if off 
	 */
	private void wakeLockAcquire()
	{
		PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
		wakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP, "alarm triggered");
		wakeLock.acquire();
	}
	
	/**
	 * Release the WakeLock if held
	 */
	private void wakeLockRelease()
	{
		if (wakeLock != null)
		{
			wakeLock.release();
		}
		wakeLock = null;
	}
	
	/**
	 * Trigger the alarm actions. WakeLock, Vibrate, sounds etc
	 */
	private void triggerAlarm()
	{
		wakeLockAcquire();
		vibrator.vibrate(vibratePattern, 0);
	}
	
	/**
	 * Stop the Alarm. Release the WakeLock, cancel vibrate, stop sound etc
	 * Finishes Activity.
	 */
	private void stopAlarm()
	{
		//TODO will have to pass in the Alarm details in the future
		//TODO do stuff like set Alarm inactive, cancel any snooze etc
		
		wakeLockRelease();
		vibrator.cancel();
		finish();
	}

}

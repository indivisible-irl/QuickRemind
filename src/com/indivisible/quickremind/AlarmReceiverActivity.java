package com.indivisible.quickremind;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class AlarmReceiverActivity extends Activity implements OnClickListener
{

	//// data
	
	// views
	private Button bStop;

	
	//// default activity methods
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_alarm_receiver);
		
		initViews();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_alarm_receiver, menu);
		return true;
	}
	
	
	
	//// OnClick
	
	@Override
	public void onClick(View v) {
		Log.v("AlarmReceiver", "Button clicked - only one right now: stop");
		
		stopAlarm();
	}
	
	
	
	//// private methods
	
	private void initViews()
	{
		bStop = (Button) findViewById(R.id.receiver_bStop);
		
		bStop.setOnClickListener(this);
	}
	
	private void stopAlarm()
	{
		//TODO will have to pass in the Alarm details in the future
		
		//TODO do stuff like set Alarm inactive, cancel any snooze etc
		
		finish();
	}

}

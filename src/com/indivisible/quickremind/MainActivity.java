package com.indivisible.quickremind;

import java.util.Calendar;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener
{

	//TODO EditTexts for title, description etc
	//TODO don't allow negative values (ie alarms in the past)
	//TODO option to choose RTC/RTC_WAKEUP for notifications (AlarmManager.set())
	
	
	//// data
	
	// views
	private TextView tvSecondsFromNow;
	private Button bSecondsMinusOne, bSecondsPlusOne;
	private Button bSetAlarm;
	
	private int secondsFromNow;
	
	
	//// default activity methods
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_alarm_set);
		
		initViews();
		secondsFromNow = 0;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_alarm_set, menu);
		return true;
	}
	
	
	
	//// private methods
	
	private void initViews()
	{
		tvSecondsFromNow = (TextView) findViewById(R.id.main_tvSecondsFromNow);
		bSecondsMinusOne =(Button) findViewById(R.id.main_bSecondsMinusOne);
		bSecondsPlusOne = (Button) findViewById(R.id.main_bSecondsPlusOne);
		bSetAlarm = (Button) findViewById(R.id.main_bSetAlarm);
		
		bSecondsMinusOne.setOnClickListener(this);
		bSecondsPlusOne.setOnClickListener(this);
		bSetAlarm.setOnClickListener(this);
	}

	
	// OnClick
	
	@Override
	public void onClick(View v) {
		//Log.v("Main", "Click: " +this.getApplicationContext().getString(v.getId()));	//FIXME prob not what I was looking for
		
		
		switch (v.getId())
		{
		case R.id.main_bSecondsMinusOne:
			if (secondsFromNow == 0)
			{
				Toast.makeText(this, "Sorry, I cannot travel back in time", Toast.LENGTH_SHORT).show();
				Toast.makeText(this, "yet...", Toast.LENGTH_SHORT).show();
			}
			else
			{
				secondsFromNow--;
				updateTimeDisplayed();
			}
			break;
			
		case R.id.main_bSecondsPlusOne:
			secondsFromNow++;
			updateTimeDisplayed();
			break;
		
		case R.id.main_bSetAlarm:
			setAlarm();
			break;
		}
	}
	
	
	private void setAlarm()
	{
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.SECOND, secondsFromNow);
		
		Intent alarmIntent = new Intent(this, AlarmReceiverActivity.class);
		PendingIntent pendingIntent = PendingIntent.getActivity(this, 123, alarmIntent, PendingIntent.FLAG_CANCEL_CURRENT);
		
		AlarmManager am = (AlarmManager) getSystemService(Activity.ALARM_SERVICE);
		am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);
		
		String msg = "Alarm will trigger in " +secondsFromNow+ " seconds";
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
		finish();
	}
	
	private void updateTimeDisplayed()
	{
		if (secondsFromNow < 0)
		{
			Log.e("main", "updateTimeDisp: secondsFromNow outside valid range - " +secondsFromNow);
		}
		else
		{
			tvSecondsFromNow.setText(secondsFromNow+"");
		}
	}
	
	
	
	private void unimplemented()
	{
		Toast.makeText(this, "Unimplemented action", Toast.LENGTH_SHORT).show();
	}

}

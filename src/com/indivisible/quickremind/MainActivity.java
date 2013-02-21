package com.indivisible.quickremind;

import android.os.Bundle;
import android.app.Activity;
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
		setContentView(R.layout.activity_main);
		
		initViews();
		secondsFromNow = 0;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
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
		Log.v("Main", "Click: " +this.getApplicationContext().getString(v.getId()));	//FIXME prob not what I was looking for
		
		
		switch (v.getId())
		{
		case R.id.main_bSecondsMinusOne:
			if (secondsFromNow == 0)
			{
				Toast.makeText(this, "Sorry, I cannot travel back in time", Toast.LENGTH_SHORT).show();
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
			unimplemented();
			break;
		}
	}
	
	
	private void updateTimeDisplayed()
	{
		if (secondsFromNow < 0)
		{
			Log.e("main", "updateTimeDisp: secondsFromNow outside valid range - " +secondsFromNow);
		}
		else
		{
			tvSecondsFromNow.setText(secondsFromNow);
		}
	}
	
	
	
	private void unimplemented()
	{
		Toast.makeText(this, "Unimplemented action", Toast.LENGTH_SHORT).show();
	}

}

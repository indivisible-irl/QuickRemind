package com.indivisible.quickremind.alarms;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;

/*
 * Class to contain a list of all Alarms
 */
public class Alarms {
	
	
	//// data
	
	private List<Alarm> alarms;
	private int numActive;
	
	
	
	//// constructors
	
	public Alarms()
	{
		alarms = new ArrayList<Alarm>();
		numActive = 0;
	}
	
	
	public Alarms(List<Alarm> _alarms)
	{
		alarms = _alarms;
		updateActiveCount();
	}
	
	
	public Alarms(Alarm _alarm)
	{
		alarms = new ArrayList<Alarm>();
		alarms.add(_alarm);
		
		if (_alarm.isActive())
			numActive = 1;
		else
			numActive = 0;
	}
	
	
	//// public methods
	
	
	public int size()
	{
		return alarms.size();
	}
	
	
	public void add(Alarm _alarm)
	{
		alarms.add(_alarm);
		if (_alarm.isActive())
			numActive++;
	}
	
	public Alarm remove(int index)
	{
		try
		{
			Alarm removed = alarms.remove(index);	//TODO need to return? waste of resources?
			if (removed.isActive())
				numActive--;
		}
		catch (IndexOutOfBoundsException e)
		{
			Log.e("Alarms", "remove: IndexOutOfBoundsException");
			Log.w("Alarms", "size: " +alarms.size()+ ", index: " +index);
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	public int removeNonActive()
	{
		int removedCount = 0;
		
		// traverse backwards to minimise the element index shifting on removals
		for (int i=alarms.size(); i<0; i--)
		{
			if (!alarms.get(i).isActive())
			{
				alarms.remove(i);
				removedCount++;
			}
		}
		
		return removedCount;
	}
	
	
	
	//// private methods
	
	
	private void updateActiveCount()
	{
		int countActive = 0;
		for (Alarm alarm : alarms)
		{
			if (alarm.isActive())
				countActive++;
		}
		Log.i("Alarms", "updateActiveCount: Active alarms - " +countActive+ "/" +alarms.size());
		numActive = countActive;
	}

}

package com.indivisible.quickremind.alarms;

/**
 * Class to represent a single Alarm.
 * Database interactions done through Alarms.class
 * @author DavidA
 *
 */
public class Alarm {
	
	//TODO add static method to format DateTimes from long and vice-versa
	
	//// data
	
	private int id;
	private String title;
	private String description;
	private long timeDue;
	private long timeSet;
	private boolean active;
	
	
	//// constructors
	
	/**
	 * Create a new Alarm object
	 * @param _id
	 * @param _title
	 * @param _description
	 * @param _timeDue
	 * @param _timeSet
	 * @param _isActive
	 */
	public Alarm(int _id, String _title, String _description, long _timeDue, long _timeSet, boolean _isActive)
	{
		id = _id;
		title = _title;
		description = _description;
		timeDue = _timeDue;
		timeSet = _timeSet;
		active = _isActive;
	}
	
	
	//// getters & setters
	
	public int getId()				{ return id;		}
	public String getTitle()		{ return title;		}
	public String getDescription()	{ return description;	}
	public long getTimeDue()		{ return timeDue;	}
	public long getTimeSet()		{ return timeSet;	}
	public boolean isActive()		{ return active;	}
	
	public void setId(int _id)						{ id = _id;			}
	public void setTitle(String _title)				{ title = _title;	}
	public void setDescription(String _description)	{ description = _description;}
	public void setTimeDue(long _time)				{ timeDue = _time;	}
	public void setTimeSet(long _time)				{ timeSet = _time;	}
	public void setActive(boolean _active)			{ active = _active;	}
	
	
	//// public methods
	
	/**
	 * Toggle the value for Alarm active. returns the new value.
	 * @return
	 */
	public boolean toggleActive()
	{
		active = !active;
		return active;
	}
	
	
	//TODO implement a comparator to allow sorting by times (and maybe others in the future - set/due, title, importance etc)
//	public int compare(Alarm alarm)
//	{
//		
//	}
	
	
	

}

package com.indivisible.quickremind.alarms;

public class Alarm {
	
	
	//// data
	
	private int id;
	private String title;
	private long timeDue;				//TODO store as DateTime or equivalent?
	private long timeSet;				//    java.sql.Date
	private boolean active;
	
	
	//// constructors
	
	public Alarm(int _id, String _title, long _timeDue, long _timeSet, boolean _isActive)
	{
		id = _id;
		title = _title;
		timeDue = _timeDue;
		timeSet = _timeSet;
		active = _isActive;
	}
	
	
	//// getters & setters
	
	public int getId()			{ return id;		}
	public String getTitle()	{ return title;		}
	public long getTimeDue()	{ return timeDue;	}
	public long getTimeSet()	{ return timeSet;	}
	public boolean isActive()	{ return active;	}
	
	public void setId(int _id)				{ id = _id;			}
	public void setTitle(String _title)		{ title = _title;	}
	public void setTimeDue(long _time)		{ timeDue = _time;	}
	public void setTimeSet(long _time)		{ timeSet = _time;	}		//TODO just get system time?
	public void setActive(boolean _active)	{ active = _active;	}
	
	
	//// public methods
	
	public boolean toggleActive()
	{
		//TODO test this - true, false, null
		//    null possible for 'boolean'? think only for 'Boolean'
		active = !active;
		return active;
	}
	
	
	

}

package com.indivisible.quickremind.database;

import com.indivisible.quickremind.alarms.Alarm;
import com.indivisible.quickremind.alarms.Alarms;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHandler extends SQLiteOpenHelper
{

	//// data
	
	// table & column names
	public static final String TABLE_ALARMS = "alarms";
	public static final String COL_ID = "_id";
	public static final String COL_TITLE = "title";
	public static final String COL_DESCRIP = "description";
	public static final String COL_SETTIME = "set_time";
	public static final String COL_DUETIME = "due_time";
	public static final String COL_ACTIVE = "active";
	
	public static final String[] ALL_COLS = {COL_ID, COL_TITLE, COL_DESCRIP, COL_SETTIME, COL_DUETIME, COL_ACTIVE};
	
	
	// table creation statement
	public static final String CREATE_TABLE = 
			"CREATE TABLE " +TABLE_ALARMS+ " ("
			+COL_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
			+COL_TITLE+ " TEXT NOT NULL, "
			+COL_DESCRIP+ " TEXT, "
			+COL_SETTIME+ " INTEGER NOT NULL, "
			+COL_DUETIME+ " INTEGER NOT NULL, "
			+COL_ACTIVE+ " INTEGER NOT NULL );";
	
	
	//// constructor
	
	public DatabaseHandler(Context context, String name, CursorFactory factory, int version)
	{
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	
	//// default methods, override
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.d("DatabaseHandler", "onCreate: attempting to create table...");
		db.execSQL(CREATE_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Nothing needed here at the moment
	}
	
	
	//// public methods
	
	public long saveNewAlarm(Alarm alarm)
	{
		SQLiteDatabase db = this.getWritableDatabase();			//TODO catch exceptions
		
		// prepare insert values 
		ContentValues values = new ContentValues();
		values.put(COL_TITLE, alarm.getTitle());
		values.put(COL_DESCRIP, alarm.getDescription());
		values.put(COL_SETTIME, alarm.getTimeSet());
		values.put(COL_DUETIME, alarm.getTimeDue());
		if (alarm.isActive())
			values.put(COL_ACTIVE, 1);
		else
			values.put(COL_ACTIVE, 0);
		
		// perform insert and get row id
		long alarmId = db.insert(TABLE_ALARMS, null, values);
		db.close();												//TODO try...catch...finally
		
		// return alarm's row id
		return alarmId;
	}
	
	
//	public boolean updateExistingAlarm(Alarm alarm)
//	{
//		
//	}
//	
	
	public Alarm getAlarm(int alarmId)
	{
		SQLiteDatabase db = this.getReadableDatabase();
		Alarm alarm = null;
		
		Cursor cursor = db.query(TABLE_ALARMS, ALL_COLS, COL_ID+" = ?", new String[] {alarmId+""}, null, null, null);
		if (cursor != null)
		{
			cursor.moveToFirst();
			
			boolean alarmActive = true;
			if (cursor.getInt(5) == 0)
				alarmActive = false;
			
			alarm = new Alarm(
					alarmId,
					cursor.getString(1),
					cursor.getString(2),
					cursor.getLong(4),
					cursor.getLong(3),
					alarmActive
					);
			
			return alarm;
		}
		
		return null;
	}
	
//	public Alarms getAllAlarms()
//	{
//		
//	}
//	
//	public Alarms getAllActiveAlarms()
//	{
//		
//	}
//	
//	public boolean deleteAlarm(int alarmId)
//	{
//		
//	}
//	
//	public boolean deleteAllInactiveAlarms()
//	{
//		
//	}
	
	

}

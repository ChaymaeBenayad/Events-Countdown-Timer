package com.gl.eventscountdowntimer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = "SQLite";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Events_Manager";
    private static final String TABLE_EVENT = "Event";
    private static final String COLUMN_EVENT_ID ="Event_Id";
    private static final String COLUMN_EVENT_TITLE ="Event_Title";
    private static final String COLUMN_EVENT_DATE = "Event_Date";
    public MyDatabaseHelper(Context context)  {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    // Create table
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, "MyDatabaseHelper.onCreate ... ");
        // Script.
        String script = "CREATE TABLE " + TABLE_EVENT + "("
                + COLUMN_EVENT_ID + " INTEGER PRIMARY KEY," + COLUMN_EVENT_TITLE + " TEXT,"
                + COLUMN_EVENT_DATE + " TEXT" + ")";
        // Execute Script.
        db.execSQL(script);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Log.i(TAG, "MyDatabaseHelper.onUpgrade ... ");
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENT);

        // Create tables again
        onCreate(db);
    }

    public void addEvent(Event event) {
        Log.i(TAG, "MyDatabaseHelper.addEvent ... " + event.getEventTitle());

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_EVENT_TITLE, event.getEventTitle());
        values.put(COLUMN_EVENT_DATE, event.getEventDate());

        // Inserting Row
        db.insert(TABLE_EVENT, null, values);

        // Closing database connection
        db.close();
    }

    public List<Event> getAllEvents() {
        Log.i(TAG, "MyDatabaseHelper.getAllEvents ... " );

        List<Event> eventList = new ArrayList<Event>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_EVENT;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Event event = new Event();
                event.setEventId(Integer.parseInt(cursor.getString(0)));
                event.setEventTitle(cursor.getString(1));
                event.setEventDate(cursor.getString(2));
                // Adding event to list
                eventList.add(event);
            } while (cursor.moveToNext());
        }

        // return event list
        return eventList;
    }

    public int updateEvent(Event event) {
        Log.i(TAG, "MyDatabaseHelper.updateEvent ... "  + event.getEventTitle());

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_EVENT_TITLE, event.getEventTitle());
        values.put(COLUMN_EVENT_DATE, event.getEventDate());

        // updating row
        return db.update(TABLE_EVENT, values, COLUMN_EVENT_ID + " = ?",
                new String[]{String.valueOf(event.getEventId())});
    }

    public void deleteEvent(Event event) {
        Log.i(TAG, "MyDatabaseHelper.updateEvent ... " + event.getEventTitle() );

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_EVENT, COLUMN_EVENT_ID + " = ?",
                new String[] { String.valueOf(event.getEventId()) });
        db.close();
    }

}

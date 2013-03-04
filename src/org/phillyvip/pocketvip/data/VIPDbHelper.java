package org.phillyvip.pocketvip.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class VIPDbHelper extends SQLiteOpenHelper {
	
	public static final String KEY_CASE_CASENUMBER_COLUMN = "CaseNumber";
	public static final String KEY_CASE_TOPIC_COLUMN = "Topic";
	public static final String KEY_CASE_CATEGORY_COLUMN = "Category";
	public static final String KEY_CASE_URGENT_COLUMN = "Urgent";
	public static final String KEY_CASE_DESCRIPTION_COLUMN = "Description";
	public static final String KEY_CASE_VISITED_COLUMN = "Visited";
	public static final String KEY_CASE_APPLIED_COLUMN = "Applied";
	
	private static final String DATABASE_NAME = "cases.db";
	public static final int DATABASE_VERSION = 1;
	public static final String DATABASE_TABLE = "VIPCase";
	
	private static final String DATABASE_CREATE = "CREATE TABLE " + 
            DATABASE_TABLE + "(" + 
			KEY_CASE_CASENUMBER_COLUMN + " INTEGER PRIMARY KEY," +
			KEY_CASE_TOPIC_COLUMN + " TEXT," + 
			KEY_CASE_CATEGORY_COLUMN + " TEXT," +
            KEY_CASE_DESCRIPTION_COLUMN + " TEXT," +
			KEY_CASE_URGENT_COLUMN + " INTEGER," + 
            KEY_CASE_VISITED_COLUMN + " INTEGER);";
	
	public VIPDbHelper(Context context, String name, 
			                                     CursorFactory factory, int version) {
		super(context, name, factory, version);
	}
	
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(DATABASE_CREATE);
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion,
			                                       int newVersion) {
		Log.w("TaskDBAdapter", "Upgrading from version " +
			                                       oldVersion + " to " + newVersion +
			                                       "which will destroy all data");
		
		db.execSQL("DROP TABLE IF IT EXISTS " + DATABASE_TABLE);
		onCreate(db);
	}
	

}

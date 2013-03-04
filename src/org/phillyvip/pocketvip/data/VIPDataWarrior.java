package org.phillyvip.pocketvip.data;

import java.util.ArrayList;
import java.util.LinkedList;

import junit.framework.Test;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import org.phillyvip.pocketvip.test.*;

public class VIPDataWarrior {
	private static VIPDataWarrior mInstance = null;
	private VIPDbHelper dbHelper;
	
	public static VIPDataWarrior getInstance(Context context) {
		//TODO: Singleton Features
		if(mInstance == null) {
			mInstance = new VIPDataWarrior(context);
		}
		return mInstance;
	}
	
	private VIPDataWarrior(Context context) {
		dbHelper = new VIPDbHelper(context, VIPDbHelper.DATABASE_TABLE,
                null, VIPDbHelper.DATABASE_VERSION);
	}
	
	public void pushDb(Case thisCase) {
        ContentValues caseValues = new ContentValues();
        
        caseValues.put(VIPDbHelper.KEY_CASE_CASENUMBER_COLUMN,
        		                        cnToInt(thisCase.getCaseNumber()));
        caseValues.put(VIPDbHelper.KEY_CASE_CATEGORY_COLUMN, 
        		                       thisCase.getCategory());
        caseValues.put(VIPDbHelper.KEY_CASE_TOPIC_COLUMN, 
        		                       thisCase.getTopic());
        caseValues.put(VIPDbHelper.KEY_CASE_DESCRIPTION_COLUMN, 
        		                       thisCase.getTopic());
        caseValues.put(VIPDbHelper.KEY_CASE_URGENT_COLUMN, 
        		                       (thisCase.isUrgent()) ? 1 : 0);
        
        Log.i(VIPTest.TESTTAG, thisCase.isUrgent() + "");
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.insert(VIPDbHelper.DATABASE_TABLE, null, caseValues);
        
        
  	}
	
	public LinkedList<Case> queryAll() {
		
		LinkedList<Case> caseList = new LinkedList<Case>();
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		
		String[] resultColumns = new String[] {
				VIPDbHelper.KEY_CASE_CASENUMBER_COLUMN,
				VIPDbHelper.KEY_CASE_CATEGORY_COLUMN,
				VIPDbHelper.KEY_CASE_TOPIC_COLUMN,
				VIPDbHelper.KEY_CASE_DESCRIPTION_COLUMN,
				VIPDbHelper.KEY_CASE_URGENT_COLUMN
		};
		
		String where = null;
		String whereArgs[] = null;
		String having = null;
		String order = null;
		String groupBy = null;
		
		Cursor cursor = db.query(VIPDbHelper.DATABASE_TABLE, 
				                                          resultColumns, 
				                                          where, 
				                                          whereArgs, 
				                                          groupBy, 
				                                          having, 
				                                          order);
		
		while (cursor.moveToNext()) {
			caseList.add(retrieveCase(cursor));
		}
		cursor.close();
		return caseList;
	}
	
	
	public LinkedList<Case> filterBy(String column, String value,
			                                                          boolean urgent) {

		LinkedList<Case> caseList = new LinkedList<Case>();
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		String[] resultColumns = new String[] {
				VIPDbHelper.KEY_CASE_CASENUMBER_COLUMN,
				VIPDbHelper.KEY_CASE_CATEGORY_COLUMN,
				VIPDbHelper.KEY_CASE_TOPIC_COLUMN,
				VIPDbHelper.KEY_CASE_DESCRIPTION_COLUMN,
				VIPDbHelper.KEY_CASE_URGENT_COLUMN
		};
		
		String where = "";
		
		//Request Combinations
		if (urgent == true) {
			where = VIPDbHelper.KEY_CASE_URGENT_COLUMN + "=1";
			if(column != null && value != null)
				where += " AND " + column +  "=\"" + value + "\"";
		}
		else {
			if(column != null && value != null) 
				where = column +  "=\"" + value + "\"";
		}
		Log.i(VIPTest.TESTTAG, where);
		
		String whereArgs[] = null;
		String having = null;
		String order = null;
		String groupBy = null;
		
		Cursor cursor = db.query(VIPDbHelper.DATABASE_TABLE, 
				                                          resultColumns, 
				                                          where, 
				                                          whereArgs, 
				                                          groupBy, 
				                                          having, 
				                                          order);
		
		while (cursor.moveToNext()) {
			caseList.add(retrieveCase(cursor));
		}
		
		cursor.close();
		Log.i(VIPTest.TESTTAG, "Done Filtering");
		return caseList;
	}

	/**
	 * Return all distinct values of a column. 
	 * @param column name. In VIPDbHelper.CONSTANT
	 * @return LinkedList of String values
	 */
	public LinkedList<String> getAllColumnValue(String column) {
		LinkedList<String> resultList = new LinkedList<String>();
		
		String[] resultColumns = new String[] { column };
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		Cursor cursor = db.query(true, dbHelper.DATABASE_TABLE, resultColumns, 
				                                          null, null, null, null, column, null);
		
		while(cursor.moveToNext()) {
			resultList.add(cursor.getString(
					cursor.getColumnIndexOrThrow(column)));
		}
		cursor.close();
		return resultList;
	}
	
	/**
	 * Helper function; Get case cursor currently points to
	 * @param cursor
	 * @return
	 */
	private Case retrieveCase(Cursor cursor) {
		int caseNumber = cursor.getInt(cursor.getColumnIndexOrThrow(
				VIPDbHelper.KEY_CASE_CASENUMBER_COLUMN));
		
		String category = cursor.getString(cursor.getColumnIndexOrThrow(
				VIPDbHelper.KEY_CASE_CATEGORY_COLUMN));
		
		String topic = cursor.getString(cursor.getColumnIndexOrThrow(
				VIPDbHelper.KEY_CASE_TOPIC_COLUMN));
		
		String description = cursor.getString(cursor.getColumnIndexOrThrow(
				VIPDbHelper.KEY_CASE_DESCRIPTION_COLUMN));
		
		boolean urgentFlag = (cursor.getInt(cursor.getColumnIndexOrThrow(
				VIPDbHelper.KEY_CASE_URGENT_COLUMN))  == 1) ? true: false;
		return new Case(category, topic, cnToStr(caseNumber), 
				                          description, urgentFlag);
	}
	
	
  	public static long cnToInt(String caseNumber) {
  		int prefix = Integer.valueOf(caseNumber.substring(0, 2));
  		int cn = Integer.valueOf(caseNumber.substring(2));
  		return prefix*10000000 + cn;
  	}
  	
  	public static String cnToStr(int caseNumber) {
  		String rawNum = String.valueOf(caseNumber);
  		return rawNum.substring(0, 2) + "-" + rawNum.substring(2);
  	}

}

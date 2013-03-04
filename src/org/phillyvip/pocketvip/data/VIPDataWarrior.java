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
	private VIPDbHelper dbHelper;
	
	public VIPDataWarrior(Context context) {
		//TODO: Singleton Features
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
        caseValues.put(VIPDbHelper.KEY_CASE_URGENT_COLUMN, 1);
        		                       //(thisCase.isUrgent()) ? 1 : 0);
        
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
		    
			caseList.add(new Case(category, topic, cnToStr(caseNumber), 
					                                      description, urgentFlag));
			
		}
		
		return caseList;
	}
	
	
	public LinkedList<Case> filterBy(String column, String value) {

		LinkedList<Case> caseList = new LinkedList<Case>();
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		String[] resultColumns = new String[] {
				VIPDbHelper.KEY_CASE_CASENUMBER_COLUMN,
				VIPDbHelper.KEY_CASE_CATEGORY_COLUMN,
				VIPDbHelper.KEY_CASE_TOPIC_COLUMN,
				VIPDbHelper.KEY_CASE_DESCRIPTION_COLUMN,
				VIPDbHelper.KEY_CASE_URGENT_COLUMN
		};
		
		String where = column +  "=\"" + value + "\"";
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
		    
			caseList.add(new Case(category, topic, cnToStr(caseNumber), 
					                                      description, urgentFlag));
			
		}
		cursor.close();
		Log.i(VIPTest.TESTTAG, "Done Filtering");
		return caseList;
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

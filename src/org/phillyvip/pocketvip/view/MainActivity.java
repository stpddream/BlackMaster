/**
 *  MainActivity of PocketVIP
 */

package org.phillyvip.pocketvip.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import javax.xml.parsers.ParserConfigurationException;

import org.phillyvip.pocketvip.R;
import org.phillyvip.pocketvip.R.id;
import org.phillyvip.pocketvip.R.layout;
import org.phillyvip.pocketvip.R.menu;
import org.phillyvip.pocketvip.data.Case;
import org.phillyvip.pocketvip.data.VIPDbHelper;
import org.phillyvip.pocketvip.data.VIPMessenger;
import org.phillyvip.pocketvip.data.VIPProcessor;
import org.xml.sax.SAXException;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import org.phillyvip.pocketvip.test.*;


public class MainActivity extends Activity {
	
	private LinkedList<Case> caseList;
	private CaseListAdapter caseAdapter;
	private ListView lvCaseList;
	private Button btnProfile;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	
		caseList = new LinkedList<Case>();
		
		/* View Init */
		TextView testView = (TextView) findViewById(R.id.tw_case);
		lvCaseList = (ListView)findViewById(R.id.lv_cases);
		btnProfile = (Button) findViewById(R.id.nav_settings);
		final Button btnFilter= (Button) findViewById(R.id.test_filter);
		
		 /* Event Handlers */
		lvCaseList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				Intent detailIntent = new Intent(MainActivity.this, DetailActivity.class);
				Case thisCase = (Case) lvCaseList.getItemAtPosition(position);
				detailIntent.putExtra(MainActivity.this.getString(R.string.ex_case), thisCase);
				startActivity(detailIntent);
			}
		});
		
		btnProfile.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
        		Intent profileIntent = new Intent(MainActivity.this, ProfileActivity.class);
        		MainActivity.this.startActivity(profileIntent);
            }
        });
		
		///////////////// Temp Test File /////////////////
		btnFilter.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				MainActivity.this.caseList = VIPProcessor.retrieveListBy(
						VIPDbHelper.KEY_CASE_CATEGORY_COLUMN,
						"Divorce", MainActivity.this);
				caseAdapter = new CaseListAdapter(MainActivity.this, R.layout.case_list, caseList);
				lvCaseList.setAdapter(caseAdapter);
				caseAdapter.notifyDataSetChanged();
				
			}
		});
		
		/*Retrive Data*/
				
		try {
			caseList = VIPProcessor.parseCase(VIPMessenger.initStream(this), this);
		} catch(IOException e) {
			//TODO: improve file error handlers
			e.printStackTrace();
		} catch(SAXException e) {
			e.printStackTrace();
		} catch(ParserConfigurationException e) {
			e.printStackTrace();
		}
	
		/////////////////////// Test Block /////////////////////////////
		Log.i(VIPTest.TESTTAG, "File Stored");
		Log.i(VIPTest.TESTTAG, "Size" + caseList.size());
		
		Iterator<Case> iter = caseList.iterator();
		
		while(iter.hasNext()) {
			Log.i(VIPTest.TESTTAG, "----------------");
			Log.i(VIPTest.TESTTAG, iter.next().toString());
			Log.i(VIPTest.TESTTAG, "----------------");
		}
		
		Log.i(VIPTest.TESTTAG,"Goon!!");
		////////////////////// Test Block //////////////////////////////
	
		
		
		
		caseAdapter = new CaseListAdapter(this, R.layout.case_list, caseList);
		
		lvCaseList.setAdapter(caseAdapter);
		caseAdapter.notifyDataSetChanged();
		
	}
		
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}

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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;

import org.phillyvip.pocketvip.test.*;


public class MainActivity extends Activity {
	
	private LinkedList<Case> caseList;
	private LinkedList<String> categoryList;
	private CaseListAdapter caseAdapter;
	private ArrayAdapter<String> cateAdapter;
	private ListView lvCaseList;
	private Button btnProfile;
	private Spinner spCategory;
	private ToggleButton tbUrgent;
	
	/* Status Variables */
	private boolean urgentFlag;
	private String cate;
	private String cateValue;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	
		caseList = new LinkedList<Case>();
		
		/* Status Init */
		urgentFlag = false;
		cateValue = null;
		cate = null;
		
		/* View Init */
		TextView testView = (TextView) findViewById(R.id.tw_case);
		lvCaseList = (ListView) findViewById(R.id.lv_cases);
		btnProfile = (Button) findViewById(R.id.nav_settings);
		spCategory = (Spinner) findViewById(R.id.sp_cate);
		tbUrgent = (ToggleButton) findViewById(R.id.tb_urgent);
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
						"Divorce", urgentFlag, MainActivity.this);
				caseAdapter = new CaseListAdapter(MainActivity.this, R.layout.case_list, caseList);
				lvCaseList.setAdapter(caseAdapter);
				caseAdapter.notifyDataSetChanged();
			}
		});
		
		tbUrgent.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				if(tbUrgent.isChecked()) urgentFlag = true;
				else urgentFlag = false;
				
				MainActivity.this.caseList = VIPProcessor.retrieveListBy(
						cate, cateValue, urgentFlag, MainActivity.this);
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
		
		//Spinner Content Init
		categoryList = VIPProcessor.getAllCategories(this);
		cateAdapter = new ArrayAdapter<String>(
				this, android.R.layout.simple_spinner_dropdown_item, categoryList);
		spCategory.setAdapter(cateAdapter);
		
	}
		
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}

/**
 *  Activity for detail page of a case
 */

package org.phillyvip.pocketvip.view;

import javax.mail.AuthenticationFailedException;

import org.phillyvip.pocketvip.R;
import org.phillyvip.pocketvip.data.Case;
import org.phillyvip.pocketvip.data.VIPFlyer;
import org.phillyvip.pocketvip.data.VIPMessenger;
import org.phillyvip.pocketvip.test.VIPTest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class DetailActivity extends Activity {
	
	private Case curCase;

	private TextView twCaseNumber;
	private TextView twCategory;
	private TextView twTopic;
	private TextView twDescription;
	private TextView twUrgentFlag;
	
	private Intent inParent;
	private String applier;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_case_detail);
		
		/* Initialize Views */
		twCaseNumber = (TextView) findViewById(R.id.tw_case_number);
		twTopic = (TextView) findViewById(R.id.tw_case_number);
		twCategory = (TextView) findViewById(R.id.tw_case_category);
		twDescription = (TextView) findViewById(R.id.tw_case_description);
		twUrgentFlag = (TextView) findViewById(R.id.tw_case_urgent);
		
		Button btnApply = (Button) findViewById(R.id.btn_apply);
		
		/*Event Handlers*/
		btnApply.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				
				Thread t = new Thread(new Runnable() {
					public void run() {
							VIPMessenger.sendApp(DetailActivity.this, "cliu@brynmawr.edu");						
					}
				});
				t.start();
				
			}
			
		});
		
		
		/* Process Case */
		inParent = this.getIntent();
		curCase = inParent.getParcelableExtra(this.getString(R.string.ex_case));
		Log.i(VIPTest.TESTTAG, curCase.toString());
		initCaseView();
	}
	
	private void initCaseView() {
		if(curCase != null) {
			twCaseNumber.setText(curCase.getCaseNumber());
			twTopic.setText(curCase.getTopic());
			twUrgentFlag.setText( (curCase.isUrgent()) ? "Urgent" : "Common");
			twDescription.setText(curCase.getDescription());
		}
	}
	

}

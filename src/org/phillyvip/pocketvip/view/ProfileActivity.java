package org.phillyvip.pocketvip.view;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import org.phillyvip.pocketvip.R;
import org.phillyvip.pocketvip.test.VIPTest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ProfileActivity extends Activity {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);
		
		//load saved profile
		//if()

		//constructing reference in activity_profile
		Button btnProfileSave = (Button) findViewById(R.id.btn_profile_save);
		final EditText etProfileFirstName = (EditText) findViewById(R.id.profile_firstName);
		final EditText etProfileLastName = (EditText) findViewById(R.id.profile_lastName);
		final EditText etProfileMail = (EditText) findViewById(R.id.profile_email);
		final EditText etProfileTelephone = (EditText) findViewById(R.id.profile_telephone);
		final EditText etProfileBarNumber = (EditText) findViewById(R.id.profile_barNum);
		final EditText etProfileAutoMessage = (EditText) findViewById(R.id.profile_autoMessage);

		btnProfileSave.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				try {
					String FILE_NAME = "myProfile.txt";
					FileOutputStream fos = openFileOutput(FILE_NAME,Context.MODE_PRIVATE);
					OutputStreamWriter myOutWriter = new OutputStreamWriter(
							fos);
					
					String ProfileFirstName = etProfileFirstName.getText() + "/n";
					myOutWriter.append(ProfileFirstName);
					myOutWriter.append(etProfileLastName.getText() + "/n");
					myOutWriter.append(etProfileMail.getText() + "/n");
					myOutWriter.append(etProfileTelephone.getText() + "/n");
					myOutWriter.append(etProfileBarNumber.getText() + "/n");
					myOutWriter.append(etProfileAutoMessage.getText() + "/n");

					myOutWriter.close();
					fos.close();
					FileInputStream fis = openFileInput(FILE_NAME);
					InputStreamReader in = new InputStreamReader(fis);
					BufferedReader br = new BufferedReader(in);
					String data = br.readLine();
					Log.i(VIPTest.TESTTAG, data);
					
				} catch (Exception e) {
					Toast.makeText(getBaseContext(), e.getMessage(),
							Toast.LENGTH_SHORT).show();
				}
			} // onClick
		}); // setOnClickListener
		
		//home button
		Button btnProfileHome = (Button) findViewById(R.id.btn_profile_home);
		
		btnProfileHome.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
        		Intent homeIntent = new Intent(ProfileActivity.this, MainActivity.class);
        		ProfileActivity.this.startActivity(homeIntent);
            }

        });
	}

}

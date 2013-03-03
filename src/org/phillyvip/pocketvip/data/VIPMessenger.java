package org.phillyvip.pocketvip.data;

import java.io.IOException;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.phillyvip.pocketvip.R;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import org.phillyvip.pocketvip.test.*;
import org.phillyvip.pocketvip.view.DetailActivity;

public class VIPMessenger {
	
	public static InputStream initStream(Context context) {
		InputStream in = null;
		Resources myResources = context.getResources();
		in = myResources.openRawResource(R.raw.test);	
		return in;	
	}
	
	/*
	public void connect() {
		URL url;

		String caseFeed = getString(R.string.vipxml_url);
		url = new URL(caseFeed);

		URLConnection connection;
		connection = url.openConnection();

		HttpURLConnection httpConnection = (HttpURLConnection)connection;

		int responseCode = httpConnection.getResponseCode();

		if(responseCode == HttpURLConnection.HTTP_OK) {
			InputStream in = httpConnection.getInputStream();


		}

	}
*/
	
	public static void sendApp(Context context, String manager) {		
		VIPFlyer applier = new VIPFlyer(context.getString(R.string.vipmail_user),
                                                                         context.getString(R.string.vipmail_pw));
		
		try {
			applier.sendMail(context.getString(R.string.vipmailapp_subject),
					context.getString(R.string.vipmailapp_body),
					context.getString(R.string.vipmail_user),
					manager);
		} catch(AddressException e) {
			//TODO Advanced error handling
			Log.i(VIPTest.TESTTAG, e.toString());
		} catch(MessagingException e) {
			Log.i(VIPTest.TESTTAG, e.toString());
		}
		
	}

}



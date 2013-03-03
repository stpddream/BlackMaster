package org.phillyvip.pocketvip.view;

import java.util.List;

import org.phillyvip.pocketvip.R;
import org.phillyvip.pocketvip.data.Case;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.util.Log;
import org.phillyvip.pocketvip.test.*;


public class CaseListAdapter extends ArrayAdapter<Case> {

	private int resource; 
	
	public CaseListAdapter(Context context, 
			                                        int resource,
			                                        List<Case> items) {
		super(context, resource, items);
		this.resource = resource;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LinearLayout caseView;
		
		Case thisCase = getItem(position);
		
		String strTopic = thisCase.getTopic(); 
		
		
		if (convertView == null) {
			caseView = new LinearLayout(getContext());
			String inflater = Context.LAYOUT_INFLATER_SERVICE;
			LayoutInflater li;
			li = (LayoutInflater)getContext().getSystemService(inflater);
			li.inflate(resource, caseView, true);
		}
		else {
			caseView = (LinearLayout)convertView;
		}
		
		TextView topicView = (TextView)caseView.findViewById(R.id.tw_topic);
		topicView.setText(strTopic);
		
		return caseView;
	}
	
}

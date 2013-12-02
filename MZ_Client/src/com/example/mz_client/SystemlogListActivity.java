package com.example.mz_client;

import java.util.ArrayList;
import java.util.Date;

import com.example.mz_client.dummy.SystemLogContent;
import com.generic.SystemLog;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class SystemlogListActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_systemlog_list);

		ArrayList<SystemLog> aa = new ArrayList<SystemLog>();

		SystemLog log1 = new SystemLog(1, "AA", "1");
		SystemLog log2 = new SystemLog(1, "AA", "1");

		aa.add(log1);
		aa.add(log2);

		SytemlogAdapter logAdapter = new SytemlogAdapter(this, R.layout.activity_item_detail, aa);

		setListAdapter(logAdapter);
	}
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		
		System.out.println("position : " + position);
		
		String clickName = l.getItemAtPosition(position).toString();
		System.out.println("clickName : " + clickName);
		
	}
	/*
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.systemlog_list, menu);
		return true;
	}
	 */
	private class SytemlogAdapter extends ArrayAdapter<SystemLog> {

		private ArrayList<SystemLog> items;

		public SytemlogAdapter(Context context, int textViewResourceId, ArrayList<SystemLog> items) {
			super(context, textViewResourceId, items);
			this.items = items;
		}
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View v = convertView;
			if (v == null) {
				LayoutInflater vi = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				v = vi.inflate(R.layout.activity_systemlog_detail, null);
			}
			SystemLog p = items.get(position);
			System.out.println("getFirstCretDt : " + p.getFirstCretDt() );
			if (p != null) {
				TextView systemLogSeverity = (TextView) v.findViewById(R.id.severity);
				TextView systemLogWorkflowID = (TextView) v.findViewById(R.id.workflow_id);
				TextView systemLogFirstCretDT = (TextView) v.findViewById(R.id.first_cret_dt);
				if (systemLogSeverity != null){
					systemLogSeverity.setText( ""+p.getServerity());                            
				}
				if(systemLogWorkflowID != null){
					systemLogWorkflowID.setText( p.getsWorklfowID());
				}
				if(systemLogFirstCretDT != null ){
					systemLogFirstCretDT.setText( p.getFirstCretDt());
				}
			}
			return v;
		}
	}

}

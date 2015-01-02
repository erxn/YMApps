package com.yusufmansur.android;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Menu extends ListActivity {

	String classes[] = { "MainActivity", "example1", "example2", "example3", "example4", "example5"};
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// set ArrayAdapter of type String
		setListAdapter(new ArrayAdapter<String>(Menu.this, android.R.layout.simple_list_item_1, classes));
		
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		String curItm = classes[position];
		// setup class for Intent
		try{
			Class ymClass = Class.forName("com.yusufmansur.android." + curItm);
			Intent ymIntent = new Intent(Menu.this, ymClass);
			startActivity(ymIntent);
		} catch (ClassNotFoundException e){
			e.printStackTrace();
		}
	}

}

package com.yusufmansur.android;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class PostListActivity extends ListActivity {
	private Context context;
	
	// post list API
	private static String postUrl = "http://yusufmansur.com/wp-json/post/";
	
	private static final String TAG_ID = "ID";
	private static final String TAG_CAT = "name";
	private static final String TAG_SLUG = "slug";
	private static final String TAG_COUNT = "count";

	ArrayList<HashMap<String, String>> jsonList = new ArrayList<HashMap<String, String>>();
	
	ListView lv ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		new ProgressTask(PostListActivity.this).execute();
	}

	private class ProgressTask extends AsyncTask<String, Void, Boolean> {
		private ProgressDialog dialog;

		private ListActivity activity;

		// private List<Message> messages;
		public ProgressTask(ListActivity activity) {
			this.activity = activity;
			context = activity;
			dialog = new ProgressDialog(context);
		}

		/** progress dialog to show user that the backup is processing. */

		/** application context. */
		private Context context;

		protected void onPreExecute() {
			this.dialog.setMessage("Loading YMApps...");
			this.dialog.show();
		}

		@Override
		protected void onPostExecute(final Boolean success) {
			if (dialog.isShowing()) {
				dialog.dismiss();
			}
			ListAdapter adapter = new SimpleAdapter(context, jsonList,
					R.layout.postlist_item, new String[] { TAG_ID, TAG_CAT,
							TAG_SLUG, TAG_COUNT }, new int[] {
							R.id.vehicleType, R.id.vehicleColor, R.id.fuel,
							R.id.treadType });

			setListAdapter(adapter);

			// selecting single ListView item
			 lv = getListView();
			
			

			

			
		}

		protected Boolean doInBackground(final String... args) {

			JSONParser jParser = new JSONParser();

			// getting JSON string from URL
			JSONArray json = jParser.getJSONFromUrl(postUrl);

			for (int i = 0; i < json.length(); i++) {

				try {
					JSONObject c = json.getJSONObject(i);
					String cid = c.getString(TAG_ID);

					String ccat = c.getString(TAG_CAT);
					String cslug = c.getString(TAG_SLUG);
					String ccount = c.getString(TAG_COUNT);

					HashMap<String, String> map = new HashMap<String, String>();

					// adding each child node to HashMap key => value
					map.put(TAG_ID, cid);
					map.put(TAG_CAT, ccat);
					map.put(TAG_SLUG, cslug);
					map.put(TAG_COUNT, ccount);
					jsonList.add(map);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			return null;

		}

	}

}

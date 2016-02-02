package com.example.consumingrestapisampleapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DetailItemActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail_item);

		String headline = "";
		String details = "";

		Intent intent = getIntent();
		if (null != intent) {
			headline = intent.getStringExtra("title");
			details = intent.getStringExtra("description");
		}

		TextView headlineTxt = (TextView) findViewById(R.id.title_detail);
		headlineTxt.setText(headline);

		TextView detailsTxt = (TextView) findViewById(R.id.description_detail);
		detailsTxt.setText(details);

	}

}

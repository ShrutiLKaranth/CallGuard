package com.neevtech.callguard.android.activity;

import java.util.List;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;

import com.neevtech.callguard.R;
import com.neevtech.callguard.android.db.datasource.CategoryDataSource;
import com.neevtech.callguard.android.model.Category;

public class MainActivity extends FragmentActivity {
	CategoryDataSource dataSource;
	private String LOG_TAG = MainActivity.class.getSimpleName();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		dataSource = new CategoryDataSource(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	protected void onResume() {
		super.onResume();
		dataSource.open();
		Category category = new Category();
		category.setName("Marketing.");
		dataSource.createCategory(category);

	}

	@Override
	protected void onPause() {
		dataSource.close();
		super.onPause();
	}

	public void clicked(View view) {
		List<Category> categories = dataSource.getAllCategories();
		for (Category category2 : categories) {
			Log.i(LOG_TAG,
					"cat Id " + category2.getId() + " name="
							+ category2.getName());
		}

	}

}

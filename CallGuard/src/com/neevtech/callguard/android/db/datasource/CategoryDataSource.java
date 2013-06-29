package com.neevtech.callguard.android.db.datasource;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.neevtech.callguard.android.db.utils.MySQLiteHelper;
import com.neevtech.callguard.android.model.Category;

/**
 * Contains database related task for Category.
 * 
 */
public class CategoryDataSource {

	private SQLiteDatabase database;
	private MySQLiteHelper dbHelper;
	private String[] allColumns = { MySQLiteHelper.COLUMN_ID,
			MySQLiteHelper.COLUMN_NAME };

	private String LOG_TAG = CategoryDataSource.class.getSimpleName();

	public CategoryDataSource(Context context) {
		dbHelper = new MySQLiteHelper(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	public long createCategory(Category category) {
		ContentValues values = new ContentValues();
		values.put(MySQLiteHelper.COLUMN_NAME, category.getName());

		return database.insert(MySQLiteHelper.TABLE_CATEGORIES, null, values);
	}

	public void deleteCategory(int id) {

		database.delete(MySQLiteHelper.TABLE_CATEGORIES,
				MySQLiteHelper.COLUMN_ID + " = " + id, null);
	}

	public void updateCategory(Category category) {
		ContentValues values = new ContentValues();
		values.put(MySQLiteHelper.COLUMN_ID, category.getId());
		values.put(MySQLiteHelper.COLUMN_NAME, category.getName());

		database.update(MySQLiteHelper.TABLE_CATEGORIES, values,
				MySQLiteHelper.COLUMN_ID + " = " + category.getId(), null);
	}

	public List<Category> getAllCategories() {
		List<Category> categories = new ArrayList<Category>();

		Cursor cursor = database.query(MySQLiteHelper.TABLE_CATEGORIES,
				allColumns, null, null, null, null, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Category category = cursorToCategory(cursor);
			categories.add(category);
			cursor.moveToNext();
		}
		cursor.close();
		return categories;
	}

	public Category findById(int id) {
		Cursor cursor = database.rawQuery("select * from "
				+ MySQLiteHelper.TABLE_CATEGORIES + " where "
				+ MySQLiteHelper.COLUMN_ID + "= " + id, null);
		Log.d(LOG_TAG, "Cursor Count :: " + cursor.getCount());
		cursor.moveToFirst();
		Category category = cursorToCategory(cursor);

		Log.d(LOG_TAG, category.toString());
		return category;
	}

	private Category cursorToCategory(Cursor cursor) {
		Category category = new Category();
		category.setId(cursor.getInt(0));
		category.setName(cursor.getString(1));

		return category;
	}

}

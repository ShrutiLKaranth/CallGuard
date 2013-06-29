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
import com.neevtech.callguard.android.model.Group;

/**
 * Contains database related task for blocked user.
 * 
 */
public class GroupDataSource {

	private SQLiteDatabase database;
	private MySQLiteHelper dbHelper;
	private String[] allColumns = { MySQLiteHelper.COLUMN_ID,
			MySQLiteHelper.COLUMN_NAME };

	private String LOG_TAG = GroupDataSource.class.getSimpleName();

	public GroupDataSource(Context context) {
		dbHelper = new MySQLiteHelper(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	public long createGroup(Group group) {
		ContentValues values = new ContentValues();
		values.put(MySQLiteHelper.COLUMN_NAME, group.getName());

		return database.insert(MySQLiteHelper.TABLE_GROUPS, null, values);

	}

	public void deleteGroup(int id) {
		database.delete(MySQLiteHelper.TABLE_GROUPS, MySQLiteHelper.COLUMN_ID
				+ " = " + id, null);
	}

	public void updateGroup(Group group) {
		ContentValues values = new ContentValues();
		values.put(MySQLiteHelper.COLUMN_ID, group.getId());
		values.put(MySQLiteHelper.COLUMN_NAME, group.getName());

		database.update(MySQLiteHelper.TABLE_GROUPS, values,
				MySQLiteHelper.COLUMN_ID + " = " + group.getId(), null);
	}

	public List<Group> getAllGroups() {
		List<Group> groups = new ArrayList<Group>();

		Cursor cursor = database.query(MySQLiteHelper.TABLE_GROUPS, allColumns,
				null, null, null, null, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Group group = cursorToGroup(cursor);
			groups.add(group);
			cursor.moveToNext();
		}
		cursor.close();
		return groups;
	}

	public Group findById(int id) {
		Cursor cursor = database.rawQuery("select * from "
				+ MySQLiteHelper.TABLE_GROUPS + " where "
				+ MySQLiteHelper.COLUMN_ID + "= " + id, null);
		Log.d(LOG_TAG, "Cursor Count :: " + cursor.getCount());
		cursor.moveToFirst();
		Group group = cursorToGroup(cursor);

		Log.d(LOG_TAG, group.toString());
		return group;
	}

	private Group cursorToGroup(Cursor cursor) {
		Group group = new Group();
		group.setId(cursor.getInt(0));
		group.setName(cursor.getString(1));
		return group;
	}

}

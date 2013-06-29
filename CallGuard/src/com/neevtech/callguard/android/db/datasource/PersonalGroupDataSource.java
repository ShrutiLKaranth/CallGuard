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
import com.neevtech.callguard.android.model.PersonalGroup;

/**
 * Contains database related task for blocked user.
 * 
 */
public class PersonalGroupDataSource {

	private final String LOG_TAG = PersonalGroupDataSource.class
			.getSimpleName();
	private SQLiteDatabase database;
	private MySQLiteHelper dbHelper;
	private String[] allColumns = { MySQLiteHelper.COLUMN_ID,
			MySQLiteHelper.COLUMN_NUMBER, MySQLiteHelper.COLUMN_GROUP_ID };

	public PersonalGroupDataSource(Context context) {
		dbHelper = new MySQLiteHelper(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	public long createPersonalGroup(PersonalGroup blockedNumber) {
		ContentValues values = new ContentValues();
		values.put(MySQLiteHelper.COLUMN_NUMBER, blockedNumber.getNumber());
		values.put(MySQLiteHelper.COLUMN_GROUP_ID, blockedNumber.getGroup()
				.getId());

		return database.insert(MySQLiteHelper.TABLE_PERSONAL_GROUPS, null,
				values);
	}

	public void deletePersonalGroup(int id) {
		database.delete(MySQLiteHelper.TABLE_PERSONAL_GROUPS,
				MySQLiteHelper.COLUMN_ID + " = " + id, null);
	}

	public void updatePersonalGroup(PersonalGroup personalGroup) {

		ContentValues values = new ContentValues();
		values.put(MySQLiteHelper.COLUMN_ID, personalGroup.getId());
		values.put(MySQLiteHelper.COLUMN_NUMBER, personalGroup.getNumber());
		values.put(MySQLiteHelper.COLUMN_GROUP_ID, personalGroup.getGroup()
				.getId());

		database.update(MySQLiteHelper.TABLE_PERSONAL_GROUPS, values,
				MySQLiteHelper.COLUMN_ID + " = " + personalGroup.getId(), null);
	}

	public List<PersonalGroup> getAllUsers() {
		List<PersonalGroup> personalGroups = new ArrayList<PersonalGroup>();

		Cursor cursor = database.query(MySQLiteHelper.TABLE_PERSONAL_GROUPS,
				allColumns, null, null, null, null, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			PersonalGroup personalGroup = cursorToPersonalGroup(cursor);
			personalGroups.add(personalGroup);
			cursor.moveToNext();
		}
		cursor.close();
		return personalGroups;
	}

	public PersonalGroup findById(int id) {
		Cursor cursor = database.rawQuery("select * from "
				+ MySQLiteHelper.TABLE_PERSONAL_GROUPS + " where "
				+ MySQLiteHelper.COLUMN_ID + "= " + id, null);
		Log.d(LOG_TAG, "findById Cursor Count:: " + cursor.getCount());
		cursor.moveToFirst();
		PersonalGroup personalGroup = cursorToPersonalGroup(cursor);

		Log.d(LOG_TAG, "findById" + personalGroup.toString());
		return personalGroup;
	}

	public PersonalGroup findGroupById(int groupId) {
		Cursor cursor = database.rawQuery("select * from "
				+ MySQLiteHelper.TABLE_PERSONAL_GROUPS + " where "
				+ MySQLiteHelper.COLUMN_GROUP_ID + "= " + groupId, null);
		Log.d(LOG_TAG, "findCategoryById Cursor Count :: " + cursor.getCount());
		cursor.moveToFirst();
		PersonalGroup personalGroup = cursorToPersonalGroup(cursor);

		Log.d(LOG_TAG, "findCategoryById " + personalGroup.toString());
		return personalGroup;
	}

	private PersonalGroup cursorToPersonalGroup(Cursor cursor) {
		PersonalGroup personalGroup = new PersonalGroup();
		personalGroup.setId(cursor.getInt(0));
		personalGroup.setNumber(cursor.getInt(1));
		Group group = new Group();
		group.setId(cursor.getInt(2));
		personalGroup.setGroup(group);
		return personalGroup;
	}

}

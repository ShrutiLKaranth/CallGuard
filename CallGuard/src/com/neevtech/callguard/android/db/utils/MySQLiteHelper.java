/**
 * 
 */
package com.neevtech.callguard.android.db.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * @author prashant
 * 
 */
public class MySQLiteHelper extends SQLiteOpenHelper {

	public static final String TABLE_CATEGORIES = "categories";
	public static final String TABLE_GROUPS = "groups";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_NAME = "name";

	public static final String TABLE_BLOCKED_NUMBER = "blocked_numbers";
	public static final String COLUMN_NUMBER = "number";
	public static final String COLUMN_BANNED = "banned";
	public static final String COLUMN_REPORT_COUNT = "report_count";
	public static final String COLUMN_CATEGORY_ID = "category_id";

	public static final String TABLE_PERSONAL_GROUPS = "personal_groups";
	public static final String COLUMN_GROUP_ID = "group_id";

	private static final String DATABASE_NAME = "call_guard.db";
	private static final int DATABASE_VERSION = 1;

	// Database creation sql statement
	// Open Database by saving to s3db then use software:
	// http://sqliteadmin.orbmu2k.de/
	private static final String TABLE_CATEGORIES_CREATE = "create table "
			+ TABLE_CATEGORIES + "(" + COLUMN_ID
			+ " integer primary key autoincrement, " + COLUMN_NAME
			+ " text not null);";

	private static final String TABLE_GROUPS_CREATE = "create table "
			+ TABLE_GROUPS + "(" + COLUMN_ID
			+ " integer primary key autoincrement, " + COLUMN_NAME
			+ " text not null);";

	private static final String TABLE_BLOCKED_NUMBER_CREATE = "create table "
			+ TABLE_BLOCKED_NUMBER + "(" + COLUMN_ID
			+ " integer primary key autoincrement, " + COLUMN_NUMBER
			+ " text not null," + COLUMN_CATEGORY_ID + " int not null,"
			+ COLUMN_REPORT_COUNT + " int not null, " + COLUMN_BANNED
			+ " bool not null);";

	private static final String TABLE_PERSONAL_GROUPS_CREATE = "create table "
			+ TABLE_PERSONAL_GROUPS + "(" + COLUMN_ID
			+ " integer primary key autoincrement, " + COLUMN_NUMBER
			+ " int(10) not null," + COLUMN_GROUP_ID + " int not null);";

	public MySQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL(TABLE_CATEGORIES_CREATE);
		database.execSQL(TABLE_GROUPS_CREATE);
		database.execSQL(TABLE_BLOCKED_NUMBER_CREATE);
		database.execSQL(TABLE_PERSONAL_GROUPS_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(MySQLiteHelper.class.getName(),
				"Upgrading database from version " + oldVersion + " to "
						+ newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORIES);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_GROUPS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_BLOCKED_NUMBER);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_PERSONAL_GROUPS);
		onCreate(db);
	}
}
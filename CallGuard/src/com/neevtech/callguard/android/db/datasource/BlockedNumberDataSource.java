/**
 * 
 */
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
import com.neevtech.callguard.android.model.BlockedNumber;

/**
 * Contains database related task for blocked user.
 * 
 */
public class BlockedNumberDataSource {

	private SQLiteDatabase database;
	private MySQLiteHelper dbHelper;
	private String[] allColumns = { MySQLiteHelper.COLUMN_ID,
			MySQLiteHelper.COLUMN_NUMBER, MySQLiteHelper.COLUMN_REPORT_COUNT,
			MySQLiteHelper.COLUMN_BANNED, MySQLiteHelper.COLUMN_CATEGORY_ID };

	public BlockedNumberDataSource(Context context) {
		dbHelper = new MySQLiteHelper(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	public long createBlockedNumber(BlockedNumber blockedNumber) {

		ContentValues values = new ContentValues();
		values.put(MySQLiteHelper.COLUMN_NUMBER, blockedNumber.getNumber());
		values.put(MySQLiteHelper.COLUMN_REPORT_COUNT,
				blockedNumber.getReportCount());
		values.put(MySQLiteHelper.COLUMN_BANNED, blockedNumber.isBanned());
		values.put(MySQLiteHelper.COLUMN_CATEGORY_ID, blockedNumber
				.getCategory().getId());

		return database.insert(MySQLiteHelper.TABLE_BLOCKED_NUMBER, null,
				values);

	}

	public void deleteBlockedNumber(int id) {

		System.out.println("Comment deleted with id: " + id);
		database.delete(MySQLiteHelper.TABLE_BLOCKED_NUMBER,
				MySQLiteHelper.COLUMN_ID + " = " + id, null);
	}

	public void updateBlockedNumber(BlockedNumber blockedNumber) {

		ContentValues values = new ContentValues();
		values.put(MySQLiteHelper.COLUMN_ID, blockedNumber.getId());
		values.put(MySQLiteHelper.COLUMN_NUMBER, blockedNumber.getNumber());
		values.put(MySQLiteHelper.COLUMN_REPORT_COUNT,
				blockedNumber.getReportCount());
		values.put(MySQLiteHelper.COLUMN_BANNED, blockedNumber.isBanned());
		values.put(MySQLiteHelper.COLUMN_CATEGORY_ID, blockedNumber
				.getCategory().getId());

		database.update(MySQLiteHelper.TABLE_BLOCKED_NUMBER, values,
				MySQLiteHelper.COLUMN_ID + " = " + blockedNumber.getId(), null);
	}

	public List<BlockedNumber> getAllBlockedNumbers() {
		List<BlockedNumber> blockedNumbers = new ArrayList<BlockedNumber>();

		Cursor cursor = database.query(MySQLiteHelper.TABLE_BLOCKED_NUMBER,
				allColumns, null, null, null, null, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			BlockedNumber blockedNumber = cursorToBlockedNumber(cursor);
			blockedNumbers.add(blockedNumber);
			cursor.moveToNext();
		}
		cursor.close();
		return blockedNumbers;
	}

	public BlockedNumber findById(int id) {
		Cursor cursor = database.rawQuery("select * from "
				+ MySQLiteHelper.TABLE_BLOCKED_NUMBER + " where "
				+ MySQLiteHelper.COLUMN_ID + "= " + id, null);
		Log.i("Cursor Count",
				"The current Cursor Count is " + cursor.getCount());
		cursor.moveToFirst();
		BlockedNumber blockedNumber = cursorToBlockedNumber(cursor);

		Log.i("ContactDataSource", blockedNumber.toString());
		return blockedNumber;
	}

	public BlockedNumber findCategoryById(int categoryId) {
		Cursor cursor = database.rawQuery("select * from "
				+ MySQLiteHelper.TABLE_BLOCKED_NUMBER + " where "
				+ MySQLiteHelper.COLUMN_CATEGORY_ID + "= " + categoryId, null);
		Log.i("Cursor Count",
				"The current Cursor Count is " + cursor.getCount());
		cursor.moveToFirst();
		BlockedNumber blockedNumber = cursorToBlockedNumber(cursor);

		Log.i("ContactDataSource", blockedNumber.toString());
		return blockedNumber;
	}

	private BlockedNumber cursorToBlockedNumber(Cursor cursor) {
		BlockedNumber blockedNumber = new BlockedNumber();
		blockedNumber.setId(cursor.getInt(0));
		blockedNumber.setNumber(cursor.getInt(1));
		blockedNumber.setReportCount(cursor.getInt(2));
		blockedNumber.setBanned(cursor.getShort(3));

		// blockedNumber.setCategory((cursor.getInt(0));

		return blockedNumber;
	}

}

package y.espace;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBAdapter {
	// Keys and values for columns
	public static final String UID = "uid";
	public static final String PARTYCODE = "partycode";
	public static final String NAME = "name";
	public static final String ADDRESS = "address";
	public static final String MOBILE = "mobile";
	public static final String EMAIL = "email";
	public static final String AREA = "area";
	public static final String BALANCE = "balance";

	// Tag for LogCat
	private static final String TAG = "DBAdapter";
	
	private static final String DATABASE_NAME = "MyDB";
	private static final String DATABASE_TABLE = "partymaster";
	private static final int DATABASE_VERSION = 1;
	
	// Query to create database table
	private static final String DATABASE_CREATE = "create table partymaster (uid text, "
			+ "partycode text not null,name text not null, address text not null,mobile text not null,email text not null,area text not null,balance integer not null);";
	private final Context context;
	private DatabaseHelper DBHelper;
	private SQLiteDatabase db;

	public DBAdapter(Context ctx) {
		this.context = ctx;
		DBHelper = new DatabaseHelper(context);
	}

	private static class DatabaseHelper extends SQLiteOpenHelper {
		
		DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			try {
				// Creates the database
				db.execSQL(DATABASE_CREATE);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
					+ newVersion + ", which will destroy all old data");
			db.execSQL("DROP TABLE IF EXISTS contacts");
			onCreate(db);
		}
	}

	// ---opens the database---
	public DBAdapter open() throws SQLException {
		db = DBHelper.getWritableDatabase();
		return this;
	}

	// ---closes the database---
	public void close() {
		DBHelper.close();
	}

	// ---insert a contact into the database---
	public long insertContact(String uid, String partycode, String name,
			String address, String mobile, String email, String area,
			String balance) {
		ContentValues initialValues = new ContentValues();
		initialValues.put(UID, uid);
		initialValues.put(PARTYCODE, partycode);
		initialValues.put(NAME, name);
		initialValues.put(ADDRESS, address);
		initialValues.put(MOBILE, mobile);
		initialValues.put(EMAIL, email);
		initialValues.put(AREA, area);
		initialValues.put(BALANCE, balance);
		return db.insert(DATABASE_TABLE, null, initialValues);
	}

	// ---deletes a particular contact---
	public boolean deleteContact(String arg) {

		db = DBHelper.getWritableDatabase();
		db.delete(DATABASE_TABLE, UID + " = ?",
				new String[] { String.valueOf(arg.toString()) });
		db.close();
		return false;

	}
	
	// ---deletes a all contact---
		public boolean deleteAllContact() {

			db = DBHelper.getWritableDatabase();
			//db.delete(table, whereClause, whereArgs)
			db.delete(DATABASE_TABLE, null,	null);
			db.close();
			return false;

		}

	// ---retrieves all the contacts---
	public Cursor getall() {
		return db.query(DATABASE_TABLE, new String[] { UID, PARTYCODE, NAME,
				ADDRESS, MOBILE, EMAIL, AREA, BALANCE }, null, null, null,
				null, null);

	}

	// ---retrieves a particular contact---
	public Cursor getContact(String rowId) throws SQLException {
		db = DBHelper.getWritableDatabase();

		Cursor cursor = db.query(DATABASE_TABLE, new String[] { UID, PARTYCODE,
				NAME, ADDRESS, MOBILE, EMAIL, AREA, BALANCE }, UID + "=?",
				new String[] { String.valueOf(rowId) }, null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();

		return cursor;
	}

	public int UpdateContact(String uid, String partycode, String name,
			String address, String mobile, String email, String area,
			String balance) {
		db = DBHelper.getWritableDatabase();

		ContentValues initialValues = new ContentValues();
		initialValues.put(UID, uid);
		initialValues.put(PARTYCODE, partycode);
		initialValues.put(NAME, name);
		initialValues.put(ADDRESS, address);
		initialValues.put(MOBILE, mobile);
		initialValues.put(EMAIL, email);
		initialValues.put(AREA, area);
		initialValues.put(BALANCE, balance);
		// updating row
		return db.update(DATABASE_TABLE, initialValues, UID + " = ?",
				new String[] { String.valueOf(uid) });
	}

}

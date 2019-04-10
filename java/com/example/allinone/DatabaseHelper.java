package com.example.allinone;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

class DatabaseHelper extends SQLiteOpenHelper {


    public static String DATABASE = "database.db";
    public static String TABLE ="mytable";
    public static String IMAGE_ID ="imageid";
    public static String LOCATION_NAME ="name";
    public static String LOCATION_TYPE ="type";
    public static String ALL_ADDRESS ="address";
    String br;

    public DatabaseHelper(Context context) {
        super(context, DATABASE, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //  br= "CREATE TABLE mytable(name TEXT,company TEXT,city TEXT,country TEXT);";
        br = "CREATE TABLE "+TABLE+"("+IMAGE_ID+ " INTEGER , "+LOCATION_NAME+ " Text PRIMARY KEY, "+LOCATION_TYPE+ " Text, "+ALL_ADDRESS+ " Text);";
        db.execSQL(br);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE+" ;");
    }

    public void insertdata(int imageID,String location_name ,String location_type,String all_address){
        System.out.print("Hello "+br);
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(IMAGE_ID, imageID);
        contentValues.put(LOCATION_NAME, location_name);
        contentValues.put(LOCATION_TYPE,location_type);
        contentValues.put(ALL_ADDRESS,all_address);
        db.insert(TABLE,null,contentValues);
    }

    public List<DataPojo> getdata(){
        // DataModel dataModel = new DataModel();
        List<DataPojo> data=new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from "+TABLE+ " ;",null);

        DataPojo dataModel = null;
        while (cursor.moveToNext()) {
            int all_image = cursor.getInt(cursor.getColumnIndexOrThrow("imageid"));
            String location_name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
            String location_type = cursor.getString(cursor.getColumnIndexOrThrow("type"));
            String all_address  = cursor.getString(cursor.getColumnIndexOrThrow("address"));

            dataModel= new DataPojo(all_image, location_name, location_type ,all_address );

         //   stringBuffer.append(dataModel);
            // stringBuffer.append(dataModel);
            data.add(dataModel);
        }
        for (DataPojo mo:data ) {

            Log.i("checking all data ",""+mo.getLocation_name());
        }
        return data;
    }

    public List<DataPojo> getdataByChoice(String choice){
        // DataModel dataModel = new DataModel();
        List<DataPojo> data=new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select imageid,name,type,address from "+TABLE+ " where type=?",new String[]{choice});

        DataPojo dataModel = null;
        while (cursor.moveToNext()) {
            int all_image = cursor.getInt(cursor.getColumnIndexOrThrow("imageid"));
            String location_name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
            String location_type = cursor.getString(cursor.getColumnIndexOrThrow("type"));
            String all_address  = cursor.getString(cursor.getColumnIndexOrThrow("address"));
            dataModel= new DataPojo(all_image, location_name, location_type ,all_address );

            // stringBuffer.append(dataModel);
            // stringBuffer.append(dataModel);
            data.add(dataModel);
        }
        for (DataPojo mo:data ) {

            Log.i("checking all data ",""+mo.getLocation_name());
        }
        return data;
    }




}

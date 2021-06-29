package com.example.newawareness.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.newawareness.Objects.ObjectSituation;

import java.util.ArrayList;
import java.util.List;

public class DatabaseClass extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Database";
    public static final String TABLE_NAME = "particulars_table";
    private static final int DATABASE_VERSION = 2;

    public static final String COL_1 = "Headphone_State";
    public static final String COL_2 = "Wheather_State";
    public static final String COL_3 = "Physical_State";
    public static final String COL_4 = "Time_State";
    public static final String COL_5 = "Date_State";
    public static final String COL_6 = "App_name";
    public static final String COL_7 = "Notififcation";
    public static final String COL_8 = "Location_name";
    public static final String COL_9 = "Action_type";
    public static final String COL_10 = "SituationName";
    public static final String COL_11 = "SetTime";
    public static final String COL_12 = "checkSwitch";


    public DatabaseClass(Context context) {
        super(context, DATABASE_NAME, null, 2);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String Table = "CREATE TABLE " + TABLE_NAME +
                " (id integer primary key," + COL_1 + " VARCHAR," +
                COL_2 + " VARCHAR," + COL_3 + " VARCHAR," +
                COL_4 + " VARCHAR," + COL_5 + " VARCHAR," + COL_6
                + " VARCHAR," + COL_7 + " VARCHAR," + COL_8 + " VARCHAR," + COL_9 +" VARCHAR," + COL_10+ " VARCHAR," +COL_11 +" VARCHAR,"+ COL_12 + " VARCHAR);";
        db.execSQL(Table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public int latestPrimarykey() {
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        cursor.moveToLast();
        int latestKey = cursor.getInt(0);
        return latestKey + 1;
    }
    public ObjectSituation checkKey_GetData(String key){

        SQLiteDatabase db = this.getWritableDatabase();
        ObjectSituation object_situation=new ObjectSituation();
        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery("SELECT * FROM particulars_table WHERE id = '"+key+"'", null);
            cursor.moveToFirst();
            object_situation.setHeadphone(cursor.getInt(1));
            object_situation.setWeather(cursor.getInt(2));
            object_situation.setActivity(cursor.getInt(3));
            object_situation.setTime(cursor.getLong(4));
            object_situation.setDate(cursor.getString(5));
            object_situation.setAppname(cursor.getString(6));
            object_situation.setNotification(cursor.getString(7));
            object_situation.setLocationname(cursor.getString(8));
            object_situation.setAction(cursor.getInt(9));
            object_situation.setSituationname(cursor.getString(10));
            object_situation.setTime(cursor.getLong(11));
            boolean value = cursor.getInt(12) > 0;
            object_situation.setSwitchActive(value);

        }
        return object_situation;

    }

    public boolean insertData(ObjectSituation object_situation) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, object_situation.getHeadphone());
        contentValues.put(COL_2, object_situation.getWeather());
        contentValues.put(COL_3, object_situation.getActivity());
        contentValues.put(COL_4, object_situation.getTime());
        contentValues.put(COL_5, object_situation.getDate());
        contentValues.put(COL_6, object_situation.getAppname());
        contentValues.put(COL_7, object_situation.getNotification());
        contentValues.put(COL_8, object_situation.getLocationname());
        contentValues.put(COL_9, object_situation.getAction());
        contentValues.put(COL_10, object_situation.getSituationname());
        contentValues.put(COL_11, object_situation.getTime());
        contentValues.put(COL_12, object_situation.getSwitchActive());

        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public List<ObjectSituation> readAllData() {
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = null;
        List<ObjectSituation> list = new ArrayList<>();
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        while ((cursor.moveToNext())) {
            ObjectSituation object_situation = new ObjectSituation();
            object_situation.setHeadphone(cursor.getInt(1));
            object_situation.setWeather(cursor.getInt(2));
            object_situation.setActivity(cursor.getInt(3));
            object_situation.setTime(cursor.getLong(4));
            object_situation.setDate(cursor.getString(5));
            object_situation.setAppname(cursor.getString(6));
            object_situation.setNotification(cursor.getString(7));
            object_situation.setLocationname(cursor.getString(8));
            object_situation.setAction(cursor.getInt(9));
            object_situation.setSituationname(cursor.getString(10));
            object_situation.setTime(cursor.getLong(11));
            boolean value = cursor.getInt(12) > 0;
            object_situation.setSwitchActive(value);
            list.add(object_situation);
        }
        return list;

    }

    public void update(int id,boolean b){


        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_12, b);
        db.update(TABLE_NAME, contentValues, "_id=" +id, null);
    }

}


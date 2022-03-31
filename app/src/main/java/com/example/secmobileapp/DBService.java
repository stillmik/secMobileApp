package com.example.secmobileapp;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DBService extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "weatherLibrary.db";
    private static final String DATABASE2_NAME = "weatherResults.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "my_weather";
    private static final String TABLE_NAME2 = "my_weather_results";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_CITY = "city";
    private static final String COLUMN_DATE = "date";
    private static final String COLUMN_TEMPERATURE = "temperature";
    private static final String COLUMN_HUMIDITY = "humidity";
    private static final String COLUMN_WIND_DIRECTION = "wind_direction";

    public DBService(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query2 = "CREATE TABLE " + TABLE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_CITY + " TEXT, " +
                COLUMN_DATE + " TEXT, " +
                COLUMN_TEMPERATURE + " INTEGER, " +
                COLUMN_HUMIDITY + " TEXT, " +
                COLUMN_WIND_DIRECTION + " TEXT);";
        db.execSQL(query2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void clearDataBase() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from " + TABLE_NAME);
    }

    void addResult(String city, String date, String temperature, String humidity, String windDirection) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_CITY, city);
        contentValues.put(COLUMN_DATE, date);
        contentValues.put(COLUMN_TEMPERATURE, temperature);
        contentValues.put(COLUMN_HUMIDITY, humidity);
        contentValues.put(COLUMN_WIND_DIRECTION, windDirection);
        long res;
        res = db.insert(TABLE_NAME, null, contentValues);
        checkError(res);
    }

    @SuppressLint("Recycle")
    public Cursor readAllData() {
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;

        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    @SuppressLint("Recycle")
    public Cursor readDataForCity(String city) {
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_CITY + " = '" + city + "';";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;

        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    private void checkError(long res) {
        if (res == -1) {
            Toast.makeText(context, "failed to add", Toast.LENGTH_SHORT).show();
        }
    }
}

package com.example.csitgis.lab6;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "student_db";

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(Student.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,int i,int il){
        db.execSQL("DROP TABLE IF EXISTS " + Student.TABLE_NAME);

        onCreate(db);
    }
    public void insertStudent(String id,String student){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Student.COLUMN_ID,id);
        values.put(Student.COLUMN_NAME,student);

        db.insert(Student.TABLE_NAME,null,values);
        db.close();
    }
    public Student getStudent(String id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Student.TABLE_NAME,
                new String[]{Student.COLUMN_ID,Student.COLUMN_NAME},
                Student.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)},null,null,null,null);

    }


}

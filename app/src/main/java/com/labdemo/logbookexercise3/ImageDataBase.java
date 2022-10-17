package com.labdemo.logbookexercise3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class ImageDataBase extends SQLiteOpenHelper {

    private final Context context;
    private static final  int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "Image_URL.db";

    private static final String TABLE_IMAGE = "ImageURL";
    private static final String ID = "id";
    private static final String URL = "url";

    private static final String QUERY_CREATE_TABLE = "CREATE TABLE " + TABLE_IMAGE + " ("
            + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + URL + " TEXT);";

    public ImageDataBase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(QUERY_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_IMAGE + "'");
        onCreate(db);
    }

    public void addNewImageUrl(String imageUrl) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(URL, imageUrl);

        long result = db.insert(TABLE_IMAGE, null, values);
        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Done", Toast.LENGTH_SHORT).show();
        }
        db.close();
    }

    public Cursor displayAllImage() {
        String query = "SELECT * FROM " + TABLE_IMAGE;
        SQLiteDatabase result = this.getReadableDatabase();

        Cursor cursor = null;
        if (result != null){
            cursor = result.rawQuery(query, null);
        }
        return cursor;
    }
}

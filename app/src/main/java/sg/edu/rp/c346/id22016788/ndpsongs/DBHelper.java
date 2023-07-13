package sg.edu.rp.c346.id22016788.ndpsongs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "simpleNDPSongs.db";
    private static final int DATABASE_VERSION = 2;
    private static final String TABLE_SONG = "NDPSongs";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_SONG_TITLE = "title";
    private static final String COLUMN_SONG_SINGER = "singer";
    private static final String COLUMN_SONG_YEAR = "year";
    private static final String COLUMN_SONG_RATING = "rating";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createNoteTableSql = "CREATE TABLE " + TABLE_SONG + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_SONG_TITLE + " TEXT , "
                + COLUMN_SONG_SINGER + " TEXT , "
                + COLUMN_SONG_YEAR + " TEXT , "
                + COLUMN_SONG_RATING + " TEXT ) ";
        db.execSQL(createNoteTableSql);
        Log.i("info", "created tables");

        //Dummy records, to be inserted when the database is created
        for (int i = 0; i < 4; i++) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_SONG_TITLE, "Data number " + i);
            values.put(COLUMN_SONG_SINGER, "Data number " + i);
            values.put(COLUMN_SONG_YEAR, "Data number " + i);
            values.put(COLUMN_SONG_RATING, "Data number " + i);

            db.insert(TABLE_SONG, null, values);
        }
        Log.i("info", "dummy records inserted");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTE);
        db.execSQL("ALTER TABLE " + TABLE_SONG + " ADD COLUMN  module_name TEXT ");
        //onCreate(db);
    }

    public ArrayList<NDPSongs> getAllNDPSongs() {
        ArrayList<NDPSongs> NDPSongs = new ArrayList<NDPSongs>();

        SQLiteDatabase db = this.getReadableDatabase();

        String[] columns = {COLUMN_ID, COLUMN_SONG_TITLE, COLUMN_SONG_SINGER, COLUMN_SONG_YEAR, COLUMN_SONG_RATING};

        Cursor cursor = db.query(TABLE_SONG, columns, null, null,
                null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String singer = cursor.getString(2);
                String year = cursor.getString(3);
                String rating = cursor.getString(4);
                NDPSongs song = new NDPSongs(id, title, singer, year, rating);
                NDPSongs.add(song);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return NDPSongs;
    }

    public long insertNDPSongs(String title, String singer, String year, String rating) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_SONG_TITLE, title);
        values.put(COLUMN_SONG_SINGER, singer);
        values.put(COLUMN_SONG_YEAR, year);
        values.put(COLUMN_SONG_RATING, rating);
        long result = db.insert(TABLE_SONG, null, values);
        db.close();
        Log.d("SQL Insert", "ID:" + result); //id returned, shouldn’t be -1
        return result;
    }


    public int updateNDPSongs(NDPSongs data) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_SONG_TITLE, data.getNDPSongsTitle());
        values.put(COLUMN_SONG_SINGER, data.getNDPSongsSinger());
        values.put(COLUMN_SONG_YEAR, data.getNDPSongsYear());
        values.put(COLUMN_SONG_RATING, data.getNDPSongsRating());

        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(data.getId())};
        int result = db.update(TABLE_SONG, values, condition, args);
        db.close();
        return result;
    }

    public int deleteNDPSongs(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(id)};
        int result = db.delete(TABLE_SONG, condition, args);
        db.close();
        return result;
    }

    public ArrayList<NDPSongs> getAllNDPSongs(String keyword) {
        ArrayList<NDPSongs> NDPSongs = new ArrayList<NDPSongs>();

        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID, COLUMN_SONG_TITLE, COLUMN_SONG_SINGER, COLUMN_SONG_YEAR, COLUMN_SONG_RATING};
        String condition = COLUMN_SONG_TITLE + COLUMN_SONG_SINGER + COLUMN_SONG_YEAR + COLUMN_SONG_RATING + " Like ?";
        String[] args = {"%" + keyword + "%"};
        Cursor cursor = db.query(TABLE_SONG, columns, condition, args,
                null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String singer = cursor.getString(2);
                String year = cursor.getString(3);
                String rating = cursor.getString(4);
                NDPSongs song = new NDPSongs(id, title, singer, year, rating);
                NDPSongs.add(song);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return NDPSongs;
    }
}


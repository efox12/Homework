/**
 * This class contains information and methods for the notes database.
 * CPSC 312-01, Fall 2017
 * Programming Assignment #7
 * Icon Sources:
 * Personal icon made by Freepik from www.flaticon.com
 * Work icon made by Freepik from www.flaticon.com
 * Other icon made by Smashicons from www.flaticon.com
 * School icon made by Freepik from www.flaticon.com
 *
 * @author Erik Fox
 * @version v1.0 11/14/17
 */

package com.example.erikfox.notetaker;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;


public class NoteDatabaseHelper extends SQLiteOpenHelper {
    // database fields
    static final String DATABASE_NAME = "databaseNotes";
    static final int DATABASE_VERSION = 1;
    // table fields
    static final String TABLE_NOTES = "tableNotes";
    // column fields
    static final String ID = "_id"; // must be _id to work with adapters
    static final String TITLE = "title";
    static final String CONTENT = "content";
    static final String TYPE = "type";
    // add a tag for logcat
    static final String TAG = "noteDatabaseHelper";

    // construrtor
    public NoteDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        // create the table
        String sqlCreate = "CREATE TABLE " + TABLE_NOTES + "(" +
                ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TITLE + " TEXT, " +
                CONTENT + " TEXT, " +
                TYPE + " TEXT)";
        Log.d(TAG, "onCreate: " + sqlCreate);
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    /**
     * Inserts data from a note into the table
     * @param note the note
     */
    public void insertNote(Note note) {
        String sqlInsertNote = "INSERT INTO " + TABLE_NOTES +
                " VALUES(null, " +
                "'" + note.getTitle() + "', " +
                "'" + note.getContent() + "', " +
                "'" + note.getType() + "')";
        Log.d(TAG, "insertNote: " + sqlInsertNote);
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(sqlInsertNote);
    }

    /**
     * gets a cursor of all the notes in the database
     * @return the cursor
     */
    public Cursor getSelectAllNotesCursor() {
        String sqlSelectAll = "SELECT * FROM " + TABLE_NOTES;
        Log.d(TAG, "getSelectAllContactsCursor: " + sqlSelectAll);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sqlSelectAll, null);
        return cursor;
    }

    /**
     * gets a list of all notes in the database
     * @return the list
     */
    public List<Note> getSelectAllNotesList() {
        List<Note> notes = new ArrayList<>();
        Cursor cursor = getSelectAllNotesCursor();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String title = cursor.getString(1);
            String content = cursor.getString(2);
            String type = cursor.getString(3);
            Note note = new Note(title, content, type);
            notes.add(note);
        }

        return notes;
    }

    /**
     * updates a note with a new notes values
     * @param id the notes id
     * @param newNote the new note
     */
    public void updateNoteById(int id, Note newNote) {
        String sqlUpdate = "UPDATE " + TABLE_NOTES +
                " SET " + TITLE + " = '" + newNote.getTitle() +
                "', " + CONTENT + " = '" + newNote.getContent() +
                "', " + TYPE + " = '" + newNote.getType() +
                "' WHERE " + ID + " = " + id;
        Log.d(TAG, "updateContactById: " + sqlUpdate);
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(sqlUpdate);
        db.close();

    }

    /**
     * deletes all notes from the database
     */
    public void deleteAllNotes() {
        String sqlDeleteAll = "DELETE FROM " + TABLE_NOTES;
        Log.d(TAG, "Delete all contacts: " + sqlDeleteAll);
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(sqlDeleteAll);
        db.close();
    }

    /**
     * deletes a selected note from the database
     * @param id the notes id
     */
    public void deleteNoteByID(int id) {
        String sqlDeleteNote = "DELETE FROM " + TABLE_NOTES + " WHERE " + ID + " = " + id;
        Log.d(TAG, "Delete note: " + sqlDeleteNote);
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(sqlDeleteNote);
        db.close();
    }
}

package com.example.backbenchers_mad4124_fp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.backbenchers_mad4124_fp.models.Notes;
import com.example.backbenchers_mad4124_fp.models.Subject;

import java.util.ArrayList;

public class NotesDB extends SQLiteOpenHelper {

    private static final String TBL_SUBJECT = "tblSubject";
    private static final String NOTE_SUBJECT_ID = "SID";
    private static final String SUBJECT_ID = NOTE_SUBJECT_ID;
    private static final String SUBJECT_NAME = "SUBJECT_NAME";

    private static final String TBL_NOTES = "tblNotes";
    private static final String NOTE_ID = "NID";
    private static final String NOTE_TITLE = "NOTE_TITLE";
    private static final String NOTE_DATA = "NOTE_DATA";


    public NotesDB(@Nullable Context context) {
        super(context, "notes.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + TBL_SUBJECT + " (" + SUBJECT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + SUBJECT_NAME + " TEXT)";
        db.execSQL(createTableStatement) ;

        createTableStatement = "CREATE TABLE " + TBL_NOTES + " (" + NOTE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NOTE_SUBJECT_ID +" INTEGER REFERENCES "+ TBL_SUBJECT +"("+ SUBJECT_ID +"), "+ NOTE_TITLE + " TEXT, " + NOTE_DATA + " TEXT)";
        db.execSQL(createTableStatement) ;
        Log.d("query", createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private SQLiteDatabase readableDB(){
        return this.getReadableDatabase();
    }


    public boolean addSubject(Subject newSubject){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues subject = new ContentValues();

        subject.put(SUBJECT_NAME, newSubject.getSubjectName());

        long insert = db.insert(TBL_SUBJECT, null, subject);
        if (insert < 0)
        {
            return false;
        }
        else {
            return true;
        }
    }

    public ArrayList<Subject> getAllSubjects(){
        ArrayList<Subject> allSubjects = new ArrayList<>();

        String query = "SELECT " + SUBJECT_ID + ", " + SUBJECT_NAME + " FROM "+ TBL_SUBJECT;

        SQLiteDatabase db = readableDB();

        Cursor cursor = db.rawQuery(query,null);
        if (cursor.moveToFirst()) {
            do{
                Integer subjectId = cursor.getInt(0);
                String subjectName = cursor.getString(1);
                Subject temp = new Subject(subjectId,subjectName);
                allSubjects.add(temp);
            }while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return allSubjects;
    }


    public ArrayList<Notes> getNoteBySubjectId(Integer sid){
        ArrayList<Notes> allNotes = new ArrayList<>();

        String query = "SELECT * FROM "+TBL_NOTES+" WHERE "+NOTE_SUBJECT_ID+"="+sid;

        SQLiteDatabase db = readableDB();

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()){
            do {
                Integer noteId = cursor.getInt(0);
                Integer noteSubjectId = cursor.getInt(1);
                String noteTitle = cursor.getString(2);
                String noteData = cursor.getString(3);
                Notes temp = new Notes(noteId, noteSubjectId, noteTitle, noteData);
                allNotes.add(temp);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return  allNotes;
    }

    public Notes getNoteByNoteId(Integer nid){
        Notes note;

        String query = "SELECT * FROM "+TBL_NOTES+" WHERE "+NOTE_SUBJECT_ID+"="+nid;

        SQLiteDatabase db = readableDB();

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()){
                Integer noteId = cursor.getInt(0);
                Integer noteSubjectId = cursor.getInt(1);
                String noteTitle = cursor.getString(2);
                String noteData = cursor.getString(3);
                Notes temp = new Notes(noteId, noteSubjectId, noteTitle, noteData);

                cursor.close();
                db.close();
                return temp;
        }
        cursor.close();
        db.close();
        return null;
    }


    public boolean addNote(Notes newNote){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues note = new ContentValues();

        note.put(NOTE_ID, newNote.getNOTE_ID());
        note.put(NOTE_SUBJECT_ID, newNote.getNOTE_SUBJECT_ID());
        note.put(NOTE_TITLE, newNote.getNOTE_TITLE());
        note.put(NOTE_DATA, newNote.getNOTE_DATA());

        long insert = db.insert(TBL_NOTES, null, note);
        if (insert < 0)
        {
            return false;
        }
        else {
            return true;
        }
    }

}

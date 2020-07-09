package com.example.backbenchers_mad4124_fp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.backbenchers_mad4124_fp.models.Subject;

import java.util.ArrayList;

public class NotesDB extends SQLiteOpenHelper {

    public static final String tbl_subject = "tblSubject";
    public static final String subject_id = "SID";
    public static final String subject_name = "SUBJECT_NAME";

    public NotesDB(@Nullable Context context) {
        super(context, "notes.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + tbl_subject + " (" + subject_id + " INTEGER PRIMARY KEY AUTOINCREMENT, " + subject_name + " TEXT)";
        db.execSQL(createTableStatement) ;
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

        subject.put(subject_name, newSubject.getSubjectName());

        long insert = db.insert(tbl_subject, null, subject);
        if (insert == 1)
        {
            return false;
        }
        else {
            return true;
        }
    }

    public ArrayList<Subject> getAllSubjects(){
        ArrayList<Subject> allSubjects = new ArrayList<>();

        String query = "SELECT " +subject_id+ ", " +subject_name + " FROM "+tbl_subject;

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
}

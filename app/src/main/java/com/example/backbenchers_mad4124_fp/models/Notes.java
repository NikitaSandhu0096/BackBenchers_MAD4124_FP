package com.example.backbenchers_mad4124_fp.models;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Notes {

    private Integer noteId;
    private Integer noteSubjectId;
    private String noteTitle;
    private String noteData;
    private Timestamp timestamp;

    public Notes(Integer noteId, Integer noteSubjectId, String noteTitle, String noteData, Timestamp timestamp) {
        this.noteId = noteId;
        this.noteSubjectId = noteSubjectId;
        this.noteTitle = noteTitle;
        this.noteData = noteData;
        this.timestamp = timestamp;
    }

    public Notes(Integer noteSubjectId, String noteTitle, String noteData) {
        this.noteSubjectId = noteSubjectId;
        this.noteTitle = noteTitle;
        this.noteData = noteData;
    }

    public Integer getNoteId() {
        return noteId;
    }

    public Integer getNoteSubjectId() {
        return noteSubjectId;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public String getNoteData() {
        return noteData;
    }

    public String getTimestamp() {
        SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
        return format.format(timestamp);
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public void setNoteData(String noteData) {
        this.noteData = noteData;
    }
}

package com.example.backbenchers_mad4124_fp.models;

public class Notes {

    private Integer NOTE_ID;
    private Integer NOTE_SUBJECT_ID;
    private String NOTE_TITLE;
    private String NOTE_DATA;

    public Notes(Integer NOTE_ID, Integer NOTE_SUBJECT_ID, String NOTE_TITLE, String NOTE_DATA) {
        this.NOTE_ID = NOTE_ID;
        this.NOTE_SUBJECT_ID = NOTE_SUBJECT_ID;
        this.NOTE_TITLE = NOTE_TITLE;
        this.NOTE_DATA = NOTE_DATA;
    }

    public Notes(Integer NOTE_SUBJECT_ID, String NOTE_TITLE, String NOTE_DATA) {
        this.NOTE_SUBJECT_ID = NOTE_SUBJECT_ID;
        this.NOTE_TITLE = NOTE_TITLE;
        this.NOTE_DATA = NOTE_DATA;
    }

    public Integer getNOTE_ID() {
        return NOTE_ID;
    }

    public void setNOTE_ID(Integer NOTE_ID) {
        this.NOTE_ID = NOTE_ID;
    }

    public Integer getNOTE_SUBJECT_ID() {
        return NOTE_SUBJECT_ID;
    }

    public void setNOTE_SUBJECT_ID(Integer NOTE_SUBJECT_ID) {
        this.NOTE_SUBJECT_ID = NOTE_SUBJECT_ID;
    }

    public String getNOTE_TITLE() {
        return NOTE_TITLE;
    }

    public void setNOTE_TITLE(String NOTE_TITLE) {
        this.NOTE_TITLE = NOTE_TITLE;
    }

    public String getNOTE_DATA() {
        return NOTE_DATA;
    }

    public void setNOTE_DATA(String NOTE_DATA) {
        this.NOTE_DATA = NOTE_DATA;
    }
}

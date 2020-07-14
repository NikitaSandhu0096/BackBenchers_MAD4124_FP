package com.example.backbenchers_mad4124_fp.models;

public class NoteImage {

    private Integer noteImageId;
    private Integer noteId;
    private String  imagePath;

    public NoteImage(Integer noteImageId, Integer noteId, String imagePath) {
        this.noteImageId = noteImageId;
        this.noteId = noteId;
        this.imagePath = imagePath;
    }

    public Integer getNoteImageId() {
        return noteImageId;
    }

    public Integer getNoteId() {
        return noteId;
    }

    public String getImagePath() {
        return imagePath;
    }
}

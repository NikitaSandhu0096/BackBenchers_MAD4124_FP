package com.example.backbenchers_mad4124_fp.models;

import android.location.Location;

public class NoteLocation {
    private Integer noteLoactionId;
    private Integer noteId;
    private Location location;

    public NoteLocation(Integer noteLoactionId, Integer noteId, Location location) {
        this.noteLoactionId = noteLoactionId;
        this.noteId = noteId;
        this.location = location;
    }

    public Integer getNoteLoactionId() {
        return noteLoactionId;
    }

    public Integer getNoteId() {
        return noteId;
    }

    public Location getLocation() {
        return location;
    }
}

package com.example.backbenchers_mad4124_fp.models;

public class Subject {
    private String subjectName;

    public Subject(String subjectName){
        this.subjectName = subjectName;
    }

    public Subject(){

    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
}

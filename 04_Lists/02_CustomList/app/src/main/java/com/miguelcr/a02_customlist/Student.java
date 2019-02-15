package com.miguelcr.a02_customlist;

public class Student {
    private String name;
    private float rate;
    private String classroom;
    private String photo;

    public Student(String name, float rate, String classroom, String photo) {
        this.name = name;
        this.rate = rate;
        this.classroom = classroom;
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}

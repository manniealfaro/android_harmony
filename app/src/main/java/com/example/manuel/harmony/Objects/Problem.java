package com.example.manuel.harmony.Objects;

/**
 * Created by manuel on 19/12/2017.
 */

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.Date;

/**
 * Created by Ravi Tamada on 07/10/16.
 * www.androidhive.info
 */

@IgnoreExtraProperties
public class Problem {

    public String uploader;
    public Date date;
    public String grade;
    public String name;
    public String comment;
    public byte[] image;
    public int ascents;
    public int points;

    // Default constructor required for calls to
    // DataSnapshot.getValue(User.class)
    public Problem() {
    }

    public Problem(String name, String grade) {
        this.name = name;
        this.grade = grade;
    }

    public Problem(String name, String grade, byte[] image) {
        this.name = name;
        this.grade = grade;
        this.image = image;
    }

    public Problem(String name, String grade, byte[] image, String comment, String uploader) {
        this.name = name;
        this.grade = grade;
        this.image = image;
        this.comment = comment;
        this.uploader = uploader;
    }
}

package com.example.manuel.harmony.Objects;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by joseca on 18/12/17.
 */

@org.parceler.Parcel
public class Boulder {
    public String uploader;
    public Date date;
    public String grade;
    public String name;
    public String comment;
    public byte[] image;
    public int ascents;
    public int points;      //Out of 5. Given by the community.


    public Boulder(){


    }

    public Boulder(String user, String name, Date date, String gra, String com) {
        this.uploader = user;
        this.date = date;
        this.grade = gra;
        this.comment = com;
        this.name = name;

    }

    public Boulder(String user, String name, Date date, String gra, String com, byte[] img) {
        this.uploader = user;
        this.date = date;
        this.grade = gra;
        this.comment = com;
        this.image = img;
        this.name = name;

    }
}

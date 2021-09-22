package com.aziz.azdbmovie.database.entitas;

import android.graphics.Bitmap;
import android.widget.ImageView;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;


@Entity
public class Movies {
    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "judul")
    public String judul;

    @ColumnInfo(name = "release")
    public String releasedate;

    @ColumnInfo(name = "ket")
    public String overview;

    public Double rate;

//    @ColumnInfo(name = "hero")
//    public String imgHero;






}

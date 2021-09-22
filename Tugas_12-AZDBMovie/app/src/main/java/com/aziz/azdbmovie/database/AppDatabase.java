package com.aziz.azdbmovie.database;

import androidx.room.Database;

import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;


import com.aziz.azdbmovie.database.dao.MoviesDao;
import com.aziz.azdbmovie.database.entitas.Movies;


@Database(entities = {Movies.class},version = 1, exportSchema = false)
@TypeConverters({Converter.class})
public abstract class AppDatabase extends RoomDatabase {

    public  abstract MoviesDao userDao();



}

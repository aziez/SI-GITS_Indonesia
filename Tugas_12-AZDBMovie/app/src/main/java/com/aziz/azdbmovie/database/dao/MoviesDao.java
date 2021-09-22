package com.aziz.azdbmovie.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.aziz.azdbmovie.database.entitas.Movies;

import java.util.List;

@Dao
public interface MoviesDao {

    @Insert
    void insertAll(Movies... movies);

    @Query("SELECT * FROM movies")
    List<Movies> getAll();

    @Delete
    void delete(Movies movies);

}

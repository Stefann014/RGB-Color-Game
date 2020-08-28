package com.example.rgbcolorgame.activity.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.rgbcolorgame.activity.domain.Score;

import java.util.List;

@Dao
public interface ScoreDAO {
    @Insert
    void insert(Score score);

    @Update
    void update(Score score);

    @Delete
    void delete(Score score);

    @Query("DELETE FROM rezultat_table")
    void deleteAllRezultati();


    @Query("SELECT * FROM rezultat_table ORDER BY rezultat DESC")
    LiveData<List<Score>> getAllRezultati();
}

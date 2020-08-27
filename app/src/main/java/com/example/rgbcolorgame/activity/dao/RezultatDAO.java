package com.example.rgbcolorgame.activity.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.rgbcolorgame.activity.domain.Rezultat;

import java.util.List;

@Dao
public interface RezultatDAO {
    @Insert
    void insert(Rezultat rezultat);

    @Update
    void update(Rezultat rezultat);

    @Delete
    void delete(Rezultat rezultat);

    @Query("DELETE FROM rezultat_table")
    void deleteAllRezultati();


    @Query("SELECT * FROM rezultat_table ORDER BY rezultat DESC")
    LiveData<List<Rezultat>> getAllRezultati();
}

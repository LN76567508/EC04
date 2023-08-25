package com.jayo.ec04.data.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.jayo.ec04.model.ShowEntity;

import java.util.List;

@Dao
public interface ShowDao {
    @Insert
    public void addShow(ShowEntity show);

    @Query("SELECT * FROM show WHERE show_name LIKE :name LIMIT 1")
    public ShowEntity getShowByName(String name);

    @Query("SELECT * FROM show")
    public LiveData<List<ShowEntity>> getAll();

//    @Delete
//    public void deleteShow(ShowEntity show);
}
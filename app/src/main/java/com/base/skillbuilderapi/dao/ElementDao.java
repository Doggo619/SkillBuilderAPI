package com.base.skillbuilderapi.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.base.skillbuilderapi.entity.ElementEntity;

import java.util.List;

@Dao
public interface ElementDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ElementEntity elementEntity);

    @Query("SELECT * FROM ELEMENT")
    LiveData<List<ElementEntity>> getAllElements();
    @Query("DELETE FROM ELEMENT")
    void clearAll();
}

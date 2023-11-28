package com.base.skillbuilderapi;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ElementDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<ElementEntity> elementEntities);

    @Query("SELECT * FROM ELEMENT")
    LiveData<List<ElementEntity>> getAllElements();
}

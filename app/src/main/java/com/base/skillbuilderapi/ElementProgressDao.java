package com.base.skillbuilderapi;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;
@Dao
public interface ElementProgressDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<ElementProgressEntity> elementProgressEntities);

    @Query("SELECT * FROM ELEMENT_PROGRESS")
    LiveData<List<ElementProgressEntity>> getAllElementProgress();
}

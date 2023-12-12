package com.base.skillbuilderapi.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.base.skillbuilderapi.entity.ElementProgressEntity;

import java.util.List;
@Dao
public interface ElementProgressDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ElementProgressEntity elementProgressEntity);

    @Query("SELECT * FROM ELEMENT_PROGRESS")
    LiveData<List<ElementProgressEntity>> getAllElementProgress();
}

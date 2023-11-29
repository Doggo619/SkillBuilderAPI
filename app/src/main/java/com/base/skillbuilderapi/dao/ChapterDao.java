package com.base.skillbuilderapi.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.base.skillbuilderapi.entity.ChapterEntity;

import java.util.List;

@Dao
public interface ChapterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ChapterEntity chapterEntity);

    @Query("SELECT * FROM CHAPTER")
    LiveData<List<ChapterEntity>> getAllChapters();
    @Query("DELETE FROM CHAPTER")
    void clearAll();
}

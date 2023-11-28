package com.base.skillbuilderapi;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ChapterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<ChapterEntity> chapterEntities);
    @Query("SELECT * FROM CHAPTER")
    LiveData<List<ChapterEntity>> getAllChapters();
}

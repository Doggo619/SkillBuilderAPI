package com.base.skillbuilderapi.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.base.skillbuilderapi.entity.ElementEntity;

import java.util.List;

@Dao
public interface ElementDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ElementEntity elementEntity);

    @Query("SELECT * FROM ELEMENT")
    LiveData<List<ElementEntity>> getAllElements();
    @Update
    void updateElement(ElementEntity elementEntity);

    @Query("UPDATE ELEMENT SET user_name = :userName, milestone_level = :milestoneLevel, milestone_date = :milestoneDate, current_progress = :currentProgress, max_star = :maxStar, certificate_earned = :certificateEarned, certificate_date = :certificateDate WHERE element_id = :elementId AND chapter_id = :chapterId")
    void updateElementProgress(int elementId, int chapterId, String userName, int milestoneLevel, long milestoneDate, int currentProgress, int maxStar, int certificateEarned, long certificateDate);
}

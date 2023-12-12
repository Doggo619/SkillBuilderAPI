package com.base.skillbuilderapi.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.base.skillbuilderapi.entity.ChapterEntity;
import com.base.skillbuilderapi.model.elementProgressList.ChapterElementList;
import com.base.skillbuilderapi.model.elementProgressList.ChapterList;

import java.util.List;

@Dao
public interface ChapterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ChapterEntity chapterEntity);

    @Query("SELECT * FROM CHAPTER")
    LiveData<List<ChapterEntity>> getAllChapters();
    @Query("SELECT CHAPTER.chapter_id AS chapterId, CHAPTER.chapter_name AS chapterName, CHAPTER.display_id AS displayId, CHAPTER.deleted AS deleted, ELEMENT.element_id AS elementId, ELEMENT.display_id AS elementDisplayId, ELEMENT.element_type AS elementType, ELEMENT.sb_type AS sbType, ELEMENT.element_name AS elementName, ELEMENT.element_is_deleted AS elementIsDeleted, ELEMENT.user_name AS userName, ELEMENT.milestone_level AS milestoneLevel, ELEMENT.milestone_date AS milestoneDate, ELEMENT.current_progress AS currentProgress, ELEMENT.max_star AS maxStar, ELEMENT.certificate_earned AS certificateEarned, ELEMENT.certificate_date AS certificateDate FROM CHAPTER LEFT JOIN ELEMENT ON CHAPTER.chapter_id = ELEMENT.chapter_id WHERE CHAPTER.deleted = 0")
    LiveData<List<ChapterElementList>> getChapterElementList();
}

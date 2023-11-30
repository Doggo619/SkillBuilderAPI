package com.base.skillbuilderapi.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.base.skillbuilderapi.entity.ChapterEntity;
import com.base.skillbuilderapi.model.elementProgressList.ChapterList;

import java.util.List;

@Dao
public interface ChapterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ChapterEntity chapterEntity);

    @Query("SELECT * FROM CHAPTER")
    LiveData<List<ChapterEntity>> getAllChapters();
    @Query("DELETE FROM CHAPTER")
    void clearAll();
    @Query("SELECT CHAPTER.chapterId AS chapterId, CHAPTER.chapterName AS chapterName, CHAPTER.displayId AS displayId, CHAPTER.deleted AS deleted, ELEMENT.elementId AS elementId, ELEMENT.chapterId AS elementChapterId, ELEMENT.displayId AS elementDisplayId, ELEMENT.elementType AS elementType, ELEMENT.sbType AS sbType, ELEMENT.elementName AS elementName, ELEMENT.elementIsDeleted AS elementIsDeleted, ELEMENT_PROGRESS.userName AS userName, ELEMENT_PROGRESS.milestoneLevel AS milestoneLevel, ELEMENT_PROGRESS.milestoneDate AS milestoneDate, ELEMENT_PROGRESS.currentProgress AS currentProgress, ELEMENT_PROGRESS.maxStar AS maxStar, ELEMENT_PROGRESS.certificateEarned AS certificateEarned, ELEMENT_PROGRESS.certificateDate AS certificateDate FROM CHAPTER LEFT JOIN ELEMENT ON CHAPTER.chapterId = ELEMENT.chapterId LEFT JOIN ELEMENT_PROGRESS ON ELEMENT.chapterId = ELEMENT_PROGRESS.chapterId AND ELEMENT.elementId = ELEMENT_PROGRESS.elementId WHERE CHAPTER.deleted = 0")
    LiveData<List<ChapterList>> getChapterElementProgress();
}

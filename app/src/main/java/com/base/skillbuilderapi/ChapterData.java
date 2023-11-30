package com.base.skillbuilderapi;

import com.base.skillbuilderapi.entity.ChapterEntity;
import com.base.skillbuilderapi.entity.ElementEntity;
import com.base.skillbuilderapi.entity.ElementProgressEntity;

import java.util.List;

public class ChapterData {
    private ChapterEntity chapterEntity;
    private List<ElementEntity> elementEntities;
    private List<ElementProgressEntity> progressEntities;

    public ChapterData(ChapterEntity chapterEntity, List<ElementEntity> elementEntities, List<ElementProgressEntity> progressEntities) {
        this.chapterEntity = chapterEntity;
        this.elementEntities = elementEntities;
        this.progressEntities = progressEntities;
    }

    public ChapterEntity getChapterEntity() {
        return chapterEntity;
    }

    public List<ElementEntity> getElementEntities() {
        return elementEntities;
    }

    public List<ElementProgressEntity> getProgressEntities() {
        return progressEntities;
    }
}


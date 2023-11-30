package com.base.skillbuilderapi.model.elementProgressList;

import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import com.base.skillbuilderapi.converter.DataConverter;
import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class ChapterList {
    @SerializedName("chapter_id")
    private int chapterId;
    @SerializedName("chapter_name")
    private String chapterName;
    @SerializedName("display_id")
    private int displayId;
    @SerializedName("deleted")
    private int deleted;
    @SerializedName("element_progress")
    @TypeConverters(DataConverter.class)
    private ElementProgressList elementProgressList;

    public ChapterList(int chapterId, String chapterName, int displayId, int deleted, ElementProgressList elementProgressList) {
        this.chapterId = chapterId;
        this.chapterName = chapterName;
        this.displayId = displayId;
        this.deleted = deleted;
        this.elementProgressList = elementProgressList;
    }

    public int getChapterId() {
        return chapterId;
    }

    public void setChapterId(int chapterId) {
        this.chapterId = chapterId;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public int getDisplayId() {
        return displayId;
    }

    public void setDisplayId(int displayId) {
        this.displayId = displayId;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    public ElementProgressList getElementProgressList() {
        return elementProgressList;
    }

    public void setElementProgressList(ElementProgressList elementProgressList) {
        this.elementProgressList = elementProgressList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChapterList that = (ChapterList) o;
        return chapterId == that.chapterId && displayId == that.displayId && deleted == that.deleted && Objects.equals(chapterName, that.chapterName) && Objects.equals(elementProgressList, that.elementProgressList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(chapterId, chapterName, displayId, deleted, elementProgressList);
    }
}



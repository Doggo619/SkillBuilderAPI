package com.base.skillbuilderapi;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "CHAPTER", indices = {@Index(name = "INDEX_CHAPTER", value = {"chapterId"},
        unique = true)})
public class ChapterEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @SerializedName("chapter_id")
    private int chapterId;
    @SerializedName("chapter_name")
    private String chapterName;
    @SerializedName("display_id")
    private int displayId;
    @SerializedName("deleted")
    private int deleted;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}

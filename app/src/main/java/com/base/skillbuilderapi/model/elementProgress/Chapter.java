package com.base.skillbuilderapi.model.elementProgress;

import com.google.gson.annotations.SerializedName;

public class Chapter {
    @SerializedName("chapter_id")
    private int chapterId;
    @SerializedName("chapter_name")
    private String chapterName;
    @SerializedName("display_id")
    private int displayId;
    @SerializedName("deleted")
    private int deleted;

    public Chapter(int chapterId, String chapterName, int displayId, int deleted) {
        this.chapterId = chapterId;
        this.chapterName = chapterName;
        this.displayId = displayId;
        this.deleted = deleted;
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

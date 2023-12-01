package com.base.skillbuilderapi.model.elementProgressList;

import com.google.gson.Gson;

import java.util.List;

public class ChapterElementStat {
    List<ChapterList> chapterLists;

    public List<ChapterList> getChapterLists() {
        return chapterLists;
    }

    public void setChapterLists(List<ChapterList> chapterLists) {
        this.chapterLists = chapterLists;
    }

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}

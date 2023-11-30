package com.base.skillbuilderapi.converter;

import androidx.room.TypeConverter;

import com.base.skillbuilderapi.model.elementProgressList.ChapterList;
import com.base.skillbuilderapi.model.elementProgressList.ElementProgressList;
import com.google.gson.Gson;

public class DataConverter {
    @TypeConverter
    public String fromChapters(ChapterList chapterList) {
        if (chapterList == null) {
            return (null);
        }
        Gson gson = new Gson();
        return gson.toJson(chapterList);
    }

    @TypeConverter
    public ChapterList toChapters(String chaptersStr) {
        if (chaptersStr == null) {
            return (null);
        }
        Gson gson = new Gson();
        return gson.fromJson(chaptersStr, ChapterList.class);
    }

    @TypeConverter
    public String fromElementProgresses(ElementProgressList elementProgressList) {
        if (elementProgressList == null) {
            return (null);
        }
        Gson gson = new Gson();
        return gson.toJson(elementProgressList);
    }

    @TypeConverter
    public ElementProgressList toElementProgresses(String elementProgressesStr) {
        if (elementProgressesStr == null) {
            return (null);
        }
        Gson gson = new Gson();
        return gson.fromJson(elementProgressesStr, ElementProgressList.class);
    }
}

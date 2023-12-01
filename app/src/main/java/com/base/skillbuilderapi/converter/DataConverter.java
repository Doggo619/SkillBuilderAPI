package com.base.skillbuilderapi.converter;

import androidx.room.TypeConverter;

import com.base.skillbuilderapi.model.elementProgressList.ChapterList;
import com.base.skillbuilderapi.model.elementProgressList.ElementProgressList;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.List;
import com.google.gson.reflect.TypeToken;

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
    public String fromElementProgressList(List<ElementProgressList> elementProgressLists) {
        if (elementProgressLists == null) {
            return (null);
        }
        Gson gson = new Gson();
        return gson.toJson(elementProgressLists);
    }

    @TypeConverter
    public List<ElementProgressList> toElementProgressList(String elementProgressListsStr) {
        if (elementProgressListsStr == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type listType = new TypeToken<List<ElementProgressList>>() {}.getType();
        return gson.fromJson(elementProgressListsStr, listType);
    }
}

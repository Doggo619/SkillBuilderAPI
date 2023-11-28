package com.base.skillbuilderapi;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.List;

public class MyRepository {
    private final ElementProgressDao elementProgressDao;
    private final ElementDao elementDao;
    private final ChapterDao chapterDao;
    private final LiveData<List<ElementProgressEntity>> allElementProgress;
    private final LiveData<List<ElementEntity>> allElement;
    private final LiveData<List<ChapterEntity>> allChapter;
    public MyRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        elementProgressDao = db.elementProgressDao();
        elementDao = db.elementDao();
        chapterDao = db.chapterDao();
        allElementProgress = elementProgressDao.getAllElementProgress();
        allElement = elementDao.getAllElements();
        allChapter = chapterDao.getAllChapters();
    }
    public void insertData(JsonInfo jsonInfo) {
        List<ElementProgressEntity> elementProgressEntities = jsonInfo.getData().getElementProgress();
        Log.d("MyRepository", "Data before insertion: " + elementProgressEntities.toString());
        List<ElementEntity> elementEntities = jsonInfo.getData().getElements();
        List<ChapterEntity> chapterEntities = jsonInfo.getData().getChapters();

        elementProgressDao.insertAll(elementProgressEntities);
        Log.d("MyRepository", "Data after insertion: " + elementProgressDao.getAllElementProgress().toString());
        elementDao.insertAll(elementEntities);
        chapterDao.insertAll(chapterEntities);
    }

    public LiveData<List<ElementProgressEntity>> getAllElementProgress() {
        return allElementProgress;
    }
    public LiveData<List<ElementEntity>> getAllElement() {
        return allElement;
    }
    public LiveData<List<ChapterEntity>> getAllChapter() {
        return allChapter;
    }
}

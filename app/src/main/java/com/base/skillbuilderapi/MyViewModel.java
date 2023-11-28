package com.base.skillbuilderapi;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class MyViewModel extends AndroidViewModel {
    private final MyRepository repository;
    private final LiveData<List<ElementProgressEntity>> allElementProgress;
    private final LiveData<List<ElementEntity>> allElement;
    private final LiveData<List<ChapterEntity>> allChapter;
    public MyViewModel(Application application) {
        super(application);
        repository = new MyRepository(application);
        allElementProgress = repository.getAllElementProgress();
        allElement = repository.getAllElement();
        allChapter = repository.getAllChapter();
    }
    public void insertData(JsonInfo jsonInfo) {
        repository.insertData(jsonInfo);
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

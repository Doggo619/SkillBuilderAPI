package com.base.skillbuilderapi.ui;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.base.skillbuilderapi.model.elementProgressList.ChapterElementList;
import com.base.skillbuilderapi.model.errorHandling.ApiStatusResponse;
import com.base.skillbuilderapi.model.errorHandling.Resource;
import com.base.skillbuilderapi.repository.MyRepository;
import com.base.skillbuilderapi.entity.ChapterEntity;
import com.base.skillbuilderapi.entity.ElementEntity;
import com.base.skillbuilderapi.entity.ElementProgressEntity;

import java.util.List;

public class MyViewModel extends AndroidViewModel {
    private final MyRepository repository;
    private final LiveData<List<ElementProgressEntity>> allElementProgress;
    private final LiveData<List<ElementEntity>> allElement;
    private final LiveData<List<ChapterEntity>> allChapter;

    public MyViewModel(Application application, MyRepository repository) {
        super(application);
        this.repository = repository;
        allElementProgress = repository.getAllElementProgress();
        allElement = repository.getAllElement();
        allChapter = repository.getAllChapter();
    }
   public LiveData<Resource<ApiStatusResponse>> getElementProgressApi() {
        return repository.getElementProgressApi();
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
    @Override
    protected void onCleared() {
        super.onCleared();
    }


    public LiveData<List<ChapterElementList>> getChapterElementList() {
        return repository.getChapterElementList();
    }
}

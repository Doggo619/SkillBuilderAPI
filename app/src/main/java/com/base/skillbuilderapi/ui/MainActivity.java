package com.base.skillbuilderapi.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.base.skillbuilderapi.JsonInfo;
import com.base.skillbuilderapi.MockJson;
import com.base.skillbuilderapi.utils.PicassoCircleTransformation;
import com.base.skillbuilderapi.R;
import com.base.skillbuilderapi.entity.ChapterEntity;
import com.base.skillbuilderapi.entity.ElementEntity;
import com.base.skillbuilderapi.entity.ElementProgressEntity;
import com.base.skillbuilderapi.model.elementProgressList.ChapterElementList;
import com.base.skillbuilderapi.model.elementProgressList.ChapterElementStat;
import com.base.skillbuilderapi.model.elementProgressList.ChapterList;
import com.base.skillbuilderapi.model.elementProgressList.ElementProgressList;
import com.base.skillbuilderapi.model.errorHandling.ApiStatusResponse;
import com.base.skillbuilderapi.model.errorHandling.Resource;
import com.base.skillbuilderapi.repository.MyRepository;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private MyViewModel viewModel;
    private ChapterAdapter chapterAdapter;
    ImageView profilePicture;
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        profilePicture = findViewById(R.id.iv_profilePicture);

        Picasso.get()
                .load("https://sm.ign.com/ign_nordic/cover/a/avatar-gen/avatar-generations_prsz.jpg")
                .error(R.drawable.ic_baseline_share_24)
                .transform(new PicassoCircleTransformation())
                .into(profilePicture);

        MyRepository repository = new MyRepository(getApplication(), getApplicationContext());

        viewModel = new ViewModelProvider(this, new MyViewModelFactory(getApplication(), repository)).get(MyViewModel.class);


        viewModel.getElementProgressApi().observe(this, this::handleElementProgress);

//        viewModel.getChapterElementProgress().observe(this, this::handleChapterElementProgressList);

        viewModel.getChapterElementList().observe(this, this::handleChapterElementList);


        String jsonString = MockJson.JSON_DATA;
        Log.d("MainActivity", "JSON Data: " + jsonString);
        JsonInfo jsonInfo = new Gson().fromJson(jsonString, JsonInfo.class);

            ChapterAdapter.OnItemClickListener listener = new ChapterAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(ElementProgressList elementProgressList, String chapterName) {
                    if (elementProgressList.getMilestoneLevel() == 4) {
                        showCertificateDialog(elementProgressList, chapterName);
                    } else {
                        showDialog(elementProgressList, chapterName);
                    }
                }
            };
            chapterAdapter = new ChapterAdapter(getApplicationContext(), listener);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(chapterAdapter);
    }

    private void showCertificateDialog(ElementProgressList elementProgressList, String chapterName) {
        CertificateDialogFragment dialog = new CertificateDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString("ElementProgressList", elementProgressList.toJson());
        bundle.putString("chapterName", chapterName);
        dialog.setArguments(bundle);

        dialog.show(getSupportFragmentManager(), "ProgressDetailsDialog");
    }

    private void handleChapterElementList(List<ChapterElementList> chapterElementLists) {
        if (chapterElementLists != null && !chapterElementLists.isEmpty()) {
            for (ChapterElementList chapterElementList : chapterElementLists) {
                Log.d("Main Activity", "chapterelementlists" + chapterElementList.toJson());
            }

            ChapterElementStat chapterElementStat = convertToChapterElementStat(chapterElementLists);
            if (chapterElementStat!= null && chapterElementStat.getChapterLists().size() > 0) {
                chapterAdapter.submitList(chapterElementStat.getChapterLists());
            }
        }
    }


    private void handleElementProgress(Resource<ApiStatusResponse> apiStatusResponseResource) {
//        observeDataChanges();
    }


    private void observeDataChanges() {
        LiveData<List<ElementProgressEntity>> progressLiveData = viewModel.getAllElementProgress();
        LiveData<List<ElementEntity>> elementLiveData = viewModel.getAllElement();
        LiveData<List<ChapterEntity>> chapterLiveData = viewModel.getAllChapter();

        progressLiveData.observe(this, elementProgressEntities -> {
            updateAdapterWithData();
        });

        elementLiveData.observe(this, elementEntities -> {
            updateAdapterWithData();
        });

        chapterLiveData.observe(this, chapterEntities -> {
            updateAdapterWithData();
        });
    }

    private void updateAdapterWithData() {
        List<ElementProgressEntity> elementProgressEntities = viewModel.getAllElementProgress().getValue();
        List<ElementEntity> elementEntities = viewModel.getAllElement().getValue();
        List<ChapterEntity> chapterEntities = viewModel.getAllChapter().getValue();

//        if (elementProgressEntities != null && elementEntities != null && chapterEntities != null) {
//            chapterAdapter = new ChapterAdapter(getApplicationContext());
//            chapterAdapter.setOnItemClickListener((progressEntity, elementEntity, chapterEntity) -> {
//                showDialog(progressEntity, elementEntity, chapterEntity);
//            });
//            recyclerView.setLayoutManager(new LinearLayoutManager(this));
//            recyclerView.setAdapter(chapterAdapter);
//        }
    }

    public void showDialog(ElementProgressList elementProgressList, String chapterName) {
        DialogFragment dialog = new DialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString("ElementProgressList", elementProgressList.toJson());
        bundle.putString("chapterName", chapterName);
        dialog.setArguments(bundle);

        dialog.show(getSupportFragmentManager(), "ProgressDetailsDialog");
    }


    public static ChapterElementStat convertToChapterElementStat(List<ChapterElementList> flatList) {
        Log.d("Main activity", "flatlistsize" + flatList.size());
        if (flatList.isEmpty()) {
            return null;
        }

        Log.d("Main activity", "flatlist1");

        ChapterElementStat chapterElementStat = new ChapterElementStat();
        chapterElementStat.setChapterLists(new ArrayList<>());
        int currChapterId = 0;

        ChapterList chapterList = new ChapterList();

        for (ChapterElementList flatItem : flatList) {
            if ((currChapterId == 0) || (currChapterId != flatItem.getChapterId())) {
                if (currChapterId != 0) {
                    chapterElementStat.getChapterLists().add(chapterList);
                }
                currChapterId = flatItem.getChapterId();
                chapterList = new ChapterList();
                chapterList.setChapterId(currChapterId);
                chapterList.setChapterName(flatItem.getChapterName());
                chapterList.setDisplayId(flatItem.getDisplayId());
                chapterList.setDeleted(flatItem.getDeleted());
                chapterList.setElementProgressList(new ArrayList<>());
            }

            ElementProgressList elementProgress = new ElementProgressList();
            elementProgress.setElementId(flatItem.getElementId());
            elementProgress.setChapterId(flatItem.getChapterId());
            elementProgress.setDisplayId(flatItem.getElementDisplayId());
            elementProgress.setElementType(flatItem.getElementType());
            elementProgress.setSbType(flatItem.getSbType());
            elementProgress.setElementName(flatItem.getElementName());
            elementProgress.setElementIsDeleted(flatItem.getElementIsDeleted());
            elementProgress.setUserName(flatItem.getUserName());
            elementProgress.setMilestoneLevel(flatItem.getMilestoneLevel());
            elementProgress.setMilestoneDate(flatItem.getMilestoneDate());
            elementProgress.setCurrentProgress(flatItem.getCurrentProgress());
            elementProgress.setMaxStar(flatItem.getMaxStar());
            elementProgress.setCertificateEarned(flatItem.getCertificateEarned());
            elementProgress.setCertificateDate(flatItem.getCertificateDate());

            chapterList.getElementProgressList().add(elementProgress);
        }
        Log.d("Main activity", "flatlist2" + chapterList.toJson());

        if (chapterList != null) {
            chapterElementStat.getChapterLists().add(chapterList);
        }


        return chapterElementStat;
    }


}
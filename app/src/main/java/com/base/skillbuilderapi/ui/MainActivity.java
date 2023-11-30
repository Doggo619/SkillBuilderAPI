package com.base.skillbuilderapi.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.base.skillbuilderapi.JsonInfo;
import com.base.skillbuilderapi.MockJson;
import com.base.skillbuilderapi.PicassoCircleTransformation;
import com.base.skillbuilderapi.R;
import com.base.skillbuilderapi.entity.ChapterEntity;
import com.base.skillbuilderapi.entity.ElementEntity;
import com.base.skillbuilderapi.entity.ElementProgressEntity;
import com.base.skillbuilderapi.model.elementProgressList.ChapterList;
import com.base.skillbuilderapi.model.errorHandling.ApiStatusResponse;
import com.base.skillbuilderapi.model.errorHandling.Resource;
import com.base.skillbuilderapi.repository.MyRepository;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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

        viewModel.getChapterElementProgress().observe(this, this::handleChapterElementProgressList);


        String jsonString = MockJson.JSON_DATA;
        Log.d("MainActivity", "JSON Data: " + jsonString);
        JsonInfo jsonInfo = new Gson().fromJson(jsonString, JsonInfo.class);

            ChapterAdapter.OnItemClickListener listener = new ChapterAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(ElementProgressEntity progressEntity, ElementEntity elementEntity, ChapterEntity chapterEntity) {
//                    showDialog(progressEntity, elementEntity, chapterEntity);
                }
            };
            chapterAdapter = new ChapterAdapter(getApplicationContext(), listener);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(chapterAdapter);



//        Handler handler = new Handler();
//        ExecutorService databaseWriteExecutor =
//                Executors.newSingleThreadExecutor();
//        databaseWriteExecutor.execute(() -> {
//            viewModel.insertData(jsonInfo);
//            handler.post(() -> {
//                Toast.makeText(this, "Data Inserted", Toast.LENGTH_SHORT).show();
//                observeDataChanges();
//            });
//        });
    }

    private void handleChapterElementProgressList(List<ChapterList> chapterLists) {
        if (!chapterLists.isEmpty()) {
            chapterAdapter.submitList(chapterLists);
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

    public void showDialog(ElementProgressEntity progressEntity, ElementEntity elementEntity, ChapterEntity chapterEntity) {
        DialogFragment dialog = new DialogFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("progressEntity", progressEntity);
        bundle.putParcelable("elementEntity", elementEntity);
        bundle.putParcelable("chapterEntity", chapterEntity);
        dialog.setArguments(bundle);

        dialog.show(getSupportFragmentManager(), "ProgressDetailsDialog");
    }
}
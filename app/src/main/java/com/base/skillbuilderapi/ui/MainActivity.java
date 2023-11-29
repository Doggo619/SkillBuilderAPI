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
import com.base.skillbuilderapi.model.errorHandling.ApiStatusResponse;
import com.base.skillbuilderapi.model.errorHandling.Resource;
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

        viewModel = new ViewModelProvider(this).get(MyViewModel.class);

        viewModel.getElementProgressApi().observe(this, this::handleElementProgress);


        String jsonString = MockJson.JSON_DATA;
        Log.d("MainActivity", "JSON Data: " + jsonString);
        JsonInfo jsonInfo = new Gson().fromJson(jsonString, JsonInfo.class);

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

    private void handleElementProgress(Resource<ApiStatusResponse> apiStatusResponseResource) {

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

        if (elementProgressEntities != null && elementEntities != null && chapterEntities != null) {
            chapterAdapter = new ChapterAdapter(elementProgressEntities, elementEntities, chapterEntities);
            chapterAdapter.setOnItemClickListener((progressEntity, elementEntity, chapterEntity) -> {
                showDialog(progressEntity, elementEntity, chapterEntity);
            });
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(chapterAdapter);
        }
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
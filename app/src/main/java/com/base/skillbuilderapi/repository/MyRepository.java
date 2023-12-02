package com.base.skillbuilderapi.repository;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.base.skillbuilderapi.AppDatabase;
import com.base.skillbuilderapi.R;
import com.base.skillbuilderapi.dao.ChapterDao;
import com.base.skillbuilderapi.dao.ElementDao;
import com.base.skillbuilderapi.dao.ElementProgressDao;
import com.base.skillbuilderapi.entity.ChapterEntity;
import com.base.skillbuilderapi.entity.ElementEntity;
import com.base.skillbuilderapi.entity.ElementProgressEntity;
import com.base.skillbuilderapi.model.elementProgressList.ChapterElementList;
import com.base.skillbuilderapi.model.elementProgressApi.Chapter;
import com.base.skillbuilderapi.model.elementProgressApi.Element;
import com.base.skillbuilderapi.model.elementProgressApi.ElementProgress;
import com.base.skillbuilderapi.model.elementProgressApi.GetElementProgressOutput;
import com.base.skillbuilderapi.model.elementProgressList.ChapterList;
import com.base.skillbuilderapi.model.errorHandling.ApiStatusResponse;
import com.base.skillbuilderapi.model.errorHandling.Resource;
import com.base.skillbuilderapi.retrofit.ApiService;
import com.base.skillbuilderapi.retrofit.RetrofitClient;
import com.base.skillbuilderapi.utils.Constants;

import org.jetbrains.annotations.NotNull;

import java.net.SocketTimeoutException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyRepository {
    private final ElementProgressDao elementProgressDao;
    private final ElementDao elementDao;
    private final ChapterDao chapterDao;
    private final LiveData<List<ElementProgressEntity>> allElementProgress;
    private final LiveData<List<ElementEntity>> allElement;
    private final LiveData<List<ChapterEntity>> allChapter;
    private final ApiService apiService;
    public final RetrofitClient retrofitClient;
    private final Context context;

    public MyRepository(Application application, Context context) {
        AppDatabase db = AppDatabase.getInstance(application);
        elementProgressDao = db.elementProgressDao();
        elementDao = db.elementDao();
        chapterDao = db.chapterDao();
        allElementProgress = elementProgressDao.getAllElementProgress();
        allElement = elementDao.getAllElements();
        allChapter = chapterDao.getAllChapters();

        retrofitClient = new RetrofitClient(context);
        apiService = retrofitClient.getApiService();
        this.context = context;
    }

    public LiveData<Resource<ApiStatusResponse>> getElementProgressApi() {

        MutableLiveData<Resource<ApiStatusResponse>> apiResponse = new MutableLiveData<>();

//        if (!Common.isInternetConnected(applicationContext)) {
//            apiResponse.postValue(Resource.error(applicationContext.getResources().getString(R.string.connection_error_msg), new GetHighlightsOutput(Constants.STATUS_CODE_CONNECTIVITY_ISSUE, applicationContext.getResources().getString(R.string.connection_error_msg))));
//            return apiResponse;
//        }


        Call<GetElementProgressOutput> elementProgressOutputCall = retrofitClient
                .getApiService()
                .getElementProgressOutput("Hello");

        elementProgressOutputCall.enqueue(new Callback<GetElementProgressOutput>() {
            @Override
            public void onResponse(@NotNull Call<GetElementProgressOutput> call, @NotNull Response<GetElementProgressOutput> response) {
                GetElementProgressOutput body = response.body();

                if (body == null) {
                    apiResponse.postValue(Resource.error("", new ApiStatusResponse(response.code(), response.message())));
                    return;
                }

                if (body.getStatus() == Constants.STATUS_CODE_SUCCESS) {

                    apiResponse.postValue(Resource.success(new ApiStatusResponse(body.getStatus(), body.getMessage())));

                } else if (body.getStatus() == Constants.STATUS_CODE_TOKEN_MISMATCH || body.getStatus() == Constants.STATUS_CODE_SERVER_RESPONSE_MISSING_DATA) {
//                    clearAllTables();
                    apiResponse.postValue(Resource.error("", new ApiStatusResponse(body.getStatus(), "")));
                } else if (body.getStatus() == Constants.DATA_NOT_FOUND) {
                    apiResponse.postValue(Resource.error("", new ApiStatusResponse(body.getStatus(), body.getMessage())));
                } else {
                    apiResponse.postValue(Resource.error("", new ApiStatusResponse(body.getStatus(), "")));
                }
            }

            @Override
            public void onFailure(@NotNull Call<GetElementProgressOutput> call, @NotNull Throwable t) {
               String jsonString = "{\"status\":200,\"message\":\"Success\",\"last_updated\":12345,\"data\":{\"element_progress\":[{\"chapter_id\":1,\"element_id\":1,\"element_type\":1,\"user_name\":\"Priya\",\"milestone_level\":4,\"milestone_date\":2292929,\"current_progress\":55,\"max_star\":2,\"certificate_earned\":4001,\"certificate_date\":1701088814},{\"chapter_id\":1,\"element_id\":2,\"element_type\":2,\"user_name\":\"Kannan\",\"milestone_level\":4,\"milestone_date\":2292929,\"current_progress\":75,\"max_star\":2,\"certificate_earned\":4002,\"certificate_date\":123344},{\"chapter_id\":2,\"element_id\":1,\"element_type\":1,\"user_name\":\"Murugaaa\",\"milestone_level\":1,\"milestone_date\":2292929,\"current_progress\":10,\"max_star\":3,\"certificate_earned\":4002,\"certificate_date\":123344},{\"chapter_id\":2,\"element_id\":2,\"element_type\":2,\"user_name\":\"Murugaaa\",\"milestone_level\":3,\"milestone_date\":2292929,\"current_progress\":100,\"max_star\":2,\"certificate_earned\":4001,\"certificate_date\":123344},{\"chapter_id\":2,\"element_id\":3,\"element_type\":3,\"user_name\":\"Murugaaa\",\"milestone_level\":4,\"milestone_date\":2292929,\"current_progress\":30,\"max_star\":1,\"certificate_earned\":4003,\"certificate_date\":123344},{\"chapter_id\":2,\"element_id\":4,\"element_type\":4,\"user_name\":\"Murugaaa\",\"milestone_level\":4,\"milestone_date\":2292929,\"current_progress\":100,\"max_star\":2,\"certificate_earned\":4003,\"certificate_date\":123344},{\"chapter_id\":2,\"element_id\":5,\"element_type\":5,\"user_name\":\"Murugaaa\",\"milestone_level\":3,\"milestone_date\":2292929,\"current_progress\":100,\"max_star\":0,\"certificate_earned\":4003,\"certificate_date\":123344},{\"chapter_id\":2,\"element_id\":6,\"element_type\":6,\"user_name\":\"Murugaaa\",\"milestone_level\":1,\"milestone_date\":2292929,\"current_progress\":100,\"max_star\":3,\"certificate_earned\":4003,\"certificate_date\":123344},{\"chapter_id\":2,\"element_id\":7,\"element_type\":7,\"user_name\":\"Murugaaa\",\"milestone_level\":0,\"milestone_date\":2292929,\"current_progress\":60,\"max_star\":2,\"certificate_earned\":4003,\"certificate_date\":123344},{\"chapter_id\":2,\"element_id\":8,\"element_type\":8,\"user_name\":\"Murugaaa\",\"milestone_level\":3,\"milestone_date\":2292929,\"current_progress\":100,\"max_star\":2,\"certificate_earned\":4003,\"certificate_date\":123344},{\"chapter_id\":2,\"element_id\":9,\"element_type\":9,\"user_name\":\"Murugaaa\",\"milestone_level\":2,\"milestone_date\":2292929,\"current_progress\":100,\"max_star\":1,\"certificate_earned\":4003,\"certificate_date\":123344},{\"chapter_id\":2,\"element_id\":10,\"element_type\":10,\"user_name\":\"Murugaaa\",\"milestone_level\":4,\"milestone_date\":2292929,\"current_progress\":100,\"max_star\":3,\"certificate_earned\":4003,\"certificate_date\":123344},{\"chapter_id\":3,\"element_id\":1,\"element_type\":1,\"user_name\":\"Murugaaa\",\"milestone_level\":4,\"milestone_date\":2292929,\"current_progress\":50,\"max_star\":3,\"certificate_earned\":4002,\"certificate_date\":123344},{\"chapter_id\":3,\"element_id\":2,\"element_type\":2,\"user_name\":\"Murugaaa\",\"milestone_level\":4,\"milestone_date\":2292929,\"current_progress\":100,\"max_star\":3,\"certificate_earned\":4002,\"certificate_date\":123344},{\"chapter_id\":3,\"element_id\":3,\"element_type\":3,\"user_name\":\"Murugaaa\",\"milestone_level\":2,\"milestone_date\":2292929,\"current_progress\":70,\"max_star\":2,\"certificate_earned\":4003,\"certificate_date\":123344},{\"chapter_id\":3,\"element_id\":4,\"element_type\":4,\"user_name\":\"Murugaaa\",\"milestone_level\":0,\"milestone_date\":2292929,\"current_progress\":0,\"max_star\":0,\"certificate_earned\":4003,\"certificate_date\":123344},{\"chapter_id\":4,\"element_id\":1,\"element_type\":1,\"user_name\":\"Murugaaa\",\"milestone_level\":0,\"milestone_date\":2292929,\"current_progress\":0,\"max_star\":0,\"certificate_earned\":4003,\"certificate_date\":123344},{\"chapter_id\":4,\"element_id\":2,\"element_type\":2,\"user_name\":\"Murugaaa\",\"milestone_level\":2,\"milestone_date\":2292929,\"current_progress\":80,\"max_star\":2,\"certificate_earned\":4001,\"certificate_date\":123344},{\"chapter_id\":4,\"element_id\":3,\"element_type\":3,\"user_name\":\"Murugaaa\",\"milestone_level\":3,\"milestone_date\":2292929,\"current_progress\":55,\"max_star\":3,\"certificate_earned\":4002,\"certificate_date\":123344},{\"chapter_id\":5,\"element_id\":1,\"element_type\":1,\"user_name\":\"Murugaaa\",\"milestone_level\":1,\"milestone_date\":2292929,\"current_progress\":50,\"max_star\":3,\"certificate_earned\":4003,\"certificate_date\":123344},{\"chapter_id\":5,\"element_id\":2,\"element_type\":2,\"user_name\":\"Murugaaa\",\"milestone_level\":4,\"milestone_date\":2292929,\"current_progress\":100,\"max_star\":3,\"certificate_earned\":4003,\"certificate_date\":123344},{\"chapter_id\":5,\"element_id\":3,\"element_type\":3,\"user_name\":\"Murugaaa\",\"milestone_level\":3,\"milestone_date\":2292929,\"current_progress\":40,\"max_star\":3,\"certificate_earned\":4003,\"certificate_date\":123344},{\"chapter_id\":5,\"element_id\":4,\"element_type\":4,\"user_name\":\"Murugaaa\",\"milestone_level\":2,\"milestone_date\":2292929,\"current_progress\":20,\"max_star\":0,\"certificate_earned\":4001,\"certificate_date\":123344},{\"chapter_id\":5,\"element_id\":5,\"element_type\":5,\"user_name\":\"Murugaaa\",\"milestone_level\":2,\"milestone_date\":2292929,\"current_progress\":60,\"max_star\":2,\"certificate_earned\":4002,\"certificate_date\":123344},{\"chapter_id\":6,\"element_id\":1,\"element_type\":1,\"user_name\":\"Murugaaa\",\"milestone_level\":3,\"milestone_date\":2292929,\"current_progress\":70,\"max_star\":0,\"certificate_earned\":4001,\"certificate_date\":123344},{\"chapter_id\":6,\"element_id\":2,\"element_type\":2,\"user_name\":\"Murugaaa\",\"milestone_level\":4,\"milestone_date\":2292929,\"current_progress\":100,\"max_star\":1,\"certificate_earned\":4003,\"certificate_date\":123344},{\"chapter_id\":6,\"element_id\":3,\"element_type\":3,\"user_name\":\"Murugaaa\",\"milestone_level\":4,\"milestone_date\":2292929,\"current_progress\":50,\"max_star\":2,\"certificate_earned\":4001,\"certificate_date\":123344}],\"elements\":[{\"element_id\":1,\"chapter_id\":1,\"display_id\":1,\"element_type\":1,\"sb_type\":2,\"element_name\":\"Electric Dipole\",\"element_is_deleted\":0},{\"element_id\":2,\"chapter_id\":1,\"display_id\":2,\"element_type\":2,\"sb_type\":1,\"element_name\":\"Mass\",\"element_is_deleted\":0},{\"element_id\":1,\"chapter_id\":2,\"display_id\":1,\"element_type\":1,\"sb_type\":2,\"element_name\":\"Force\",\"element_is_deleted\":0},{\"element_id\":2,\"chapter_id\":2,\"display_id\":2,\"element_type\":1,\"sb_type\":2,\"element_name\":\"Classical Mechanics\",\"element_is_deleted\":0},{\"element_id\":3,\"chapter_id\":2,\"display_id\":3,\"element_type\":1,\"sb_type\":2,\"element_name\":\"Electromagnetism\",\"element_is_deleted\":0},{\"element_id\":4,\"chapter_id\":2,\"display_id\":4,\"element_type\":1,\"sb_type\":1,\"element_name\":\"Optics\",\"element_is_deleted\":0},{\"element_id\":5,\"chapter_id\":2,\"display_id\":5,\"element_type\":1,\"sb_type\":2,\"element_name\":\"Relativity\",\"element_is_deleted\":0},{\"element_id\":6,\"chapter_id\":2,\"display_id\":6,\"element_type\":1,\"sb_type\":1,\"element_name\":\"Quantum Mechanics\",\"element_is_deleted\":0},{\"element_id\":7,\"chapter_id\":2,\"display_id\":7,\"element_type\":1,\"sb_type\":2,\"element_name\":\"Nuclear Physics\",\"element_is_deleted\":0},{\"element_id\":8,\"chapter_id\":2,\"display_id\":8,\"element_type\":1,\"sb_type\":1,\"element_name\":\"Particle Physics\",\"element_is_deleted\":0},{\"element_id\":9,\"chapter_id\":2,\"display_id\":9,\"element_type\":1,\"sb_type\":2,\"element_name\":\"Astrophysics\",\"element_is_deleted\":0},{\"element_id\":10,\"chapter_id\":2,\"display_id\":10,\"element_type\":1,\"sb_type\":2,\"element_name\":\"Biophysics\",\"element_is_deleted\":0},{\"element_id\":1,\"chapter_id\":3,\"display_id\":1,\"element_type\":1,\"sb_type\":2,\"element_name\":\"Acoustics\",\"element_is_deleted\":0},{\"element_id\":2,\"chapter_id\":3,\"display_id\":2,\"element_type\":1,\"sb_type\":2,\"element_name\":\"Condensed Matter Physics\",\"element_is_deleted\":0},{\"element_id\":3,\"chapter_id\":3,\"display_id\":3,\"element_type\":1,\"sb_type\":1,\"element_name\":\"Fluid Dynamics\",\"element_is_deleted\":0},{\"element_id\":4,\"chapter_id\":3,\"display_id\":4,\"element_type\":1,\"sb_type\":2,\"element_name\":\"Plasma Physics\",\"element_is_deleted\":0},{\"element_id\":1,\"chapter_id\":4,\"display_id\":1,\"element_type\":1,\"sb_type\":2,\"element_name\":\"Chaos Theory\",\"element_is_deleted\":0},{\"element_id\":2,\"chapter_id\":4,\"display_id\":2,\"element_type\":1,\"sb_type\":2,\"element_name\":\"Computational Physics\",\"element_is_deleted\":0},{\"element_id\":3,\"chapter_id\":4,\"display_id\":3,\"element_type\":1,\"sb_type\":2,\"element_name\":\"Historical Physics\",\"element_is_deleted\":0},{\"element_id\":1,\"chapter_id\":5,\"display_id\":1,\"element_type\":1,\"sb_type\":2,\"element_name\":\"Computational Physics\",\"element_is_deleted\":0},{\"element_id\":2,\"chapter_id\":5,\"display_id\":2,\"element_type\":1,\"sb_type\":2,\"element_name\":\"Quantum Computing\",\"element_is_deleted\":0},{\"element_id\":3,\"chapter_id\":5,\"display_id\":3,\"element_type\":1,\"sb_type\":1,\"element_name\":\"String Theory\",\"element_is_deleted\":0},{\"element_id\":4,\"chapter_id\":5,\"display_id\":4,\"element_type\":1,\"sb_type\":2,\"element_name\":\"Medical Physics\",\"element_is_deleted\":0},{\"element_id\":5,\"chapter_id\":5,\"display_id\":5,\"element_type\":1,\"sb_type\":2,\"element_name\":\"Oceanography\",\"element_is_deleted\":0},{\"element_id\":1,\"chapter_id\":6,\"display_id\":1,\"element_type\":1,\"sb_type\":2,\"element_name\":\"Radiation Therapy\",\"element_is_deleted\":0},{\"element_id\":2,\"chapter_id\":6,\"display_id\":2,\"element_type\":1,\"sb_type\":1,\"element_name\":\"Geothermal Energy\",\"element_is_deleted\":0},{\"element_id\":3,\"chapter_id\":6,\"display_id\":3,\"element_type\":1,\"sb_type\":2,\"element_name\":\"Nonlinear Dynamics\",\"element_is_deleted\":0},{\"element_id\":4,\"chapter_id\":6,\"display_id\":4,\"element_type\":1,\"sb_type\":1,\"element_name\":\"Chaos in Classical and Quantum Systems\",\"element_is_deleted\":0}],\"chapters\":[{\"chapter_id\":1,\"chapter_name\":\"Electric Charge and Field\",\"display_id\":1,\"deleted\":0},{\"chapter_id\":2,\"chapter_name\":\"Friction\",\"display_id\":2,\"deleted\":0},{\"chapter_id\":3,\"chapter_name\":\"Force\",\"display_id\":3,\"deleted\":0},{\"chapter_id\":4,\"chapter_name\":\"Gravity\",\"display_id\":4,\"deleted\":0},{\"chapter_id\":5,\"chapter_name\":\"Laws of Motion\",\"display_id\":5,\"deleted\":0},{\"chapter_id\":6,\"chapter_name\":\"Space Exploration\",\"display_id\":6,\"deleted\":0}]}}";               GetElementProgressOutput output = GetElementProgressOutput.fromJson(jsonString);
                insertElementProgressIntoDb(output);
                if (t instanceof SocketTimeoutException) {
                    apiResponse.postValue(Resource.error("", new ApiStatusResponse(Constants.STATUS_CODE_TIMEOUT, context.getResources().getString(com.base.skillbuilderapi.R.string.connection_timeout_msg))));
                } else if (t instanceof IllegalStateException) {
                    apiResponse.postValue(Resource.error("", new ApiStatusResponse(-1, context.getResources().getString(R.string.connection_illegal_state_exception_msg))));
                } else {
                    apiResponse.postValue(Resource.error("", new ApiStatusResponse(-1, t.getMessage())));
                }


//                if (t instanceof RetrofitClient.NoConnectivityException) {
                // No internet connection
//                    apiResponse.postValue(Resource.error("", new GetElementProgressOutput(Constants.STATUS_CODE_CONNECTIVITY_ISSUE, applicationContext.getResources().getString(R.string.connection_error_msg))));

            }
        });
        return apiResponse;
    }


    public void insertElementProgressIntoDb(GetElementProgressOutput output) {

        List<ElementProgress> elementProgresses = output.getData().getElementProgress();
        List<Element> elements = output.getData().getElements();
        List<Chapter> chapters = output.getData().getChapters();
        Handler handler = new Handler();
        ExecutorService databaseWriteExecutor =
                Executors.newSingleThreadExecutor();
        databaseWriteExecutor.execute(() -> {
            for (Chapter chapter : chapters) {
                ChapterEntity chapterEntity = new ChapterEntity(chapter.getChapterId(), chapter.getChapterName(), chapter.getDisplayId(), chapter.getDeleted());
                chapterDao.insert(chapterEntity);
            }
            for (Element element : elements) {
                ElementEntity elementEntity = new ElementEntity(element.getElementId(), element.getChapterId(), element.getDisplayId(), element.getElementType(), element.getSbType(), element.getElementName(), element.getElementIsDeleted());
                elementDao.insert(elementEntity);
            }
            handler.post(() -> {
                databaseWriteExecutor.execute(() -> {
                    for (ElementProgress elementProgress : elementProgresses) {
                        elementDao.updateElementProgress(elementProgress.getElementId(), elementProgress.getChapterId(), elementProgress.getUserName(), elementProgress.getMilestoneLevel(), elementProgress.getMilestoneDate(), elementProgress.getCurrentProgress(), elementProgress.getMaxStar(), elementProgress.getCertificateEarned(), elementProgress.getCertificateDate());
                    }
                });
            });
        });

//            for (ElementProgress elementProgress : elementProgresses) {
//                ElementProgressEntity elementProgressEntity = new ElementProgressEntity(elementProgress.getChapterId(), elementProgress.getElementId(), elementProgress.getElementType(), elementProgress.getUserName(), elementProgress.getMilestoneLevel(), elementProgress.getMilestoneDate(), elementProgress.getCurrentProgress(), elementProgress.getMaxStar(), elementProgress.getCertificateEarned(), elementProgress.getCertificateDate());
//                elementProgressDao.insert(elementProgressEntity);
//            }
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

    public LiveData<List<ChapterElementList>> getChapterElementList() {
        return chapterDao.getChapterElementList();
    }

}

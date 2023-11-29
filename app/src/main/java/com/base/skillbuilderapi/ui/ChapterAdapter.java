package com.base.skillbuilderapi.ui;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.base.skillbuilderapi.R;
import com.base.skillbuilderapi.entity.ChapterEntity;
import com.base.skillbuilderapi.entity.ElementEntity;
import com.base.skillbuilderapi.entity.ElementProgressEntity;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;
import java.util.List;

public class ChapterAdapter extends RecyclerView.Adapter<ChapterAdapter.ViewHolder> {
    private List<ElementEntity> elementLists;
    private List<ElementProgressEntity> progressLists;
    private List<ChapterEntity> chapterLists;
    private OnItemClickListener onItemClickListener;
    public interface OnItemClickListener {
        void onItemClick(ElementProgressEntity progressEntity, ElementEntity elementEntity, ChapterEntity chapterEntity);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    public ChapterAdapter(List<ElementProgressEntity> progressLists, List<ElementEntity> elementLists, List<ChapterEntity> chapterLists ) {
        this.progressLists = progressLists;
        this.elementLists = elementLists;
        this.chapterLists = chapterLists;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chapter_cards, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ChapterEntity chapter = chapterLists.get(position);
        holder.tvChapterName.setText(chapter.getChapterName());

        List<ElementProgressEntity> progressList = getProgressListForChapter(chapter.getChapterId());
        if (progressList != null && !progressList.isEmpty()) {
            List<ElementEntity> filteredElementList = getElementListForChapter(chapter.getChapterId());
            List<ChapterEntity> filteredChapterList = getChapterListForChapter(chapter.getChapterId());
            Log.d("Chapter Adapter", "Checking in chapter adapter" + elementLists);
            ProgressAdapter progressAdapter = new ProgressAdapter(progressList, filteredElementList, filteredChapterList);
            progressAdapter.setOnItemClickListener((progressEntity, elementEntity, chapterEntity) -> {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(progressEntity, elementEntity, chapterEntity);
                }
            });
            holder.rvImages.setLayoutManager(new GridLayoutManager(holder.itemView.getContext(), 4));
            holder.rvImages.setAdapter(progressAdapter);
        }
    }

    private List<ElementProgressEntity> getProgressListForChapter(int chapterId) {
        List<ElementProgressEntity> filteredList = new ArrayList<>();

        for (ElementProgressEntity progressEntity : progressLists) {
            for (ElementEntity elementEntity : elementLists) {
                if (progressEntity.getChapterId() == chapterId && elementEntity.getChapterId() == chapterId) {
                    filteredList.add(progressEntity);
                }
            }
        }
        return filteredList;
    }
    private List<ElementEntity> getElementListForChapter(int chapterId) {
        List<ElementEntity> filteredList = new ArrayList<>();
        for (ElementEntity elementEntity : elementLists) {
            if (elementEntity.getChapterId() == chapterId) {
                filteredList.add(elementEntity);
            }
        }
        return filteredList;
    }

    private List<ChapterEntity> getChapterListForChapter(int chapterId) {
        List<ChapterEntity> filteredList = new ArrayList<>();
        for (ChapterEntity chapterEntity : chapterLists) {
            if (chapterEntity.getChapterId() == chapterId) {
                filteredList.add(chapterEntity);
            }
        }
        return filteredList;
    }
    @Override
    public int getItemCount() {
        return chapterLists.size() != 0 ? chapterLists.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        MaterialTextView tvChapterName;
        RecyclerView rvImages;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvChapterName = itemView.findViewById(R.id.tv_chapterName);
            rvImages = itemView.findViewById(R.id.rv_images);
        }
    }
}

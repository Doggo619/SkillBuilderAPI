package com.base.skillbuilderapi;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;
import java.util.List;

public class ChapterAdapter extends RecyclerView.Adapter<ChapterAdapter.ViewHolder> {
    private List<ElementEntity> elementLists;
    private List<ElementProgressEntity> progressLists;
    private List<ChapterEntity> chapterLists;
    private OnItemClickListener onItemClickListener;
    public interface OnItemClickListener {
        void onItemClick(ElementProgressEntity progressEntity, String chapterName);
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
        ElementEntity chapter = chapterList.get(position);
        holder.tvChapterName.setText(chapter.getChapterName());

        List<ElementProgressEntity> progressList = getProgressListForChapter(chapter.getChapterId());
        if (progressList != null && !progressList.isEmpty()) {
            ProgressAdapter progressAdapter = new ProgressAdapter(progressList);
            holder.rvImages.setLayoutManager(new GridLayoutManager(holder.itemView.getContext(), 4));
            holder.rvImages.setAdapter(progressAdapter);
            progressAdapter.setOnItemClickListener((progressEntity) -> {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(progressEntity, chapter.getChapterName());
                }
            });
            Log.d("ChapterAdapter", "ProgressAdapter set for chapter: " + chapter.getChapterName());
        } else {
            Log.d("ChapterAdapter", "ProgressList is null or empty for chapter: " + chapter.getChapterName());
        }
        holder.itemView.setOnClickListener(view -> {
            if (onItemClickListener != null) {
                int adapterPosition = holder.getAdapterPosition();
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    List<ElementProgressEntity> progressLists = getProgressListForChapter(chapterList.get(adapterPosition).getChapterId());
                    if (!progressLists.isEmpty()) {
                        onItemClickListener.onItemClick(progressLists.get(0), chapterList.get(adapterPosition).getChapterName());
                    }
                }
            }
        });

    }
    private List<ElementProgressEntity> getProgressListForChapter(int chapterId) {
        List<ElementProgressEntity> filteredList = new ArrayList<>();

        for (ElementProgressEntity progressEntity : progressLists) {
            if (progressEntity.getChapterId() == chapterId) {
                filteredList.add(progressEntity);
            }
        }
        return filteredList;
    }

    @Override
    public int getItemCount() {
        return chapterList.size() != 0 ? chapterList.size() : 0;
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

package com.base.skillbuilderapi.ui;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.base.skillbuilderapi.R;
import com.base.skillbuilderapi.entity.ChapterEntity;
import com.base.skillbuilderapi.entity.ElementEntity;
import com.base.skillbuilderapi.entity.ElementProgressEntity;
import com.base.skillbuilderapi.model.elementProgressList.ChapterList;
import com.google.android.material.textview.MaterialTextView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ChapterAdapter extends ListAdapter<ChapterList, ChapterAdapter.ViewHolder> {
    private List<ElementEntity> elementLists;
    private List<ElementProgressEntity> progressLists;
    private List<ChapterEntity> chapterLists;
    private OnItemClickListener onItemClickListener;
    private Context context;
    public interface OnItemClickListener {
        void onItemClick(ElementProgressEntity progressEntity, ElementEntity elementEntity, ChapterEntity chapterEntity);
    }


    private static final DiffUtil.ItemCallback<ChapterList> STAT_ITEM_CALLBACK = new DiffUtil.ItemCallback<ChapterList>() {
        @Override
        public boolean areItemsTheSame(@NonNull @NotNull ChapterList oldItem, @NonNull @NotNull ChapterList newItem) {
            return oldItem.getChapterId() == newItem.getChapterId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull @NotNull ChapterList oldItem, @NonNull @NotNull ChapterList newItem) {
            return oldItem.equals(newItem);
        }
    };

    protected ChapterAdapter(Context context, OnItemClickListener onItemClickListener) {
        super(STAT_ITEM_CALLBACK);
        this.onItemClickListener = onItemClickListener;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chapter_cards, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        ChapterEntity chapter = chapterLists.get(position);
        ChapterList chapter = getItem(position);
        holder.tvChapterName.setText(chapter.getChapterName());

////        List<ElementProgressEntity> progressList = getProgressListForChapter(chapter.getChapterId());
////        if (progressList != null && !progressList.isEmpty()) {
//            List<ElementEntity> filteredElementList = getElementListForChapter(chapter.getChapterId());
//            List<ChapterEntity> filteredChapterList = getChapterListForChapter(chapter.getChapterId());
//            Log.d("Chapter Adapter", "Checking in chapter adapter" + elementLists);
//            ProgressAdapter progressAdapter = new ProgressAdapter(progressList, filteredElementList, filteredChapterList);
//            progressAdapter.setOnItemClickListener((progressEntity, elementEntity, chapterEntity) -> {
//                if (onItemClickListener != null) {
//                    onItemClickListener.onItemClick(progressEntity, elementEntity, chapterEntity);
//                }
//            });
//            holder.rvImages.setLayoutManager(new GridLayoutManager(holder.itemView.getContext(), 4));
//            holder.rvImages.setAdapter(progressAdapter);
////        }
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
//    @Override
//    public int getItemCount() {
//        return chapterLists.size() != 0 ? chapterLists.size() : 0;
//    }

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

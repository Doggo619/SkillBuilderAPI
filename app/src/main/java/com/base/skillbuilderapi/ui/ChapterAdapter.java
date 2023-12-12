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
import com.base.skillbuilderapi.model.elementProgressList.ElementProgressList;
import com.google.android.material.textview.MaterialTextView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ChapterAdapter extends ListAdapter<ChapterList, ChapterAdapter.ViewHolder> {
    private OnItemClickListener onItemClickListener;
    private Context context;

    public interface OnItemClickListener {
        void onItemClick(ElementProgressList elementProgressList, String chapterName);
    }


    private static final DiffUtil.ItemCallback<ChapterList> CHAPTER_ITEM_CALLBACK = new DiffUtil.ItemCallback<ChapterList>() {
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
        super(CHAPTER_ITEM_CALLBACK);
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
        ChapterList chapter = getItem(position);
        holder.tvChapterName.setText(chapter.getChapterName());

        ProgressAdapter.OnItemClickListener listener = new ProgressAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ElementProgressList elementProgressList) {
                onItemClickListener.onItemClick(elementProgressList, chapter.getChapterName());
            }
        };
        ProgressAdapter progressAdapter = new ProgressAdapter(context, listener);
        holder.rvImages.setLayoutManager(new GridLayoutManager(holder.itemView.getContext(), 4));
        holder.rvImages.setAdapter(progressAdapter);
        Log.d("Chaper Adapter", "Checking list1" + chapter.getElementProgressList().size());
        if (!chapter.getElementProgressList().isEmpty()) {
            Log.d("Chaper Adapter", "Checking list2" + chapter.getElementProgressList().size());
            progressAdapter.submitList(chapter.getElementProgressList());
        }
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

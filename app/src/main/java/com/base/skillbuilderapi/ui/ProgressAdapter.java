package com.base.skillbuilderapi.ui;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.base.skillbuilderapi.R;
import com.base.skillbuilderapi.entity.ChapterEntity;
import com.base.skillbuilderapi.entity.ElementEntity;
import com.base.skillbuilderapi.entity.ElementProgressEntity;

import java.util.List;

public class ProgressAdapter extends RecyclerView.Adapter<ProgressAdapter.ViewHolder> {
    private List<ElementProgressEntity> progressEntityList;
    private List<ElementEntity> elementEntityList;
    private List<ChapterEntity> chapterEntityList;
    private OnItemClickListener onItemClickListener;
    public ProgressAdapter(List<ElementProgressEntity> progressEntityList, List<ElementEntity> elementEntityList, List<ChapterEntity> chapterEntityList) {
        Log.d("Progress Adapter", "Checking in progress adapter" + elementEntityList);
        this.progressEntityList = progressEntityList;
        this.elementEntityList = elementEntityList;
        this.chapterEntityList = chapterEntityList;
    }
    public interface OnItemClickListener {
        void onItemClick(ElementProgressEntity progressEntity, ElementEntity elementEntity, ChapterEntity chapterEntity);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.elements_list, parent, false);
        return new ViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (position <= progressEntityList.size() && position <= elementEntityList.size() && position <= chapterEntityList.size()) {

            ElementProgressEntity progressEntity = progressEntityList.get(position);

            int milestoneLevel = progressEntity.getMilestoneLevel();
            switch (milestoneLevel) {
                case 0:
                    holder.imageView.setImageResource(R.drawable.milestone_0);
                    holder.ivTick.setVisibility(View.GONE);
                    break;
                case 1:
                    holder.imageView.setImageResource(R.drawable.milestone_1);
                    holder.ivTick.setVisibility(View.GONE);
                    break;
                case 2:
                    holder.imageView.setImageResource(R.drawable.milestone_2);
                    holder.ivTick.setVisibility(View.GONE);
                    break;
                case 3:
                    holder.imageView.setImageResource(R.drawable.milestone_3);
                    holder.ivTick.setVisibility(View.GONE);
                    break;
                case 4:
                    holder.imageView.setImageResource(R.drawable.milestone_4);
                    holder.ivTick.setVisibility(View.VISIBLE);
                    break;
                default:
                    holder.imageView.setImageResource(R.drawable.ic_baseline_share_24);
                    break;
            }

            ElementEntity elementEntity = elementEntityList.get(position);

            if (elementEntity.getSbType() > 1) {
                int maxStar = progressEntity.getMaxStar();
                switch (maxStar) {
                    case 0:
                        holder.ivStar.setImageResource(R.drawable.zerostar);
                        break;
                    case 1:
                        holder.ivStar.setImageResource(R.drawable.onestar);
                        break;
                    case 2:
                        holder.ivStar.setImageResource(R.drawable.twostar);
                        break;
                    case 3:
                        holder.ivStar.setImageResource(R.drawable.threestar);
                        break;
                    default:
                        holder.ivStar.setImageResource(R.drawable.ic_baseline_share_24);
                        break;
                }
            }
        }
        holder.itemView.setOnClickListener(view -> {
            int adapterPosition = holder.getAdapterPosition();
            if (adapterPosition != RecyclerView.NO_POSITION && onItemClickListener != null) {
                onItemClickListener.onItemClick(progressEntityList.get(adapterPosition), elementEntityList.get(adapterPosition), chapterEntityList.get(adapterPosition));
            }
        });
    }

    @Override
    public int getItemCount() {
        return progressEntityList!= null ? progressEntityList.size() : 0;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView, ivStar, ivTick;
        ProgressAdapter adapter;
        public ViewHolder(@NonNull View itemView, ProgressAdapter adapter) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_milestone);
            ivStar = itemView.findViewById(R.id.iv_star);
            ivTick = itemView.findViewById(R.id.iv_tick);
            this.adapter = adapter;
        }
    }
}

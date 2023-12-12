package com.base.skillbuilderapi.ui;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.base.skillbuilderapi.R;
import com.base.skillbuilderapi.entity.ChapterEntity;
import com.base.skillbuilderapi.entity.ElementEntity;
import com.base.skillbuilderapi.entity.ElementProgressEntity;
import com.base.skillbuilderapi.model.elementProgressList.ChapterList;
import com.base.skillbuilderapi.model.elementProgressList.ElementProgressList;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ProgressAdapter extends ListAdapter<ElementProgressList, ProgressAdapter.ViewHolder> {
    private OnItemClickListener onItemClickListener;
    Context context;
    private static final DiffUtil.ItemCallback<ElementProgressList> ELEMENT_ITEM_CALLBACK = new DiffUtil.ItemCallback<ElementProgressList>() {
        @Override
        public boolean areItemsTheSame(@NonNull @NotNull ElementProgressList oldItem, @NonNull @NotNull ElementProgressList newItem) {
            return oldItem.getChapterId() == newItem.getChapterId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull @NotNull ElementProgressList oldItem, @NonNull @NotNull ElementProgressList newItem) {
            return oldItem.equals(newItem);
        }
    };

    protected ProgressAdapter(Context context, OnItemClickListener onItemClickListener) {
        super(ELEMENT_ITEM_CALLBACK);
        this.onItemClickListener = onItemClickListener;
        this.context = context;
    }
    public interface OnItemClickListener {
        void onItemClick(ElementProgressList elementProgressList);
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.elements_list, parent, false);
        return new ViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ElementProgressList elementProgressList = getItem(position);
        if (elementProgressList.getElementId() != 0) {
            Log.d("Progres Adapter", "Element Progress list1" + elementProgressList.toJson());

            int milestoneLevel = elementProgressList.getMilestoneLevel();
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


            if (elementProgressList.getSbType() > 1) {
                int maxStar = elementProgressList.getMaxStar();
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
            holder.itemView.setOnClickListener(view -> {
                int adapterPosition = holder.getAdapterPosition();
                if (adapterPosition != RecyclerView.NO_POSITION && onItemClickListener != null) {
                    onItemClickListener.onItemClick(elementProgressList);
                }
            });
        } else {
            holder.itemView.setVisibility(View.GONE);
        }
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

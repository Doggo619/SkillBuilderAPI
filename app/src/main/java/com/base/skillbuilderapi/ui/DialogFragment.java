package com.base.skillbuilderapi.ui;

import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.base.skillbuilderapi.R;
import com.base.skillbuilderapi.entity.ChapterEntity;
import com.base.skillbuilderapi.entity.ElementEntity;
import com.base.skillbuilderapi.entity.ElementProgressEntity;
import com.base.skillbuilderapi.model.elementProgressList.ElementProgressList;
import com.google.android.material.textview.MaterialTextView;

import java.util.Date;

public class DialogFragment extends androidx.fragment.app.DialogFragment {
    MaterialTextView date, chapterName, userName, currentProgress, elementName;
    ImageView milestone, tick;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.dialog_fragment, container, false);

        date = view.findViewById(R.id.tv_date);
        chapterName = view.findViewById(R.id.tv_chapterName);
        userName = view.findViewById(R.id.tv_userName);
        currentProgress = view.findViewById(R.id.tv_currentProgress);
        elementName = view.findViewById(R.id.tv_elementName);
        milestone = view.findViewById(R.id.iv_milestone);
        tick = view.findViewById(R.id.iv_tick);

        Bundle args = getArguments();
        if (args != null) {

            String elementProgressStr = args.getString("ElementProgressList");
            String chapterNameStr = args.getString("chapterName");
            ElementProgressList elementProgress = ElementProgressList.fromJson(elementProgressStr);

            long dateFormat = elementProgress.getCertificateDate();
            Date date1 = new Date(dateFormat * 1000);
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                SimpleDateFormat sdf =  new SimpleDateFormat("dd MMM yyyy");
                String formatedDate = sdf.format(date1);
                date.setText(formatedDate);
            }

            chapterName.setText(chapterNameStr);
            userName.setText(elementProgress.getUserName());
            currentProgress.setText("has completed " + elementProgress.getCurrentProgress() + "% of the Skill Builder on");
            elementName.setText(elementProgress.getElementName());
            int milestoneLevel = elementProgress.getMilestoneLevel();
            switch (milestoneLevel) {
                case 0:
                    milestone.setImageResource(R.drawable.milestone_0);
                    tick.setVisibility(View.GONE);
                    break;
                case 1:
                    milestone.setImageResource(R.drawable.milestone_1);
                    tick.setVisibility(View.GONE);
                    break;
                case 2:
                    milestone.setImageResource(R.drawable.milestone_2);
                    tick.setVisibility(View.GONE);
                    break;
                case 3:
                    milestone.setImageResource(R.drawable.milestone_3);
                    tick.setVisibility(View.GONE);
                    break;
                case 4:
                    milestone.setImageResource(R.drawable.milestone_4);
                    tick.setVisibility(View.VISIBLE);
                    break;
                default:
                    milestone.setImageResource(R.drawable.ic_baseline_share_24);
                    break;
            }
        }

        return view;
    }
}

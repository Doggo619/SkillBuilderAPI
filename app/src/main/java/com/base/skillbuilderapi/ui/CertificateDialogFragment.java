package com.base.skillbuilderapi.ui;

import android.app.Dialog;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.utils.widget.ImageFilterView;

import com.base.skillbuilderapi.PicassoCircleTransformation;
import com.base.skillbuilderapi.R;
import com.base.skillbuilderapi.model.elementProgressList.ElementProgressList;
import com.google.android.material.textview.MaterialTextView;
import com.squareup.picasso.Picasso;

import java.util.Date;

public class CertificateDialogFragment extends DialogFragment {
    MaterialTextView userName, elementName, chapterName, date;
    ImageView profilePicture;
    ImageFilterView closeIcon;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_certificate_dialog, container, false);


        userName = view.findViewById(R.id.tvUserName);
        elementName = view.findViewById(R.id.tvElementName);
        chapterName = view.findViewById(R.id.tvChapterName);
        date = view.findViewById(R.id.tvDate);
        profilePicture = view.findViewById(R.id.ivProfilePicture);
        closeIcon = view.findViewById(R.id.ivClose);

        closeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        Picasso.get()
                .load("https://sm.ign.com/ign_nordic/cover/a/avatar-gen/avatar-generations_prsz.jpg")
                .error(R.drawable.ic_baseline_share_24)
                .transform(new PicassoCircleTransformation())
                .into(profilePicture);

        Bundle args = getArguments();
        if (args != null) {

            String elementProgressStr = args.getString("ElementProgressList");
            String chapterNameStr = args.getString("chapterName");
            ElementProgressList elementProgress = ElementProgressList.fromJson(elementProgressStr);


            long dateFormat = elementProgress.getCertificateDate();
            Date date1 = new Date(dateFormat * 1000);
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
                String formatedDate = sdf.format(date1);
                date.setText(formatedDate);
            }
            chapterName.setText(chapterNameStr);
            userName.setText(elementProgress.getUserName());
            elementName.setText(elementProgress.getElementName());
        }
        requireDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getDialog().setCancelable(false);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            if (dialog != null) {
                int width = ViewGroup.LayoutParams.MATCH_PARENT;
                int height = ViewGroup.LayoutParams.WRAP_CONTENT;
                dialog.getWindow().setLayout(width, height);
            }
        } else {
            if (dialog != null) {
                int width = (int) (getResources().getDisplayMetrics().widthPixels * 0.50);
                int height = ViewGroup.LayoutParams.WRAP_CONTENT;
                dialog.getWindow().setLayout(width, height);
            }
        }
    }
}

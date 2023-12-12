package com.base.skillbuilderapi.ui;

import android.app.Dialog;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.utils.widget.ImageFilterView;

import com.base.skillbuilderapi.utils.DownloadImage;
import com.base.skillbuilderapi.utils.PicassoCircleTransformation;
import com.base.skillbuilderapi.R;
import com.base.skillbuilderapi.model.elementProgressList.ElementProgressList;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textview.MaterialTextView;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.Date;
import java.util.Locale;

public class CertificateDialogFragment extends DialogFragment {
    MaterialTextView userName, elementName, chapterName, date, congratsMessage, certificateName, certificateOfDash, successfully, andCompleted, skillBuilder;
    ImageView profilePicture, border, certificateLogo, mainCertificateLogo, star;
    ImageFilterView closeIcon;
    MaterialButton share, download;
    MaterialCardView certificateCard;
    private static final int REQUEST_CODE = 1000;



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
        border = view.findViewById(R.id.ivBorderImage);
        certificateLogo = view.findViewById(R.id.ivCertificateLogo);
        mainCertificateLogo = view.findViewById(R.id.ivMainCertificateLogo);
        congratsMessage = view.findViewById(R.id.tvCongratsMessage);
        certificateName = view.findViewById(R.id.tvCertificate);
        certificateOfDash = view.findViewById(R.id.tvCertificateOfCompletion);
        star = view.findViewById(R.id.ivStar);
        successfully = view.findViewById(R.id.tvBody);
        andCompleted = view.findViewById(R.id.tvAndCompleted);
        skillBuilder = view.findViewById(R.id.tvCompleted);
        share = view.findViewById(R.id.btnShareCertificate);
        download = view.findViewById(R.id.btnDownloadCertificate);
        certificateCard = view.findViewById(R.id.certificateCard);

        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downloadImage();
            }
        });

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

            int maxStar = elementProgress.getMaxStar();

            if (elementProgress.getCertificateEarned() == 4001) {
                border.setImageResource(R.drawable.certificate_green);
                certificateLogo.setImageResource(R.drawable.ic_certificate_icon_green);
                mainCertificateLogo.setImageResource(R.drawable.ic_certificate_icon_green);
                congratsMessage.setText(getResources().getText(R.string.certificate_congrats_message_with_vowels_proactiveLearner));
                certificateName.setText(getResources().getText(R.string.certificate_of_achievement_proactiveLearner));
                certificateName.setTextColor(getResources().getColor(R.color.green));
                certificateOfDash.setText(getResources().getText(R.string.certificate_of_completion));
                star.setVisibility(View.GONE);
                userName.setTextColor(getResources().getColor(R.color.green));
                successfully.setText(getResources().getText(R.string.successfully_completed));
                andCompleted.setVisibility(View.GONE);
                skillBuilder.setText(getResources().getString(R.string.current_progress, elementProgress.getCurrentProgress()));
            } else if (elementProgress.getCertificateEarned() == 4002) {
                border.setImageResource(R.drawable.certificate_orange);
                certificateLogo.setImageResource(R.drawable.ic_certificate_icon_orange);
                mainCertificateLogo.setImageResource(R.drawable.ic_certificate_icon_orange);
                congratsMessage.setText(getResources().getText(R.string.certificate_congrats_message_with_vowels_goodPerformer));
                certificateName.setText(getResources().getText(R.string.certificate_of_achievement_goodPerformer));
                certificateName.setTextColor(getResources().getColor(R.color.orange));
                certificateOfDash.setText(getResources().getText(R.string.certificate_of_achievement));
                if (elementProgress.getSbType() > 1) {
                    star.setVisibility(View.VISIBLE);
                    switch (maxStar) {
                        case 0:
                            star.setImageResource(R.drawable.zerostar);
                            break;
                        case 1:
                            star.setImageResource(R.drawable.onestar);
                            break;
                        case 2:
                            star.setImageResource(R.drawable.twostar);
                            break;
                        case 3:
                            star.setImageResource(R.drawable.threestar);
                            break;
                        default:
                            star.setImageResource(R.drawable.ic_baseline_share_24);
                            break;
                    }
                }
                userName.setTextColor(getResources().getColor(R.color.orange));
                successfully.setText(getResources().getString(R.string.successfully_won, maxStar));
                andCompleted.setVisibility(View.VISIBLE);
                skillBuilder.setText(getResources().getString(R.string.current_progress, elementProgress.getCurrentProgress()));
            } else {
                border.setImageResource(R.drawable.certificate_blue);
                certificateLogo.setImageResource(R.drawable.ic_certificate_icon_blue);
                mainCertificateLogo.setImageResource(R.drawable.ic_certificate_icon_blue);
                congratsMessage.setText(getResources().getText(R.string.certificate_congrats_message_with_vowels_starStudent));
                certificateName.setText(getResources().getText(R.string.certificate_of_achievement_starStudent));
                certificateName.setTextColor(getResources().getColor(R.color.blue));
                certificateOfDash.setText(getResources().getText(R.string.certificate_of_achievement));
                if (elementProgress.getSbType() > 1) {
                    star.setVisibility(View.VISIBLE);
                    switch (maxStar) {
                        case 0:
                            star.setImageResource(R.drawable.zerostar);
                            break;
                        case 1:
                            star.setImageResource(R.drawable.onestar);
                            break;
                        case 2:
                            star.setImageResource(R.drawable.twostar);
                            break;
                        case 3:
                            star.setImageResource(R.drawable.threestar);
                            break;
                        default:
                            star.setImageResource(R.drawable.ic_baseline_share_24);
                            break;
                    }
                }
                userName.setTextColor(getResources().getColor(R.color.blue));
                successfully.setText(getResources().getString(R.string.successfully_won, maxStar));
                andCompleted.setVisibility(View.VISIBLE);
                skillBuilder.setText(getResources().getString(R.string.current_progress, elementProgress.getCurrentProgress()));
            }


            long dateFormat = elementProgress.getCertificateDate();

            Date date1 = new Date(dateFormat * 1000);
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
                String formattedDate = sdf.format(date1);
                date.setText(formattedDate);
            }
            chapterName.setText(chapterNameStr);
            userName.setText(elementProgress.getUserName());
            elementName.setText(elementProgress.getElementName());
        }
        requireDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getDialog().setCancelable(false);
        return view;
    }

    private void downloadImage() {
        Bitmap certificateBitmap = DownloadImage.viewToBitmap(certificateCard);
        String timestamp = new java.text.SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        String fileName = "MathZap_Certificate_" + timestamp + ".jpg";
        String filePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + File.separator + fileName;
        DownloadImage.saveBitmapAsJpeg(certificateBitmap, filePath);
        Toast.makeText(requireContext(), "Certificate downloaded successfully", Toast.LENGTH_SHORT).show();
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

package com.base.skillbuilderapi.model.elementProgress;

import com.google.gson.annotations.SerializedName;

public class ElementProgress {
    @SerializedName("chapter_id")
    private int chapterId;
    @SerializedName("element_id")
    private int elementId;
    @SerializedName("element_type")
    private int elementType;
    @SerializedName("user_name")
    private String userName;
    @SerializedName("milestone_level")
    private int milestoneLevel;
    @SerializedName("milestone_date")
    private long milestoneDate;
    @SerializedName("current_progress")
    private int currentProgress;
    @SerializedName("max_star")
    private int maxStar;
    @SerializedName("certificate_earned")
    private int certificateEarned;
    @SerializedName("certificate_date")
    private long certificateDate;

    public ElementProgress(int chapterId, int elementId, int elementType, String userName, int milestoneLevel, long milestoneDate, int currentProgress, int maxStar, int certificateEarned, long certificateDate) {
        this.chapterId = chapterId;
        this.elementId = elementId;
        this.elementType = elementType;
        this.userName = userName;
        this.milestoneLevel = milestoneLevel;
        this.milestoneDate = milestoneDate;
        this.currentProgress = currentProgress;
        this.maxStar = maxStar;
        this.certificateEarned = certificateEarned;
        this.certificateDate = certificateDate;
    }

    public int getChapterId() {
        return chapterId;
    }

    public void setChapterId(int chapterId) {
        this.chapterId = chapterId;
    }

    public int getElementId() {
        return elementId;
    }

    public void setElementId(int elementId) {
        this.elementId = elementId;
    }

    public int getElementType() {
        return elementType;
    }

    public void setElementType(int elementType) {
        this.elementType = elementType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getMilestoneLevel() {
        return milestoneLevel;
    }

    public void setMilestoneLevel(int milestoneLevel) {
        this.milestoneLevel = milestoneLevel;
    }

    public long getMilestoneDate() {
        return milestoneDate;
    }

    public void setMilestoneDate(long milestoneDate) {
        this.milestoneDate = milestoneDate;
    }

    public int getCurrentProgress() {
        return currentProgress;
    }

    public void setCurrentProgress(int currentProgress) {
        this.currentProgress = currentProgress;
    }

    public int getMaxStar() {
        return maxStar;
    }

    public void setMaxStar(int maxStar) {
        this.maxStar = maxStar;
    }

    public int getCertificateEarned() {
        return certificateEarned;
    }

    public void setCertificateEarned(int certificateEarned) {
        this.certificateEarned = certificateEarned;
    }

    public long getCertificateDate() {
        return certificateDate;
    }

    public void setCertificateDate(long certificateDate) {
        this.certificateDate = certificateDate;
    }
}

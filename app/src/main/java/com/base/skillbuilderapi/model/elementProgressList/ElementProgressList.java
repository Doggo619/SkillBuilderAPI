package com.base.skillbuilderapi.model.elementProgressList;

import com.base.skillbuilderapi.model.elementProgressApi.GetElementProgressOutput;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class ElementProgressList {
    @SerializedName("element_id")
    private int elementId;
    @SerializedName("chapter_id")
    private int chapterId;
    @SerializedName("display_id")
    private int displayId;
    @SerializedName("element_type")
    private int elementType;
    @SerializedName("sb_type")
    private int sbType;
    @SerializedName("element_name")
    private String elementName;
    @SerializedName("element_is_deleted")
    private int elementIsDeleted;
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

//    public ElementProgressList(int elementId, int chapterId, int displayId, int elementType, int sbType, String elementName, int elementIsDeleted, String userName, int milestoneLevel, long milestoneDate, int currentProgress, int maxStar, int certificateEarned, long certificateDate) {
//        this.elementId = elementId;
//        this.chapterId = chapterId;
//        this.displayId = displayId;
//        this.elementType = elementType;
//        this.sbType = sbType;
//        this.elementName = elementName;
//        this.elementIsDeleted = elementIsDeleted;
//        this.userName = userName;
//        this.milestoneLevel = milestoneLevel;
//        this.milestoneDate = milestoneDate;
//        this.currentProgress = currentProgress;
//        this.maxStar = maxStar;
//        this.certificateEarned = certificateEarned;
//        this.certificateDate = certificateDate;
//    }

    public int getElementId() {
        return elementId;
    }

    public void setElementId(int elementId) {
        this.elementId = elementId;
    }

    public int getChapterId() {
        return chapterId;
    }

    public void setChapterId(int chapterId) {
        this.chapterId = chapterId;
    }

    public int getDisplayId() {
        return displayId;
    }

    public void setDisplayId(int displayId) {
        this.displayId = displayId;
    }

    public int getElementType() {
        return elementType;
    }

    public void setElementType(int elementType) {
        this.elementType = elementType;
    }

    public int getSbType() {
        return sbType;
    }

    public void setSbType(int sbType) {
        this.sbType = sbType;
    }

    public String getElementName() {
        return elementName;
    }

    public void setElementName(String elementName) {
        this.elementName = elementName;
    }

    public int getElementIsDeleted() {
        return elementIsDeleted;
    }

    public void setElementIsDeleted(int elementIsDeleted) {
        this.elementIsDeleted = elementIsDeleted;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ElementProgressList that = (ElementProgressList) o;
        return elementId == that.elementId && chapterId == that.chapterId && displayId == that.displayId && elementType == that.elementType && sbType == that.sbType && elementIsDeleted == that.elementIsDeleted && milestoneLevel == that.milestoneLevel && milestoneDate == that.milestoneDate && currentProgress == that.currentProgress && maxStar == that.maxStar && certificateEarned == that.certificateEarned && certificateDate == that.certificateDate && Objects.equals(elementName, that.elementName) && Objects.equals(userName, that.userName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(elementId, chapterId, displayId, elementType, sbType, elementName, elementIsDeleted, userName, milestoneLevel, milestoneDate, currentProgress, maxStar, certificateEarned, certificateDate);
    }
    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
    public static ElementProgressList fromJson(String jsonString) {
        return new Gson().fromJson(jsonString, ElementProgressList.class);
    }
}

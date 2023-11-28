package com.base.skillbuilderapi;

import com.google.gson.annotations.SerializedName;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "ELEMENT_PROGRESS", indices = {@Index(name = "INDEX_ELEMENT_PROGRESS", value = {"chapterId", "elementId", "elementType"},
        unique = true)})
public class ElementProgressEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    @Override
    public String toString() {
        return "ElementProgressEntity{" +
                "id=" + id +
                ", chapterId=" + chapterId +
                ", elementId=" + elementId +
                ", elementType=" + elementType +
                ", userName='" + userName + '\'' +
                ", milestoneLevel=" + milestoneLevel +
                ", milestoneDate=" + milestoneDate +
                ", currentProgress=" + currentProgress +
                ", maxStar=" + maxStar +
                ", certificateEarned=" + certificateEarned +
                ", certificateDate=" + certificateDate +
                '}';
    }
}

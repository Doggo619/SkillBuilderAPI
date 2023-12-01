package com.base.skillbuilderapi.entity;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "ELEMENT_PROGRESS", indices = {@Index(name = "INDEX_ELEMENT_PROGRESS", value = {"chapter_id", "element_id", "element_type"},
        unique = true)})
public class ElementProgressEntity implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "chapter_id")
    private int chapterId;
    @ColumnInfo(name = "element_id")
    private int elementId;
    @ColumnInfo(name ="element_type")
    private int elementType;
    @ColumnInfo(name ="user_name")
    private String userName;
    @ColumnInfo(name ="milestone_level")
    private int milestoneLevel;
    @ColumnInfo(name ="milestone_date")
    private long milestoneDate;
    @ColumnInfo(name ="current_progress")
    private int currentProgress;
    @ColumnInfo(name ="max_star")
    private int maxStar;
    @ColumnInfo(name ="certificate_earned")
    private int certificateEarned;
    @ColumnInfo(name ="certificate_date")
    private long certificateDate;

    public ElementProgressEntity(int chapterId, int elementId, int elementType, String userName, int milestoneLevel, long milestoneDate, int currentProgress, int maxStar, int certificateEarned, long certificateDate) {
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

    protected ElementProgressEntity(Parcel in) {
        id = in.readInt();
        chapterId = in.readInt();
        elementId = in.readInt();
        elementType = in.readInt();
        userName = in.readString();
        milestoneLevel = in.readInt();
        milestoneDate = in.readLong();
        currentProgress = in.readInt();
        maxStar = in.readInt();
        certificateEarned = in.readInt();
        certificateDate = in.readLong();
    }

    public static final Creator<ElementProgressEntity> CREATOR = new Creator<ElementProgressEntity>() {
        @Override
        public ElementProgressEntity createFromParcel(Parcel in) {
            return new ElementProgressEntity(in);
        }

        @Override
        public ElementProgressEntity[] newArray(int size) {
            return new ElementProgressEntity[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(chapterId);
        dest.writeInt(elementId);
        dest.writeInt(elementType);
        dest.writeString(userName);
        dest.writeInt(milestoneLevel);
        dest.writeLong(milestoneDate);
        dest.writeInt(currentProgress);
        dest.writeInt(maxStar);
        dest.writeInt(certificateEarned);
        dest.writeLong(certificateDate);
    }

}

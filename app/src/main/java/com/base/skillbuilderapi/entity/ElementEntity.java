package com.base.skillbuilderapi.entity;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "ELEMENT", indices = {@Index(name = "INDEX_ELEMENT", value = {"element_id", "chapter_id", "element_type"},
        unique = true)})
public class ElementEntity implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name ="element_id")
    private int elementId;
    @ColumnInfo(name ="chapter_id")
    private int chapterId;
    @ColumnInfo(name ="display_id")
    private int displayId;
    @ColumnInfo(name ="element_type")
    private int elementType;
    @ColumnInfo(name ="sb_type")
    private int sbType;
    @ColumnInfo(name ="element_name")
    private String elementName;
    @ColumnInfo(name ="element_is_deleted")
    private int elementIsDeleted;
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

    public ElementEntity(int elementId, int chapterId, int displayId, int elementType, int sbType, String elementName, int elementIsDeleted) {
        this.elementId = elementId;
        this.chapterId = chapterId;
        this.displayId = displayId;
        this.elementType = elementType;
        this.sbType = sbType;
        this.elementName = elementName;
        this.elementIsDeleted = elementIsDeleted;
    }

    protected ElementEntity(Parcel in) {
        id = in.readInt();
        elementId = in.readInt();
        chapterId = in.readInt();
        displayId = in.readInt();
        elementType = in.readInt();
        sbType = in.readInt();
        elementName = in.readString();
        elementIsDeleted = in.readInt();
        userName = in.readString();
        milestoneLevel = in.readInt();
        milestoneDate = in.readLong();
        currentProgress = in.readInt();
        maxStar = in.readInt();
        certificateEarned = in.readInt();
        certificateDate = in.readLong();
    }

    public static final Creator<ElementEntity> CREATOR = new Creator<ElementEntity>() {
        @Override
        public ElementEntity createFromParcel(Parcel in) {
            return new ElementEntity(in);
        }

        @Override
        public ElementEntity[] newArray(int size) {
            return new ElementEntity[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(elementId);
        dest.writeInt(chapterId);
        dest.writeInt(displayId);
        dest.writeInt(elementType);
        dest.writeInt(sbType);
        dest.writeString(elementName);
        dest.writeInt(elementIsDeleted);
        dest.writeString(userName);
        dest.writeInt(milestoneLevel);
        dest.writeLong(milestoneDate);
        dest.writeInt(currentProgress);
        dest.writeInt(maxStar);
        dest.writeInt(certificateEarned);
        dest.writeLong(certificateDate);
    }
}

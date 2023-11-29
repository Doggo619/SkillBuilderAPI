package com.base.skillbuilderapi.entity;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "ELEMENT", indices = {@Index(name = "INDEX_ELEMENT", value = {"elementId", "chapterId", "elementType"},
        unique = true)})
public class ElementEntity implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    private int id;
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
    }

}

package com.base.skillbuilderapi;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "ELEMENT", indices = {@Index(name = "INDEX_ELEMENT", value = {"elementId", "chapterId", "elementType"},
        unique = true)})
public class ElementEntity {
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
}

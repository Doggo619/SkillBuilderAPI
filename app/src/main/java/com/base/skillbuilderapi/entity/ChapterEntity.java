package com.base.skillbuilderapi.entity;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;


@Entity(tableName = "CHAPTER", indices = {@Index(name = "INDEX_CHAPTER", value = {"chapter_id"},
        unique = true)})
public class ChapterEntity implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name ="chapter_id")
    private int chapterId;
    @ColumnInfo(name ="chapter_name")
    private String chapterName;
    @ColumnInfo(name ="display_id")
    private int displayId;
    @ColumnInfo(name ="deleted")
    private int deleted;

    public ChapterEntity(int chapterId, String chapterName, int displayId, int deleted) {
        this.chapterId = chapterId;
        this.chapterName = chapterName;
        this.displayId = displayId;
        this.deleted = deleted;
    }

    protected ChapterEntity(Parcel in) {
        id = in.readInt();
        chapterId = in.readInt();
        chapterName = in.readString();
        displayId = in.readInt();
        deleted = in.readInt();
    }

    public static final Creator<ChapterEntity> CREATOR = new Creator<ChapterEntity>() {
        @Override
        public ChapterEntity createFromParcel(Parcel in) {
            return new ChapterEntity(in);
        }

        @Override
        public ChapterEntity[] newArray(int size) {
            return new ChapterEntity[size];
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

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public int getDisplayId() {
        return displayId;
    }

    public void setDisplayId(int displayId) {
        this.displayId = displayId;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(chapterId);
        dest.writeString(chapterName);
        dest.writeInt(displayId);
        dest.writeInt(deleted);
    }

}

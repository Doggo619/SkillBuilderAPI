package com.base.skillbuilderapi;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {ElementProgressEntity.class, ElementEntity.class, ChapterEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ElementProgressDao elementProgressDao();
    public abstract ElementDao elementDao();
    public abstract ChapterDao chapterDao();

    private static volatile AppDatabase instance;

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    AppDatabase.class,
                    "app_database"
            ).fallbackToDestructiveMigration().build();
        }
        return instance;
    }

}

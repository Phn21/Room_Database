package com.example.ex7;

import android.content.Context;

import androidx.room.Room;

public class DatabaseClient {

    private Context mCtx;
    private static DatabaseClient mnInstance;
    private AppDatabase appDatabase;

    private DatabaseClient(Context mCtx) {
        this.mCtx = mCtx;
        appDatabase = Room.databaseBuilder(mCtx, AppDatabase.class, "Database1").build();
    }

    public static synchronized DatabaseClient getInstance(Context mCtx) {
        if (mnInstance == null) {
            mnInstance = new DatabaseClient(mCtx);
        }
        return mnInstance;
    }

    public AppDatabase getAppDatabase() {
        return appDatabase;
    }
}

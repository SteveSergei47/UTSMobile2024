package com.example.tourismblog.Database;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {BlogPost.class}, version = 1)
public abstract class BlogDatabase extends RoomDatabase {
    private static BlogDatabase instance;
    public abstract BlogPostDao blogPostDao();

    public static synchronized BlogDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    BlogDatabase.class,
                    "blog_database"
            ).build();
        }
        return instance;
    }
}



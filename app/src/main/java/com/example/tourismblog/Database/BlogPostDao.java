package com.example.tourismblog.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Delete;
import androidx.room.Query;
import java.util.List;

@Dao
public interface BlogPostDao {
    @Insert
    void insert(BlogPost blogPost);

    @Delete
    void delete(BlogPost blogPost);
    @Query("SELECT * FROM blog_posts ORDER BY date DESC")
    LiveData<List<BlogPost>> getAllPosts();

    @Query("SELECT * FROM blog_posts WHERE title LIKE :searchQuery OR content LIKE :searchQuery")
    LiveData<List<BlogPost>> searchPosts(String searchQuery);
}



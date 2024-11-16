package com.example.tourismblog.repository;

import android.app.Application;
import androidx.lifecycle.LiveData;
import com.example.tourismblog.Database.BlogDatabase;
import com.example.tourismblog.Database.BlogPost;
import com.example.tourismblog.Database.BlogPostDao;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BlogRepository {
    private BlogPostDao blogPostDao;
    private LiveData<List<BlogPost>> allPosts;
    private ExecutorService executorService;

    public BlogRepository(Application application) {
        BlogDatabase database = BlogDatabase.getInstance(application);
        blogPostDao = database.blogPostDao();
        allPosts = blogPostDao.getAllPosts();
        executorService = Executors.newSingleThreadExecutor();
    }


    public void insert(BlogPost blogPost) {
        executorService.execute(() -> blogPostDao.insert(blogPost));
    }


    public void delete(BlogPost blogPost) {
        executorService.execute(() -> blogPostDao.delete(blogPost)); // Menghapus data menggunakan DAO
    }


    public LiveData<List<BlogPost>> getAllPosts() {
        return allPosts;
    }


    public LiveData<List<BlogPost>> searchPosts(String query) {
        return blogPostDao.searchPosts("%" + query + "%"); // Pencarian berdasarkan query
    }
}

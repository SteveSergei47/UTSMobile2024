package com.example.tourismblog.viewmodel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.tourismblog.Database.BlogPost;
import com.example.tourismblog.repository.BlogRepository;
import java.util.List;

public class BlogViewModel extends AndroidViewModel {
    private BlogRepository repository;
    private LiveData<List<BlogPost>> allPosts;

    public BlogViewModel(Application application) {
        super(application);
        repository = new BlogRepository(application);
        allPosts = repository.getAllPosts();
    }

    public void insert(BlogPost blogPost) {
        repository.insert(blogPost);
    }

    public LiveData<List<BlogPost>> getAllPosts() {
        return allPosts;
    }

    public LiveData<List<BlogPost>> searchPosts(String query) {
        return repository.searchPosts(query);
    }


    public void delete(BlogPost blogPost) {
        repository.delete(blogPost);
    }
}

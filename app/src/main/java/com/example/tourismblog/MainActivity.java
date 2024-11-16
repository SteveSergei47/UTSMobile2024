package com.example.tourismblog;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.tourismblog.Database.BlogPost;
import com.example.tourismblog.viewmodel.BlogViewModel;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private BlogPostAdapter adapter;
    private ProgressBar progressBar;
    private BlogViewModel blogViewModel;
    private Button buttonDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new BlogPostAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);

        blogViewModel = new ViewModelProvider(this).get(BlogViewModel.class);

        // Observe changes in the database
        blogViewModel.getAllPosts().observe(this, blogPosts -> {
            adapter.setBlogPosts(blogPosts);
            progressBar.setVisibility(View.GONE);
        });

        // Load sample data if database is empty
        loadSampleData();

        // Set listener for delete button
        adapter.setOnDeleteListener(post -> {
            new AlertDialog.Builder(this)
                    .setTitle("Hapus Data")
                    .setMessage("Apakah Anda yakin ingin menghapus posting ini?")
                    .setPositiveButton("Ya", (dialog, which) -> {
                        blogViewModel.delete(post);
                    })
                    .setNegativeButton("Tidak", null)
                    .show();
        });
    }

    private void loadSampleData() {
        // Add sample blog posts to database
        blogViewModel.insert(new BlogPost(
                "Raja Ampat : Pesona Bawah Laut",
                "Temukan Keindahan Bawah Laut Tiada Tanding",
                "https://th.bing.com/th/id/OSK.HEROhNmIv3f3lob0dPO27JsetM2F0CwAltRrT6ajLC8ohMA?rs=1&pid=ImgDetMain",
                "Stevin Yustisio",
                "2024-03-15"
        ));

        blogViewModel.insert(new BlogPost(
                "Keajaiban Dunia : Candi Borobudur",
                "Candi Buddha Termegah di Dunia",
                "https://upload.wikimedia.org/wikipedia/commons/7/77/Stupa_Borobudur.jpg",
                "Aldo Cau",
                "2024-03-14"
        ));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                performSearch(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                performSearch(newText);
                return true;
            }
        });

        return true;
    }

    private void performSearch(String query) {
        progressBar.setVisibility(View.VISIBLE);
        if (query.isEmpty()) {
            blogViewModel.getAllPosts().observe(this, blogPosts -> {
                adapter.setBlogPosts(blogPosts);
                progressBar.setVisibility(View.GONE);
            });
        } else {
            blogViewModel.searchPosts(query).observe(this, blogPosts -> {
                adapter.setBlogPosts(blogPosts);
                progressBar.setVisibility(View.GONE);
            });
        }
    }
}

package com.example.tourismblog;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.squareup.picasso.Picasso;

public class BlogPostDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog_post_detail);

        String title = getIntent().getStringExtra("title");
        String content = getIntent().getStringExtra("content");
        String imageUrl = getIntent().getStringExtra("imageUrl");
        String author = getIntent().getStringExtra("author");
        String date = getIntent().getStringExtra("date");

        TextView titleText = findViewById(R.id.detailTitle);
        TextView contentText = findViewById(R.id.detailContent);
        TextView authorText = findViewById(R.id.detailAuthor);
        TextView dateText = findViewById(R.id.detailDate);
        ImageView imageView = findViewById(R.id.detailImage);

        titleText.setText(title);
        contentText.setText(content);
        authorText.setText(author);
        dateText.setText(date);

        Picasso.get().load(imageUrl)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
                .into(imageView);
    }
}
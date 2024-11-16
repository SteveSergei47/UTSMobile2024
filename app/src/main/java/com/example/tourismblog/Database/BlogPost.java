package com.example.tourismblog.Database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "blog_posts")
public class BlogPost {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String content;
    private String imageUrl;
    private String author;
    private String date;

    public BlogPost(String title, String content, String imageUrl, String author, String date) {
        this.title = title;
        this.content = content;
        this.imageUrl = imageUrl;
        this.author = author;
        this.date = date;
    }


    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTitle() { return title; }
    public String getContent() { return content; }
    public String getImageUrl() { return imageUrl; }
    public String getAuthor() { return author; }
    public String getDate() { return date; }

}


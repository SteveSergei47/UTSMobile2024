package com.example.tourismblog;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tourismblog.Database.BlogPost;
import com.squareup.picasso.Picasso;
import java.util.List;

public class BlogPostAdapter extends RecyclerView.Adapter<BlogPostAdapter.ViewHolder> {
    private List<BlogPost> blogPosts;
    private OnDeleteListener onDeleteListener;

    public BlogPostAdapter(List<BlogPost> blogPosts) {
        this.blogPosts = blogPosts;
    }

    public void setBlogPosts(List<BlogPost> blogPosts) {
        this.blogPosts = blogPosts;
        notifyDataSetChanged();
    }

    public void setOnDeleteListener(OnDeleteListener listener) {
        this.onDeleteListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.blog_post_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BlogPost post = blogPosts.get(position);
        holder.titleText.setText(post.getTitle());
        holder.authorText.setText(post.getAuthor());
        holder.dateText.setText(post.getDate());

        Picasso.get().load(post.getImageUrl())
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
                .into(holder.imageView);


        holder.deleteButton.setOnClickListener(v -> {
            if (onDeleteListener != null) {
                onDeleteListener.onDelete(post);
            }
        });
    }

    @Override
    public int getItemCount() {
        return blogPosts.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView titleText;
        TextView authorText;
        TextView dateText;
        Button deleteButton; // Tombol Hapus

        ViewHolder(View view) {
            super(view);
            imageView = view.findViewById(R.id.postImage);
            titleText = view.findViewById(R.id.postTitle);
            authorText = view.findViewById(R.id.postAuthor);
            dateText = view.findViewById(R.id.postDate);
            deleteButton = view.findViewById(R.id.deleteButton);
        }
    }


    public interface OnDeleteListener {
        void onDelete(BlogPost post);
    }
}

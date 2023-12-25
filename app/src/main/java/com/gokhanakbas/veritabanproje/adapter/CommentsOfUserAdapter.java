package com.gokhanakbas.veritabanproje.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.gokhanakbas.veritabanproje.MoviePage;
import com.gokhanakbas.veritabanproje.data.entity.entity.Comment;
import com.gokhanakbas.veritabanproje.data.entity.entity.Movie;
import com.gokhanakbas.veritabanproje.databinding.RecyclerRowCommentBinding;
import com.gokhanakbas.veritabanproje.databinding.RecyclerRowMovieBinding;

import java.util.List;

public class CommentsOfUserAdapter extends RecyclerView.Adapter<CommentsOfUserAdapter.CommentOfUserViewHolder> {

        private final Context mContext;
        private List<Comment> comment_list;

        public CommentsOfUserAdapter(Context context, List<Comment> comments) {
            mContext = context;
            comment_list = comments;
        }

        @Override
        public CommentOfUserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            RecyclerRowCommentBinding binding = RecyclerRowCommentBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
            return new CommentOfUserViewHolder(binding);
        }

        @Override
        public void onBindViewHolder(CommentOfUserViewHolder holder, int position) {
            Comment comment = comment_list.get(position);
            holder.binding.userFullName.setText(String.valueOf(comment.getUser_id()));
            holder.binding.userComment.setText(comment.getComment_desc());
            holder.binding.userCommentScore.setText(String.valueOf(comment.getComment_user_score()).concat("/10"));
        }

        @Override
        public int getItemCount() {
            return comment_list.size();
        }

        public class CommentOfUserViewHolder extends RecyclerView.ViewHolder {
            private final RecyclerRowCommentBinding binding;

            public CommentOfUserViewHolder(RecyclerRowCommentBinding binding) {
                super(binding.getRoot());
                this.binding = binding;
            }
        }
    }


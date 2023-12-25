package com.gokhanakbas.veritabanproje.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.gokhanakbas.veritabanproje.data.entity.entity.Comment;
import com.gokhanakbas.veritabanproje.databinding.RecyclerRowCommentBinding;

import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder> {
        private final Context mContext;
        private List<Comment> comment_list;

        public CommentAdapter(Context context, List<Comment> comments) {
            mContext = context;
            comment_list = comments;
        }

        @Override
        public CommentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            RecyclerRowCommentBinding binding = RecyclerRowCommentBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
            return new CommentViewHolder(binding);
        }

        @Override
        public void onBindViewHolder(CommentViewHolder holder, int position) {
            Comment comment = comment_list.get(position);
            holder.binding.userFullName.setText(String.valueOf(comment.getUser_id()));
            holder.binding.userComment.setText(comment.getComment_desc());
            holder.binding.userCommentScore.setText(comment.getComment_user_score().concat("/10"));
        }

        @Override
        public int getItemCount() {
            return comment_list.size();
        }

        public class CommentViewHolder extends RecyclerView.ViewHolder {
            private final RecyclerRowCommentBinding binding;

            public CommentViewHolder(RecyclerRowCommentBinding binding) {
                super(binding.getRoot());
                this.binding = binding;
            }
        }
    }

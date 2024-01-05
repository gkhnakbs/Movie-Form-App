package com.gokhanakbas.veritabanproje.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.gokhanakbas.veritabanproje.CommentEditPage;
import com.gokhanakbas.veritabanproje.CommentPage;
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
            holder.binding.userCommentScore.setText(comment.getComment_user_score().concat("/5"));
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

                binding.getRoot().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            Comment comment=comment_list.get(position);
                            Intent intent=new Intent(v.getContext(),CommentEditPage.class);
                            intent.putExtra("comment_object",comment);
                            mContext.startActivity(intent);
                        }
                    }
                });
            }
        }
    }

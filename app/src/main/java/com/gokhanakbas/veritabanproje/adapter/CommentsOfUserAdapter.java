package com.gokhanakbas.veritabanproje.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.gokhanakbas.veritabanproje.CommentEditPage;
import com.gokhanakbas.veritabanproje.CommentPage;
import com.gokhanakbas.veritabanproje.MoviePage;
import com.gokhanakbas.veritabanproje.data.entity.entity.Comment;
import com.gokhanakbas.veritabanproje.data.entity.entity.Movie;
import com.gokhanakbas.veritabanproje.databinding.RecyclerRowCommentBinding;
import com.gokhanakbas.veritabanproje.databinding.RecyclerRowMovieBinding;

import java.util.List;

public class CommentsOfUserAdapter extends RecyclerView.Adapter<CommentsOfUserAdapter.CommentOfUserViewHolder> {

        private final Context mContext;
        private List<Comment> comment_list;
        private String activity;

        public CommentsOfUserAdapter(Context context, List<Comment> comments,String Activity) {
            mContext = context;
            comment_list = comments;
            activity=Activity;
        }

        @Override
        public CommentOfUserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            RecyclerRowCommentBinding binding = RecyclerRowCommentBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
            return new CommentOfUserViewHolder(binding);
        }

        @Override
        public void onBindViewHolder(CommentOfUserViewHolder holder, int position) {
            Comment comment = comment_list.get(position);
            holder.binding.userFullName.setText(String.valueOf(comment.getComment_user_name()));
            holder.binding.userComment.setText(comment.getComment_desc());
            holder.binding.userCommentScore.setText(String.valueOf(comment.getComment_user_score()).concat("/5"));
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

                binding.getRoot().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        binding.getRoot().setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                int position=getAdapterPosition();
                                if(position!=RecyclerView.NO_POSITION){
                                    Comment comment=comment_list.get(position);
                                    if(activity.equals("user_profile")){
                                        Intent intent= new Intent(v.getContext(), CommentEditPage.class);
                                        intent.putExtra("comment_object",comment);
                                        intent.putExtra("user_role","user");
                                        mContext.startActivity(intent);
                                    }else{
                                    Intent intent= new Intent(v.getContext(), CommentPage.class);
                                    intent.putExtra("comment_object",comment);
                                    mContext.startActivity(intent);
                                    }
                                }
                            }
                        });
                    }
                });
            }
        }
    }


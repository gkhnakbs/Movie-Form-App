package com.gokhanakbas.veritabanproje.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.gokhanakbas.veritabanproje.MovieCreateAndEdit;
import com.gokhanakbas.veritabanproje.MoviePage;
import com.gokhanakbas.veritabanproje.data.entity.entity.Movie;
import com.gokhanakbas.veritabanproje.databinding.RecyclerRowMovieBinding;


import java.util.List;

public class DeletedMovieAdapter extends RecyclerView.Adapter<DeletedMovieAdapter.MovieViewHolder> {

    private final Context mContext;
    private List<Movie> movie_list;

    public DeletedMovieAdapter(Context context, List<Movie> movies) {
        mContext = context;
        movie_list = movies;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerRowMovieBinding binding = RecyclerRowMovieBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MovieViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        Movie movie = movie_list.get(position);
        holder.binding.movieNameRvItem.setText(movie.getMovie_name());
        holder.binding.movieDescRvItem.setText(movie.getMovie_desc());
        holder.binding.movieScoreRvItem.setText(String.valueOf(movie.getMovie_score()).concat("/5"));
    }

    @Override
    public int getItemCount() {
        return movie_list.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        private final RecyclerRowMovieBinding binding;

        public MovieViewHolder(RecyclerRowMovieBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}

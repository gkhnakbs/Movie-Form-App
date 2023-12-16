package com.example.yourpackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.gokhanakbas.veritabanproje.data.entity.Movie;
import com.gokhanakbas.veritabanproje.databinding.RecyclerRowMovieBinding;


import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.FilmViewHolder> {

    private final Context mContext;
    private List<Movie> movie_list;

    public MovieAdapter(Context context, List<Movie> movies) {
        mContext = context;
        movie_list = movies;
    }

    @Override
    public FilmViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerRowMovieBinding binding = RecyclerRowMovieBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new FilmViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(FilmViewHolder holder, int position) {
        Movie movie = movie_list.get(position);

    }

    @Override
    public int getItemCount() {
        return movie_list.size();
    }

    public class FilmViewHolder extends RecyclerView.ViewHolder {
        private final RecyclerRowMovieBinding binding;

        public FilmViewHolder(RecyclerRowMovieBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}

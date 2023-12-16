package com.example.yourpackage;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.gokhanakbas.veritabanproje.MoviePage;
import com.gokhanakbas.veritabanproje.data.entity.entity.Movie;
import com.gokhanakbas.veritabanproje.databinding.RecyclerRowMovieBinding;


import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private final Context mContext;
    private List<Movie> movie_list;

    public MovieAdapter(Context context, List<Movie> movies) {
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
        holder.binding.movieScoreRvItem.setText(String.valueOf(movie.getMovie_score()).concat("/10"));
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

            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Tıklama olayını ele al
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        Movie clickedMovie = movie_list.get(position);

                        Intent intent = new Intent(mContext, MoviePage.class);
                        intent.putExtra("movie_object",clickedMovie);

                        /*intent.putExtra("movie_id",clickedMovie.getMovie_id());
                        intent.putExtra("movie_name",clickedMovie.getMovie_name());
                        intent.putExtra("movie_desc",clickedMovie.getMovie_desc());
                        intent.putExtra("movie_score",clickedMovie.getMovie_score());
                        intent.putIntegerArrayListExtra("movie_actors",clickedMovie.getMovie_actors());
                        intent.putIntegerArrayListExtra("movie_comments",clickedMovie.getMovie_comments());*/
                        mContext.startActivity(intent);
                    }
                }
            });
        }
    }
}

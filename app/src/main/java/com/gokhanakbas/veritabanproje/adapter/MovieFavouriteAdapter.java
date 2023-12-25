package com.gokhanakbas.veritabanproje.adapter;

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

public class MovieFavouriteAdapter extends RecyclerView.Adapter<MovieFavouriteAdapter.FavouriteMovieHolder> {

        private final Context mContext;
        private List<Movie> movie_list;

        public MovieFavouriteAdapter(Context context, List<Movie> movies) {
            mContext = context;
            movie_list = movies;
        }

        @Override
        public FavouriteMovieHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            RecyclerRowMovieBinding binding = RecyclerRowMovieBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
            return new FavouriteMovieHolder(binding);
        }

        @Override
        public void onBindViewHolder(FavouriteMovieHolder holder, int position) {
            Movie movie = movie_list.get(position);
            holder.binding.movieNameRvItem.setText(movie.getMovie_name());
            holder.binding.movieDescRvItem.setText(movie.getMovie_desc());
            holder.binding.movieScoreRvItem.setText(String.valueOf(movie.getMovie_score()).concat("/10"));
        }

        @Override
        public int getItemCount() {
            return movie_list.size();
        }

        public class FavouriteMovieHolder extends RecyclerView.ViewHolder {
            private final RecyclerRowMovieBinding binding;

            public FavouriteMovieHolder(RecyclerRowMovieBinding binding) {
                super(binding.getRoot());
                this.binding = binding;

                binding.getRoot().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            Movie clickedMovie = movie_list.get(position);

                            Intent intent = new Intent(mContext, MoviePage.class);
                            intent.putExtra("movie_object",clickedMovie);

                            mContext.startActivity(intent);
                        }
                    }
                });
            }
        }
    }

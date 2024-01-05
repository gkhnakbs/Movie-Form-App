package com.gokhanakbas.veritabanproje.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gokhanakbas.veritabanproje.MovieCreateAndEdit;
import com.gokhanakbas.veritabanproje.adapter.MovieAdapter;
import com.gokhanakbas.veritabanproje.data.entity.entity.Actor;
import com.gokhanakbas.veritabanproje.data.entity.entity.Comment;
import com.gokhanakbas.veritabanproje.data.entity.entity.Movie;
import com.gokhanakbas.veritabanproje.databinding.FragmentMovieBinding;

import java.util.ArrayList;

public class MovieFragment extends Fragment {


    FragmentMovieBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // inflater.inflate(R.layout.fragment_movie, container, false);
        binding=FragmentMovieBinding.inflate(getLayoutInflater());
        ArrayList<Actor> actorList=new ArrayList<>();

        ArrayList<Movie> movieList=new ArrayList<>();

        MovieAdapter adapter=new MovieAdapter(requireContext(),movieList,"admin");
        binding.movieRv.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.movieRv.setAdapter(adapter);
        binding.floatingActionButtonAddMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(), MovieCreateAndEdit.class);
                startActivity(intent);
            }
        });

        return binding.getRoot();
    }
}
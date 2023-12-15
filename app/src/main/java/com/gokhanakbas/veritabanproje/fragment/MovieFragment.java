package com.gokhanakbas.veritabanproje.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gokhanakbas.veritabanproje.MovieCreateAndEdit;
import com.gokhanakbas.veritabanproje.R;
import com.gokhanakbas.veritabanproje.databinding.FragmentMovieBinding;

public class MovieFragment extends Fragment {


    FragmentMovieBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // inflater.inflate(R.layout.fragment_movie, container, false);
        binding=FragmentMovieBinding.inflate(getLayoutInflater());
        binding.floatingActionButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(), MovieCreateAndEdit.class);
                startActivity(intent);
            }
        });

        return binding.getRoot();
    }
}
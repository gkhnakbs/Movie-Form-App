package com.gokhanakbas.veritabanproje.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gokhanakbas.veritabanproje.Profile_Settings;
import com.gokhanakbas.veritabanproje.R;
import com.gokhanakbas.veritabanproje.adapter.CommentsOfUserAdapter;
import com.gokhanakbas.veritabanproje.adapter.MovieFavouriteAdapter;
import com.gokhanakbas.veritabanproje.data.entity.entity.Actor;
import com.gokhanakbas.veritabanproje.data.entity.entity.Comment;
import com.gokhanakbas.veritabanproje.data.entity.entity.Movie;
import com.gokhanakbas.veritabanproje.databinding.FragmentProfilePageBinding;

import java.util.ArrayList;

public class ProfilePageFragment extends Fragment {

    FragmentProfilePageBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding=FragmentProfilePageBinding.inflate(inflater);
        ArrayList<Actor> actorList=new ArrayList<>();
        actorList.add(new Actor(1,"Ahmet Akbaş","Türkiye","Erkek","34"));
        actorList.add(new Actor(2,"Ahmet Akbaş","Türkiye","Gay","34"));
        actorList.add(new Actor(3,"Ahmet Akbaş","Türkiye","Kadın","34"));
        actorList.add(new Actor(4,"Ahmet Akbaş","Türkiye","Erkek","34"));
        actorList.add(new Actor(5,"Ahmet Akbaş","Türkiye","Erkek","34"));
        actorList.add(new Actor(6,"Ahmet Akbaş","Türkiye","Erkek","34"));
        actorList.add(new Actor(7,"Ahmet Akbaş","Türkiye","Erkek","34"));
        actorList.add(new Actor(8,"Ahmet Akbaş","Türkiye","Erkek","34"));
        ArrayList<Comment> commentList=new ArrayList<>();
        commentList.add(new Comment(1,1,"Comment,Comment,Comment,Comment,Comment,Comment","8.9"));
        commentList.add(new Comment(1,2,"Comment,Comment,Comment,Comment,Comment,Comment","8.9"));
        commentList.add(new Comment(1,3,"Comment,Comment,Comment,Comment,Comment,Comment","8.9"));
        commentList.add(new Comment(1,4,"Comment,Comment,Comment,Comment,Comment,Comment","8.9"));
        commentList.add(new Comment(1,5,"Comment,Comment,Comment,Comment,Comment,Comment","8.9"));
        commentList.add(new Comment(1,6,"Comment,Comment,Comment,Comment,Comment,Comment","8.9"));

        ArrayList<Movie> movieList=new ArrayList<>();
        movieList.add(new Movie(1,"Movie 1","Movie Description Movie Description Movie Description Movie Description Movie Description","8.9","Science",actorList,commentList));
        movieList.add(new Movie(2,"Movie 2","Movie Description Movie Description Movie Description Movie Description Movie Description","6.5","Science",actorList,commentList));
        movieList.add(new Movie(3,"Movie 3","Movie Description Movie Description Movie Description Movie Description Movie Description","9.0","Science",actorList,commentList));
        movieList.add(new Movie(4,"Movie 4","Movie Description Movie Description Movie Description Movie Description Movie Description","5.7","Science",actorList,commentList));
        movieList.add(new Movie(5,"Movie 5","Movie Description Movie Description Movie Description Movie Description Movie Description","5.2","Science",actorList,commentList));
        movieList.add(new Movie(6,"Movie 6","Movie Description Movie Description Movie Description Movie Description Movie Description","7.0","Science",actorList,commentList));
        movieList.add(new Movie(7,"Movie 7","Movie Description Movie Description Movie Description Movie Description Movie Description","6.1","Science",actorList,commentList));
        movieList.add(new Movie(8,"Movie 8","Movie Description Movie Description Movie Description Movie Description Movie Description","6.8","Science",actorList,commentList));
        movieList.add(new Movie(9,"Movie 9","Movie Description Movie Description Movie Description Movie Description Movie Description","7.9","Science",actorList,commentList));
        movieList.add(new Movie(10,"Movie 10","Movie Description Movie Description Movie Description Movie Description Movie Description","10.0","Science",actorList,commentList));
        movieList.add(new Movie(11,"Movie 11","Movie Description Movie Description Movie Description Movie Description Movie Description","9.3","Science",actorList,commentList));
        movieList.add(new Movie(12,"Movie 12","Movie Description Movie Description Movie Description Movie Description Movie Description","8.6","Science",actorList,commentList));

        binding.recyclerViewCommentsOfProfile.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.recyclerViewFavouritesOfProfile.setLayoutManager(new LinearLayoutManager(requireContext()));
        MovieFavouriteAdapter adapter=new MovieFavouriteAdapter(requireContext(),movieList);
        binding.recyclerViewFavouritesOfProfile.setAdapter(adapter);

        CommentsOfUserAdapter adapter1=new CommentsOfUserAdapter(requireContext(),commentList);
        binding.recyclerViewCommentsOfProfile.setAdapter(adapter1);
        binding.setProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(), Profile_Settings.class);
                startActivity(intent);
                
            }
        });
        return binding.getRoot();
    }
}
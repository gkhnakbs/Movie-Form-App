package com.gokhanakbas.veritabanproje.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import com.gokhanakbas.veritabanproje.adapter.CommentAdapter;
import com.gokhanakbas.veritabanproje.data.entity.entity.Comment;
import com.gokhanakbas.veritabanproje.data.entity.entity.Movie;
import com.gokhanakbas.veritabanproje.databinding.FragmentCommentBinding;

import java.util.ArrayList;

public class CommentFragment extends Fragment {

    FragmentCommentBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // inflater.inflate(R.layout.fragment_comment, container, false);

        //burada spinner a entries özelliğine liste halinde filmleri vereceğiz.
        //Daha sonra butona bastığında ise database de filtreleme ile o filmin yorumlarını çekip adapter ı yenileyecek.
        binding=FragmentCommentBinding.inflate(getLayoutInflater());

        ArrayList<Movie> movieList=new ArrayList<>();
        movieList.add(new Movie(1,"Oppenheimer"));
        movieList.add(new Movie(1,"Ölümlü Dünya 2"));
        movieList.add(new Movie(1,"El Camino"));


        ArrayAdapter<Movie> adapter=new ArrayAdapter<Movie>(requireContext(),  android.R.layout.simple_spinner_dropdown_item, movieList);
        adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        binding.filmSpinner.setAdapter(adapter);

        ArrayList<Comment> commentList=new ArrayList<>();
        commentList.add(new Comment(1,1,"Ahmet","Comment,Comment,Comment,Comment,Comment,Comment","8.9"));
        commentList.add(new Comment(1,2,"Ahmet","Comment,Comment,Comment,Comment,Comment,Comment","8.9"));
        commentList.add(new Comment(1,3,"Ahmet","Comment,Comment,Comment,Comment,Comment,Comment","8.9"));
        commentList.add(new Comment(1,4,"Ahmet","Comment,Comment,Comment,Comment,Comment,Comment","8.9"));
        commentList.add(new Comment(1,5,"Ahmet","Comment,Comment,Comment,Comment,Comment,Comment","8.9"));
        commentList.add(new Comment(1,6,"Ahmet","Comment,Comment,Comment,Comment,Comment,Comment","8.9"));

        binding.commentRvAdmin.setLayoutManager(new LinearLayoutManager(requireContext()));
        CommentAdapter adapter1=new CommentAdapter(requireContext(),commentList);
        binding.commentRvAdmin.setAdapter(adapter1);

        return binding.getRoot();
    }
}
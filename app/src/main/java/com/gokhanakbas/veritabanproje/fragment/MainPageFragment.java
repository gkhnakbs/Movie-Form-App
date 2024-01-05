package com.gokhanakbas.veritabanproje.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.gokhanakbas.veritabanproje.LoginPage;
import com.gokhanakbas.veritabanproje.adapter.MovieAdapter;
import com.gokhanakbas.veritabanproje.data.entity.entity.Actor;
import com.gokhanakbas.veritabanproje.data.entity.entity.Comment;
import com.gokhanakbas.veritabanproje.data.entity.entity.Movie;
import com.gokhanakbas.veritabanproje.database.DBConnection;
import com.gokhanakbas.veritabanproje.databinding.FragmentMainPageBinding;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class MainPageFragment extends Fragment {

    FragmentMainPageBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // inflater.inflate(R.layout.fragment_main_page, container, false);
        binding=FragmentMainPageBinding.inflate(getLayoutInflater()); // Doğru id kullanıldığından emin olun
        binding.rvMainPage.setLayoutManager(new LinearLayoutManager(requireContext()));
        int result_movie_id;
        String result_movie_name="";
        String result_movie_desc="";
        String result_movie_score="";
        String result_movie_category="";
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection connection= DBConnection.connection;
        ArrayList<Movie> movieList=new ArrayList<>();

        try {
            // Örnek bir sorgu
            String query = "SELECT * FROM movies";
            PreparedStatement statement = connection.prepareStatement(query);

            // Sorguyu çalıştır
            ResultSet resultSet = statement.executeQuery();

            // Sonuçları işle
            if (resultSet == null) {
                Toast.makeText(getContext(), "Film bulunamamıştır", Toast.LENGTH_LONG).show();
            } else {
                while (resultSet.next()) {
                    result_movie_id = resultSet.getInt(1);
                    result_movie_name = resultSet.getString(2).toString();
                    result_movie_desc = resultSet.getString(3).toString();
                    result_movie_score = resultSet.getString(4).toString();
                    result_movie_category = resultSet.getString(5).toString();
                    movieList.add(new Movie(result_movie_id,result_movie_name,result_movie_desc,result_movie_score,result_movie_category));
                }
                resultSet.close();
                statement.close();
            }}catch(Exception e){
                System.out.println("Başarısız Film");
                e.printStackTrace();
            }

        MovieAdapter adapter=new MovieAdapter(requireContext(),movieList,"user");

        binding.rvMainPage.setAdapter(adapter);
        return binding.getRoot();
    }
}
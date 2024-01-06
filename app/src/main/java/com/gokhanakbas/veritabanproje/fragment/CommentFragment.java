package com.gokhanakbas.veritabanproje.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.gokhanakbas.veritabanproje.adapter.CommentAdapter;
import com.gokhanakbas.veritabanproje.data.entity.entity.Comment;
import com.gokhanakbas.veritabanproje.data.entity.entity.Movie;
import com.gokhanakbas.veritabanproje.database.DBConnection;
import com.gokhanakbas.veritabanproje.databinding.FragmentCommentBinding;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CommentFragment extends Fragment {

    FragmentCommentBinding binding;
    ArrayList<Comment> commentList=new ArrayList<>();
    ArrayList<Movie> movie_list1=new ArrayList<>();
    Connection connection;
    int result_movie_id;
    String result_movie_name="";
    String result_movie_desc="";
    String result_movie_score="";
    String result_movie_category="";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // inflater.inflate(R.layout.fragment_comment, container, false);

        //burada spinner a entries özelliğine liste halinde filmleri vereceğiz.
        //Daha sonra butona bastığında ise database de filtreleme ile o filmin yorumlarını çekip adapter ı yenileyecek.
        binding=FragmentCommentBinding.inflate(getLayoutInflater());

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        connection= DBConnection.connection;
        int i=0;
        while(i<MovieFragment.movieList.size()){
            Movie movie=MovieFragment.movieList.get(i);
            movie_list1.add(new Movie(movie.getMovie_id(),movie.getMovie_name()));
            i++;
        }

        ArrayAdapter<Movie> adapter=new ArrayAdapter<Movie>(requireContext(),  android.R.layout.simple_spinner_dropdown_item, movie_list1);
        adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        binding.filmSpinner.setAdapter(adapter);

        getComments();

        binding.commentRvAdmin.setLayoutManager(new LinearLayoutManager(requireContext()));
        CommentAdapter adapter1=new CommentAdapter(requireContext(),commentList);
        binding.commentRvAdmin.setAdapter(adapter1);

        return binding.getRoot();
    }

    public void getComments() {

        int result_comment_id;
        int result_comment_user_id;
        String result_comment_desc;
        String result_comment_score;
        String result_comment_user_name;

        try {
            String query = "SELECT c.com_id,c.com_user_id,c.com_description,c.com_score,u.user_name FROM comments AS c " +
                    "JOIN users u ON u.user_id=c.com_user_id";
            PreparedStatement statement = connection.prepareStatement(query);

            ResultSet resultSet = statement.executeQuery();


            if (resultSet == null) {
                Toast.makeText(getContext(), "Yorum bulunamamıştır", Toast.LENGTH_LONG).show();
            } else {
                while (resultSet.next()) {
                    result_comment_id = resultSet.getInt(1);
                    result_comment_user_id = resultSet.getInt(2);
                    result_comment_desc = resultSet.getString(3);
                    result_comment_score = resultSet.getString(4);
                    result_comment_user_name = resultSet.getString(5);
                    commentList.add(new Comment(result_comment_id, result_comment_user_id, result_comment_user_name, result_comment_desc, result_comment_score));
                }
                resultSet.close();
                statement.close();
            }
        } catch (Exception e) {
            System.out.println("Başarısız Yorum Çekme");
            e.printStackTrace();
        }
    }
}
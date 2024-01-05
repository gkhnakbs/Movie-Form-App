package com.gokhanakbas.veritabanproje.fragment;

import static com.gokhanakbas.veritabanproje.database.DBConnection.connection;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.gokhanakbas.veritabanproje.LoginPage;
import com.gokhanakbas.veritabanproje.MainActivity;
import com.gokhanakbas.veritabanproje.Profile_Settings;
import com.gokhanakbas.veritabanproje.R;
import com.gokhanakbas.veritabanproje.adapter.CommentsOfUserAdapter;
import com.gokhanakbas.veritabanproje.adapter.MovieFavouriteAdapter;
import com.gokhanakbas.veritabanproje.data.entity.entity.Actor;
import com.gokhanakbas.veritabanproje.data.entity.entity.Comment;
import com.gokhanakbas.veritabanproje.data.entity.entity.Movie;
import com.gokhanakbas.veritabanproje.database.DBConnection;
import com.gokhanakbas.veritabanproje.databinding.FragmentProfilePageBinding;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ProfilePageFragment extends Fragment {
    ArrayList<Comment> commentList=new ArrayList<>();
    ArrayList<Movie> movieList=new ArrayList<>();
    FragmentProfilePageBinding binding;
    Connection connection;
    int userId;
    String result_user_name="";
    String result_user_age="";
    String result_user_password="";
    String result_user_mail="";
    int result_movie_id;
    String result_movie_name="";
    String result_movie_desc="";
    String result_movie_score="";
    int result_comment_id;
    int result_com_user_id;
    int result_com_movie_id;
    String result_com_description="";
    String result_com_score="";
    String result_com_username="";
    public ProfilePageFragment(int loginUserId) {
        userId=loginUserId;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding=FragmentProfilePageBinding.inflate(inflater);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        connection = DBConnection.connection;

        getUserInfo(userId);
        getFavMoviesID();
        getUserComments();
        binding.userName.setText(result_user_name);


        binding.recyclerViewCommentsOfProfile.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.recyclerViewFavouritesOfProfile.setLayoutManager(new LinearLayoutManager(requireContext()));
        MovieFavouriteAdapter adapter=new MovieFavouriteAdapter(requireContext(),movieList);
        binding.recyclerViewFavouritesOfProfile.setAdapter(adapter);

        CommentsOfUserAdapter adapter1=new CommentsOfUserAdapter(requireContext(),commentList,"user_profile");
        binding.recyclerViewCommentsOfProfile.setAdapter(adapter1);
        binding.setProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(), Profile_Settings.class);
                intent.putExtra("user_name",result_user_name);
                intent.putExtra("user_age",result_user_age);
                intent.putExtra("user_mail",result_user_mail);
                intent.putExtra("user_password",result_user_password);
                startActivity(intent);
            }
        });
        return binding.getRoot();
    }
    public void getFavMoviesID(){
        try {
            // Örnek bir sorgu
            String query = "SELECT fav_movie_id FROM favourites where fav_user_id="+userId;
            PreparedStatement statement = connection.prepareStatement(query);

            // Sorguyu çalıştır
            ResultSet resultSet = statement.executeQuery();

            // Sonuçları işle
            if (resultSet == null) {
                Toast.makeText(getContext(), "Favori Film bulunamamıştır", Toast.LENGTH_LONG).show();
            } else {
                while (resultSet.next()) {
                    getFavMovies(resultSet.getInt(1));
                }
                resultSet.close();
                statement.close();
            }}catch(Exception e){
            System.out.println("Başarısız Favori Film");
            e.printStackTrace();
        }
    }
    public void getFavMovies(int movie_id){
        try {
            // Örnek bir sorgu
            String query = "SELECT movie_name,movie_desc,movie_score FROM movies where movie_id="+movie_id;
            PreparedStatement statement = connection.prepareStatement(query);

            // Sorguyu çalıştır
            ResultSet resultSet = statement.executeQuery();

            // Sonuçları işle
            if (resultSet == null) {
                Toast.makeText(getContext(), "Favori Film bulunamamıştır", Toast.LENGTH_LONG).show();
            } else {
                while (resultSet.next()) {
                    result_movie_name = resultSet.getString(1);
                    result_movie_desc = resultSet.getString(2);
                    result_movie_score = resultSet.getString(3);
                    movieList.add(new Movie(movie_id,result_movie_name,result_movie_desc,result_movie_score));
                }
                resultSet.close();
                statement.close();
            }}catch(Exception e){
            System.out.println("Başarısız Favori Film");
            e.printStackTrace();
        }
    }

    public void getUserComments() {
        try {
            // Örnek bir sorgu
            String query = "SELECT * from comments where com_user_id="+userId ;
            PreparedStatement statement = connection.prepareStatement(query);

            // Sorguyu çalıştır
            ResultSet resultSet = statement.executeQuery();

            // Sonuçları işle
            if (resultSet == null) {
                Toast.makeText(getContext(), "Yorum bulunamamıştır", Toast.LENGTH_LONG).show();
            } else {
                while (resultSet.next()) {
                    result_comment_id=resultSet.getInt(1);
                    result_com_user_id=resultSet.getInt(2);
                    result_com_movie_id=resultSet.getInt(3);
                    result_com_description=resultSet.getString(4);
                    result_com_score=resultSet.getString(5);
                    commentList.add(new Comment(result_comment_id,result_com_user_id,result_com_movie_id,result_user_name,result_com_description,result_com_score));
                }
                resultSet.close();
                statement.close();
            }
        } catch (Exception e) {
            System.out.println("Başarısız Yorum Çekme");
            e.printStackTrace();
        }

    }

    public void getUserInfo(int user_id){
        try {
            // Örnek bir sorgu
            String query = "SELECT * FROM users where user_id="+user_id;
            PreparedStatement statement = connection.prepareStatement(query);

            // Sorguyu çalıştır
            ResultSet resultSet = statement.executeQuery();

            // Sonuçları işle
            while(resultSet.next()){
                 result_user_name=resultSet.getString("user_name");
                 result_user_age=resultSet.getString("user_age");
                 result_user_mail=resultSet.getString("user_mail");
                 result_user_password=resultSet.getString("user_password");
            }
            resultSet.close();
            statement.close();
        } catch (Exception e) {
            System.out.println("Başarısız Kişi Çekme");
            e.printStackTrace();
        }
    }
}
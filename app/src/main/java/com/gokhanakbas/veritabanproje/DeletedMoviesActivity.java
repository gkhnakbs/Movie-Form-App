package com.gokhanakbas.veritabanproje;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.os.StrictMode;
import android.widget.Toast;

import com.gokhanakbas.veritabanproje.adapter.DeletedMovieAdapter;
import com.gokhanakbas.veritabanproje.data.entity.entity.Movie;
import com.gokhanakbas.veritabanproje.database.DBConnection;
import com.gokhanakbas.veritabanproje.databinding.ActivityDeletedMoviesBinding;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DeletedMoviesActivity extends AppCompatActivity {

    ActivityDeletedMoviesBinding binding;
    Connection connection;
    ArrayList<Movie> movieList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityDeletedMoviesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        connection= DBConnection.connection;
        getMovies();


        DeletedMovieAdapter adapter=new DeletedMovieAdapter(this,movieList);
        binding.deletedMovieRv.setLayoutManager(new LinearLayoutManager(this));
        binding.deletedMovieRv.setAdapter(adapter);
    }
    public void getMovies(){
        movieList.clear();
        int result_movie_id;
        String result_movie_name="";
        String result_movie_desc="";
        String result_movie_score="";
        String result_movie_category="";
        try {

            String query = "SELECT * FROM movie_log";
            PreparedStatement statement = connection.prepareStatement(query);


            ResultSet resultSet = statement.executeQuery();


            if (resultSet == null) {
                Toast.makeText(this, "Film bulunamamıştır", Toast.LENGTH_LONG).show();
            } else {
                while (resultSet.next()) {
                    result_movie_id = resultSet.getInt(1);
                    result_movie_name = resultSet.getString(2).toString();
                    result_movie_desc = "";
                    result_movie_score = resultSet.getString(4).toString();
                    result_movie_category = "";
                    movieList.add(new Movie(result_movie_id,result_movie_name,result_movie_desc,result_movie_score,result_movie_category));
                }
                resultSet.close();
                statement.close();
            }}catch(Exception e){
            System.out.println("Başarısız Silinmiş Film çekme");
            e.printStackTrace();
        }
    }
}
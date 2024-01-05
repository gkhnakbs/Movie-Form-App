package com.gokhanakbas.veritabanproje;

import static androidx.core.content.ContentProviderCompat.requireContext;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.gokhanakbas.veritabanproje.adapter.ActorAdapter;
import com.gokhanakbas.veritabanproje.adapter.CommentAdapter;
import com.gokhanakbas.veritabanproje.adapter.CommentsOfUserAdapter;
import com.gokhanakbas.veritabanproje.data.entity.entity.Actor;
import com.gokhanakbas.veritabanproje.data.entity.entity.Comment;
import com.gokhanakbas.veritabanproje.data.entity.entity.Movie;
import com.gokhanakbas.veritabanproje.database.DBConnection;
import com.gokhanakbas.veritabanproje.databinding.ActivityMoviePageBinding;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MoviePage extends AppCompatActivity {

    ActivityMoviePageBinding binding;
    int favouriteStatus=0;
    ArrayList<Actor> actorList=new ArrayList<>();
    ArrayList<Comment> commentList=new ArrayList<>();
    Connection connection;
    int movie_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMoviePageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        connection = DBConnection.connection;

        Intent intent = getIntent();
        if (intent != null) {
            Movie movie = (Movie) intent.getSerializableExtra("movie_object");
            binding.movieName.setText(movie.getMovie_name());
            binding.movieDescription.setText(movie.getMovie_desc());
            binding.movieScore.setText(movie.getMovie_score().concat("/10"));
            binding.categoryName.setText(movie.getMovie_category());
            movie_id=movie.getMovie_id();
            getComments(movie_id);
            getActors(movie_id);
            isItFavourite();
        }
        else{
            Toast.makeText(this,"Film bilgileri alınamadı",Toast.LENGTH_LONG).show();
            Intent intent1=new Intent(this, MainActivity.class);
            startActivity(intent1);
            finish();
        }


        binding.addFavourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(favouriteStatus==0){
                    Toast.makeText(v.getContext(),"Favorilere eklendi",Toast.LENGTH_SHORT).show();
                    binding.addFavourite.setImageResource(R.drawable.heart_icon_filled);
                    favouriteStatus=1;
                    addToFavourites();
                }
                else{
                    Toast.makeText(v.getContext(),"Favorilerden Çıkarıldı",Toast.LENGTH_SHORT).show();
                    binding.addFavourite.setImageResource(R.drawable.heart_icon);
                    favouriteStatus=0;
                    deleteFromFavourites();
                }
            }
        });

        binding.floatingActionButtonAddComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(view.getContext(), CommentEditPage.class);
                intent.putExtra("user_role","user");
                intent.putExtra("movie_id",movie_id);
                Comment comment = null;
                intent.putExtra("comment_object",comment);
                startActivity(intent);
            }
        });

        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        CommentsOfUserAdapter adapter=new CommentsOfUserAdapter(this,commentList,"movie_page");
        binding.rvCommentMoviePage.setLayoutManager(layoutManager);
        binding.rvCommentMoviePage.setAdapter(adapter);


        StaggeredGridLayoutManager layoutManager1=new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.HORIZONTAL);
        ActorAdapter adapter1 = new ActorAdapter(this,actorList);
        binding.rvActorMoviePage.setLayoutManager(layoutManager1);
        binding.rvActorMoviePage.setAdapter(adapter1);

    }

    public void isItFavourite(){
        String sql = "Select * from favourites where fav_movie_id="+movie_id+" AND fav_user_id="+LoginPage.login_user_id;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();

                if(resultSet.next()){
                        System.out.println("Beğenmiş:"+resultSet.getInt(1));
                        binding.addFavourite.setImageResource(R.drawable.heart_icon_filled);
                        favouriteStatus=1;
                        }
                       else{
                        binding.addFavourite.setImageResource(R.drawable.heart_icon);
                        favouriteStatus=0;
                    }
                resultSet.close();
                preparedStatement.close();
            System.out.println("Başarılı Favoride");
        } catch (SQLException e) {
            System.out.println("Başarısız Favoride");
            e.printStackTrace();
        }
    }

    public void addToFavourites(){
        String sql = "INSERT INTO favourites VALUES (12,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, movie_id);
            preparedStatement.setInt(2, LoginPage.login_user_id);

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Başarılı");
                Toast.makeText(this,"Favorilere Ekledi",Toast.LENGTH_SHORT).show();
            } else {
                System.out.println("Başarısız Favorilere Ekleme");
                Toast.makeText(this,"İşlem Gerçekleştirilemedi , Tekrar Deneyiniz",Toast.LENGTH_SHORT).show();
            }
        } catch (SQLException e) {
            System.out.println("Başarısız Favorilere Ekleme");
            e.printStackTrace();
        }
    }

    public void deleteFromFavourites(){
        String sql = "Delete from favourites where fav_movie_id="+movie_id+" AND fav_user_id="+LoginPage.login_user_id;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Başarılı");
                Toast.makeText(this,"Favori Kaldırma Başarılı",Toast.LENGTH_SHORT).show();
            } else {
                System.out.println("Başarısız Favori Kaldırma");
                Toast.makeText(this,"İşlem Gerçekleştirilemedi , Tekrar Deneyiniz",Toast.LENGTH_SHORT).show();
            }
        } catch (SQLException e) {
            System.out.println("Başarısız Favorilere Ekleme");
            e.printStackTrace();
        }
    }

    public void getActors(int movie_id){

        try {
            String query = "SELECT a.actor_id,a.actor_name,a.actor_age,a.actor_sex,a.actor_country FROM actorsofmovies AS aom " +
                    "JOIN actors AS a ON a.actor_id=aom.actor_id where actor_movie_id="+movie_id;
            PreparedStatement statement = connection.prepareStatement(query);
            String result_actor_name="";
            String result_actor_age="";
            String result_actor_country="";
            String result_actor_gender="";

            ResultSet resultSet = statement.executeQuery();


            if (resultSet == null) {
                Toast.makeText(this, "Aktör", Toast.LENGTH_LONG).show();
            } else {
                while (resultSet.next()) {
                    int actor_id=resultSet.getInt(1);
                    result_actor_name=resultSet.getString(2);
                    result_actor_age=resultSet.getString(3);
                    result_actor_gender=resultSet.getString(4);
                    result_actor_country=resultSet.getString(5);
                    actorList.add(new Actor(actor_id,result_actor_name,result_actor_country,result_actor_gender,result_actor_age));
                }
                resultSet.close();
                statement.close();
            }}catch(Exception e){
            System.out.println("Başarısız Film");
            e.printStackTrace();
        }

    }

    public void getComments(int movie_id) {

        int result_comment_id;
        int result_comment_user_id;
        String result_comment_desc = "";
        String result_comment_score = "";
        String result_comment_user_name = "";

        try {
            String query = "SELECT c.com_id,c.com_user_id,c.com_description,c.com_score,u.user_name FROM comments AS c " +
                    "JOIN users u ON u.user_id=c.com_user_id where com_movie_id=" + movie_id;
            PreparedStatement statement = connection.prepareStatement(query);

            ResultSet resultSet = statement.executeQuery();


            if (resultSet == null) {
                Toast.makeText(this, "Yorum bulunamamıştır", Toast.LENGTH_LONG).show();
            } else {
                while (resultSet.next()) {
                    result_comment_id=resultSet.getInt(1);
                    result_comment_user_id=resultSet.getInt(2);
                    result_comment_desc=resultSet.getString(3);
                    result_comment_score=resultSet.getString(4);
                    result_comment_user_name=resultSet.getString(5);
                    commentList.add(new Comment(result_comment_id,result_comment_user_id,result_comment_user_name,result_comment_desc,result_comment_score));
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
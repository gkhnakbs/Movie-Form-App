package com.gokhanakbas.veritabanproje;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.gokhanakbas.veritabanproje.adapter.ActorOfAdminAdapter;
import com.gokhanakbas.veritabanproje.data.entity.entity.Actor;
import com.gokhanakbas.veritabanproje.data.entity.entity.Movie;
import com.gokhanakbas.veritabanproje.database.DBConnection;
import com.gokhanakbas.veritabanproje.databinding.ActivityMovieCreateAndEditBinding;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import kotlinx.coroutines.GlobalScope;

public class MovieCreateAndEdit extends AppCompatActivity {

    ActivityMovieCreateAndEditBinding binding;
    Connection connection;
    int movie_id;
    ArrayList<Actor> actorListOfMovie=new ArrayList<>();
    public static ArrayList<String> actor_listName=new ArrayList<>();
    public static ArrayList<Integer> actor_listID=new ArrayList<>();
    int ilk=0;
    ActorOfAdminAdapter adapter;
    int edit_mode=0;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMovieCreateAndEditBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        connection= DBConnection.connection;

        Intent intent=getIntent();
        if(intent!=null) {
            Movie movie =(Movie) intent.getSerializableExtra("movie_object");
            if(movie!=null) {
                movie_id=movie.getMovie_id();
                binding.movieCategory.setText(movie.getMovie_category());
                binding.movieDescription.setText(movie.getMovie_desc());
                binding.movieName.setText(movie.getMovie_name());
                binding.movieImdbScore.setText(movie.getMovie_score());
                edit_mode=1;
                actorListOfMovie.clear();
                getActors();
            }
        }
        getAllActors();

        adapter = new ActorOfAdminAdapter(this, actorListOfMovie);
        binding.actorMovieRvAdmin.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL));
        binding.actorMovieRvAdmin.setAdapter(adapter);
        for(String i:actor_listName){
            System.out.println("Aktör list:"+i);
        }

        binding.addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int isThere=0;
                if(binding.actorAddInput.getText().toString().trim().equals("")){
                    Toast.makeText(getApplicationContext(),"Aktör adı giriniz",Toast.LENGTH_SHORT).show();
                }
                else{
                    for (String i:actor_listName) {
                        if(i.equals(binding.actorAddInput.getText().toString())){
                            isThere=1;
                            break;
                        }
                    }
                    if(isThere==1){
                       // AsyncTask.execute(MovieCreateAndEdit.this::addActorToMovie);
                        addActorToMovie();
                    }else{
                        Toast.makeText(getApplicationContext(),"Aktör bulunamadı",Toast.LENGTH_LONG).show();
                    }
                }

            }
        }
        );
        binding.saveButtonMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveMovieSettings();
                Toast.makeText(v.getContext(),"Film Başarıyla Kaydedildi",Toast.LENGTH_LONG).show();
                Intent intent=new Intent(v.getContext(), AdminMainPage.class);
                startActivity(intent);
                finish();
            }
        });
        binding.cancelButtonMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(), AdminMainPage.class);
                startActivity(intent);
                finish();
            }
        });

        binding.deleteButtonMovieAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteMovie();
                Intent intent=new Intent(v.getContext(), AdminMainPage.class);
                startActivity(intent);
                finish();
            }
        });
    }
    public void getActors(){
        actorListOfMovie.clear();
        try {
            String query = "SELECT a.actor_id,a.actor_name FROM actors AS a JOIN actorsofmovies AS aom " +
                    "ON a.actor_id=aom.actor_id where aom.actor_movie_id="+movie_id;
            PreparedStatement statement = connection.prepareStatement(query);

            int result_actor_id;
            String result_actor_name="";
            
            ResultSet resultSet = statement.executeQuery();
            if (resultSet == null) {
                System.out.println("Filmin Aktörleri bulunamadı");
            } else {
                while (resultSet.next()) {
                    result_actor_id=resultSet.getInt(1);
                    result_actor_name=resultSet.getString(2);
                    actorListOfMovie.add(new Actor(result_actor_id,result_actor_name));
                }
                resultSet.close();
                statement.close();
            }
        }catch(Exception e){
            System.out.println("Başarısız Filmin Aktörünü çekme");
            e.printStackTrace();
        }
    }
    
    public void getAllActors(){
        actor_listName.clear();
        actor_listID.clear();
        try {

            String query = "SELECT actor_id,actor_name FROM actors";
            PreparedStatement statement = connection.prepareStatement(query);

            int result_actor_id;
            String result_actor_name="";

            ResultSet resultSet = statement.executeQuery();

            if (resultSet == null) {
            } else {
                while (resultSet.next()) {
                    result_actor_id=resultSet.getInt(1);
                    result_actor_name=resultSet.getString(2);
                    actor_listID.add(result_actor_id);
                    actor_listName.add(result_actor_name);
                }
                resultSet.close();
                statement.close();
            }
        }catch(Exception e){
            System.out.println("Başarısız Film");
            e.printStackTrace();
        }
    }
    public void addActorToMovie(){
            try{
                int in=0;
                int b=0;
                while (in<actorListOfMovie.size()){
                    //Aktör listede var mı yok mu kontrol ediliyor burada
                    if(actorListOfMovie.get(in).getActor_name().equals(binding.actorAddInput.getText().toString().trim())){
                        Toast.makeText(getApplicationContext(),"Aktör zaten var",Toast.LENGTH_SHORT).show();
                        b=1;
                        break;
                    }
                    in++;
                }
                //Eğer listede yoksa actorListOfMovie ye ekliyor
                if(b==0){
                    actorListOfMovie.add(new Actor(actor_listID.get(actor_listName.indexOf(binding.actorAddInput.getText().toString().trim())),binding.actorAddInput.getText().toString().trim()));
                    adapter.notifyDataSetChanged();
                }
                }catch (Exception e){
                    e.printStackTrace();
                }
    }
    public void saveMovieSettings(){
        int isThereMovieID=0;
        String sql = "UPDATE movies SET movie_name=?,movie_desc=?,movie_score=?,movie_category=? where movie_id="+movie_id;
        if(edit_mode==0){
            sql="INSERT INTO movies(movie_name,movie_desc,movie_score,movie_category) VALUES(?,?,?,?)";
            isThereMovieID=1;
        }
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, binding.movieName.getText().toString());
            preparedStatement.setString(2, binding.movieDescription.getText().toString());
            preparedStatement.setString(3, binding.movieImdbScore.getText().toString());
            preparedStatement.setString(4, binding.movieCategory.getText().toString());

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Başarılı");
                Toast.makeText(getApplicationContext(),"Başarıyla film Bilgileri Güncellendi",Toast.LENGTH_SHORT).show();

                sql = "DELETE FROM actorsofmovies where actor_movie_id="+movie_id;
                PreparedStatement statement = connection.prepareStatement(sql);
                int affectedRowss=statement.executeUpdate();
                    if (affectedRowss >= 0) {
                        System.out.println("Başarılı Önceki Aktörleri Silme");
                        System.out.println("Sıra aktörleri eklemede");
                        if(isThereMovieID==1){
                            sql = "Select movie_id from movies where movie_name=?";
                            PreparedStatement statement21 = connection.prepareStatement(sql);
                            statement21.setString(1,binding.movieName.getText().toString());
                            ResultSet resultSet=statement21.executeQuery();
                            while(resultSet.next()){
                                movie_id=resultSet.getInt(1);
                            }
                        }
                        sql = "INSERT INTO actorsofmovies(actor_movie_id,actor_id) VALUES(?,?)";
                        for(Actor i:actorListOfMovie){
                            PreparedStatement statement1= connection.prepareStatement(sql);
                            System.out.println("Aktör ID si:"+i.getActor_id());
                            statement1.setInt(1, movie_id);
                            statement1.setInt(2, i.getActor_id());
                            statement1.executeUpdate();
                        }
                    }
                    else{
                        System.out.println("aktörleri ilk silme başarısız");
                    }
                } else {
                System.out.println("Başarısızız");
                Toast.makeText(getApplicationContext(),"İşlem Gerçekleştirilemedi , Tekrar Deneyiniz",Toast.LENGTH_LONG).show();
            }
        } catch (SQLException e) {
            System.out.println("Başarısız");
            e.printStackTrace();
        }
    }

    public void deleteMovie(){
        String sql = "delete from movies where movie_id="+movie_id;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Başarılı Film Silme");
                Toast.makeText(getApplicationContext(),"Başarılı Film Silme",Toast.LENGTH_SHORT).show();

            } else {
                System.out.println("Başarısız Film Silme");
                Toast.makeText(getApplicationContext(),"İşlem Gerçekleştirilemedi , Tekrar Deneyiniz",Toast.LENGTH_SHORT).show();
            }
        } catch (SQLException e) {
            System.out.println("Başarısız");
            e.printStackTrace();
        }
    }

}
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
                getActors();
            }
        }
        getAllActors();

        ActorOfAdminAdapter adapter = new ActorOfAdminAdapter(this, actorListOfMovie);
        binding.actorMovieRvAdmin.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        binding.actorMovieRvAdmin.setAdapter(adapter);

        binding.addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyncTask.execute(MovieCreateAndEdit.this::addActorToMovie);
                adapter.notifyDataSetChanged();
            }
        });



        binding.saveButtonMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Burada database e gidilip filmin bilgileri güncelleme işlemi yapılacak.
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
        int isThere=0;
        if(binding.actorAddInput.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(),"Aktör adı giriniz",Toast.LENGTH_LONG).show();
        }
        else{
            for (String i:actor_listName) {
                if(i.equals(binding.actorAddInput.getText().toString())){
                    isThere=1;
                    break;
                }
            }
            if(isThere==1){
                int i=0;
                int b=0;
                while (i<actorListOfMovie.size()){
                    if(actorListOfMovie.get(i).getActor_name().equals(binding.actorAddInput.getText().toString())){
                        Toast.makeText(getApplicationContext(),"Aktör zaten var",Toast.LENGTH_LONG).show();
                        b=1;
                        break;
                    }
                    i++;
                }
                if(b==0){
                    actorListOfMovie.add(new Actor(actor_listID.get(i),binding.actorAddInput.getText().toString()));
                }

            }else{
                Toast.makeText(getApplicationContext(),"Böyle bir aktör yok",Toast.LENGTH_LONG).show();
            }
        }
    }

}
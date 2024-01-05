package com.gokhanakbas.veritabanproje;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
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

public class MovieCreateAndEdit extends AppCompatActivity {

    ActivityMovieCreateAndEditBinding binding;
    Connection connection;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMovieCreateAndEditBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        connection= DBConnection.connection;
        ArrayList<Actor> actorArrayList=new ArrayList<>();
        Intent intent=getIntent();
        if(intent!=null) {
            Movie movie =(Movie) intent.getSerializableExtra("movie_object");
            if(movie!=null) {
                binding.movieCategory.setText(movie.getMovie_category());
                binding.movieDescription.setText(movie.getMovie_desc());
                binding.movieName.setText(movie.getMovie_name());
                ActorOfAdminAdapter adapter = new ActorOfAdminAdapter(this, actorArrayList);
                binding.actorMovieRvAdmin.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
                binding.actorMovieRvAdmin.setAdapter(adapter);
            }
        }
        //getActors();
        ArrayList<String> actor_list=new ArrayList<>();
        ArrayAdapter<String> adapterForAdmin=new ArrayAdapter<String>(binding.spinner.getContext(),android.R.layout.simple_spinner_dropdown_item, actor_list);
        adapterForAdmin.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        binding.spinner.setAdapter(adapterForAdmin);

        binding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Burada filmin listesine aktörü ekleme yapacağız.
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

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
        try {
            // Örnek bir sorgu
            String query = "SELECT * FROM actors AS a";
            PreparedStatement statement = connection.prepareStatement(query);

            // Sorguyu çalıştır
            ResultSet resultSet = statement.executeQuery();

            // Sonuçları işle
            if (resultSet == null) {
                Toast.makeText(this, "Aktör", Toast.LENGTH_LONG).show();
            } else {
                while (resultSet.next()) {
                    //getActorInfo(resultSet.getInt(1));

                }
                resultSet.close();
                statement.close();
            }}catch(Exception e){
            System.out.println("Başarısız Film");
            e.printStackTrace();
        }
    }
}
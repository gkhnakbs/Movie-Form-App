package com.gokhanakbas.veritabanproje;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.gokhanakbas.veritabanproje.data.entity.entity.Movie;
import com.gokhanakbas.veritabanproje.databinding.ActivityMoviePageBinding;

public class MoviePage extends AppCompatActivity {

    ActivityMoviePageBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMoviePageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent = getIntent();
        if (intent != null) {
            Movie movie = (Movie) intent.getSerializableExtra("movie_object");
            binding.movieName.setText(movie.getMovie_name());
            binding.movieDescription.setText(movie.getMovie_desc());
            binding.movieScore.setText(movie.getMovie_score().concat("/10"));
            binding.categoryName.setText(movie.getMovie_category());
        }

        binding.addFavourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(binding.addFavourite.getDrawable().equals(R.drawable.heart_icon)){
                    Toast.makeText(v.getContext(),"Favorilere eklendi",Toast.LENGTH_LONG).show();
                    binding.addFavourite.setImageResource(R.drawable.heart_icon_filled);
                }
                else{
                Toast.makeText(v.getContext(),"Favorilerden Çıkarıldı",Toast.LENGTH_LONG).show();
                binding.addFavourite.setImageResource(R.drawable.heart_icon);
                }
            }
        });
    }
}
package com.gokhanakbas.veritabanproje;

import static androidx.core.content.ContentProviderCompat.requireContext;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.gokhanakbas.veritabanproje.adapter.CommentAdapter;
import com.gokhanakbas.veritabanproje.data.entity.entity.Comment;
import com.gokhanakbas.veritabanproje.data.entity.entity.Movie;
import com.gokhanakbas.veritabanproje.databinding.ActivityMoviePageBinding;

import java.util.ArrayList;

public class MoviePage extends AppCompatActivity {

    ActivityMoviePageBinding binding;
    int favouriteStatus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMoviePageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent = getIntent();
        ArrayList<Comment> comment_list=new ArrayList<>();
        if (intent != null) {
            Movie movie = (Movie) intent.getSerializableExtra("movie_object");
            binding.movieName.setText(movie.getMovie_name());
            binding.movieDescription.setText(movie.getMovie_desc());
            binding.movieScore.setText(movie.getMovie_score().concat("/10"));
            binding.categoryName.setText(movie.getMovie_category());
            comment_list=movie.getMovie_comments();
        }

        favouriteStatus=0;
        binding.addFavourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(favouriteStatus==0){
                    Toast.makeText(v.getContext(),"Favorilere eklendi",Toast.LENGTH_SHORT).show();
                    binding.addFavourite.setImageResource(R.drawable.heart_icon_filled);
                    favouriteStatus=1;
                }
                else{
                Toast.makeText(v.getContext(),"Favorilerden Çıkarıldı",Toast.LENGTH_SHORT).show();
                binding.addFavourite.setImageResource(R.drawable.heart_icon);
                favouriteStatus=0;
                }
            }
        });


        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        CommentAdapter adapter=new CommentAdapter(this,comment_list);
        binding.rvCommentMoviePage.setLayoutManager(layoutManager);
        binding.rvCommentMoviePage.setAdapter(adapter);

    }
}
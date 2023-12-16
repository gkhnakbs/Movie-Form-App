package com.gokhanakbas.veritabanproje;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.gokhanakbas.veritabanproje.databinding.ActivityMovieCreateAndEditBinding;

public class MovieCreateAndEdit extends AppCompatActivity {

    ActivityMovieCreateAndEditBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMovieCreateAndEditBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.saveButtonMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
}
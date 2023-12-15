package com.gokhanakbas.veritabanproje;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.gokhanakbas.veritabanproje.databinding.ActivityActorPageBinding;

public class ActorPage extends AppCompatActivity {

    ActivityActorPageBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityActorPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}
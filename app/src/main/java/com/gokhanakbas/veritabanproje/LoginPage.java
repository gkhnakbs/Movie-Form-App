package com.gokhanakbas.veritabanproje;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.gokhanakbas.veritabanproje.databinding.ActivityLoginPageBinding;

public class LoginPage extends AppCompatActivity {

    ActivityLoginPageBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityLoginPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}
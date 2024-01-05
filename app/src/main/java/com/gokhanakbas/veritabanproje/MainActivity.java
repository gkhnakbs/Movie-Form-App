package com.gokhanakbas.veritabanproje;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;

import com.gokhanakbas.veritabanproje.database.DBConnection;
import com.gokhanakbas.veritabanproje.databinding.ActivityMainBinding;
import com.gokhanakbas.veritabanproje.fragment.MainPageFragment;
import com.gokhanakbas.veritabanproje.fragment.ProfilePageFragment;

import java.sql.Connection;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        Intent intent=getIntent();
        String user_mail=intent.getStringExtra("user_mail");
        int durum1=R.id.profile;
        binding.bottomNavigationMain.setOnItemSelectedListener(item -> {

            if(item.getItemId()==durum1){
                replaceFragment(new ProfilePageFragment(LoginPage.login_user_id));

            }
            else {
                replaceFragment(new MainPageFragment());
            }

            return true;
        });
    }

    public void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager =getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainerView2,fragment);
        fragmentTransaction.commit();

    }
}
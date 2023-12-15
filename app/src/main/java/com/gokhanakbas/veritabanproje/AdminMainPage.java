package com.gokhanakbas.veritabanproje;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.gokhanakbas.veritabanproje.databinding.ActivityAdminMainPageBinding;
import com.gokhanakbas.veritabanproje.fragment.ActorFragment;
import com.gokhanakbas.veritabanproje.fragment.CommentFragment;
import com.gokhanakbas.veritabanproje.fragment.MovieFragment;

public class AdminMainPage extends AppCompatActivity {

    ActivityAdminMainPageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityAdminMainPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        int durum1=R.id.movie;
        int durum2=R.id.actor;



        binding.bottomBarAdminPage.setOnItemSelectedListener(item -> {

            if(item.getItemId()==durum1){
                replaceFragment(new MovieFragment());
            }
            else if(item.getItemId()==durum2){
                replaceFragment(new ActorFragment());
            }else {
                replaceFragment(new CommentFragment());
            }
            return true;
        });

    }

    public void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager =getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainerView,fragment);
        fragmentTransaction.commit();

    }
}
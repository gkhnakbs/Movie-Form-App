package com.gokhanakbas.veritabanproje.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gokhanakbas.veritabanproje.Profile_Settings;
import com.gokhanakbas.veritabanproje.R;
import com.gokhanakbas.veritabanproje.databinding.FragmentProfilePageBinding;

public class ProfilePageFragment extends Fragment {

    FragmentProfilePageBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding=FragmentProfilePageBinding.inflate(inflater);

        binding.setProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(), Profile_Settings.class);
                startActivity(intent);
                
            }
        });
        return binding.getRoot();
    }
}
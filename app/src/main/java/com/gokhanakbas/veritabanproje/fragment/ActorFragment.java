package com.gokhanakbas.veritabanproje.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gokhanakbas.veritabanproje.ActorPage;
import com.gokhanakbas.veritabanproje.R;
import com.gokhanakbas.veritabanproje.databinding.FragmentActorBinding;


public class ActorFragment extends Fragment {
    FragmentActorBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // inflater.inflate(R.layout.fragment_actor, container, false);
        binding=FragmentActorBinding.inflate(inflater);

        binding.floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(), ActorPage.class);
                intent.putExtra("role","admin");
                startActivity(intent);
            }
        });
        return binding.getRoot();
    }
}
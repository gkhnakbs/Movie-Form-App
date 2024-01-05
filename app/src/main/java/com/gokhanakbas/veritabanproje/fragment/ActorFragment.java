package com.gokhanakbas.veritabanproje.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gokhanakbas.veritabanproje.ActorPage;
import com.gokhanakbas.veritabanproje.R;
import com.gokhanakbas.veritabanproje.adapter.ActorOfAdminAdapter;
import com.gokhanakbas.veritabanproje.adapter.ActorOfAdminAdapter2;
import com.gokhanakbas.veritabanproje.adapter.CommentAdapter;
import com.gokhanakbas.veritabanproje.data.entity.entity.Actor;
import com.gokhanakbas.veritabanproje.data.entity.entity.Comment;
import com.gokhanakbas.veritabanproje.databinding.FragmentActorBinding;

import java.util.ArrayList;


public class ActorFragment extends Fragment {
    FragmentActorBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // inflater.inflate(R.layout.fragment_actor, container, false);
        binding=FragmentActorBinding.inflate(inflater);

        binding.floatingActionButtonAddActor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(), ActorPage.class);
                intent.putExtra("user_role","admin");
                startActivity(intent);
            }
        });

        ArrayList<Actor> actorList=new ArrayList<>();
        actorList.add(new Actor(1,"Ahmet Akbaş","Türkiye","Erkek","34"));
        actorList.add(new Actor(2,"Ahmet Akbaş","Türkiye","Gay","34"));
        actorList.add(new Actor(3,"Ahmet Akbaş","Türkiye","Kadın","34"));
        actorList.add(new Actor(4,"Ahmet Akbaş","Türkiye","Erkek","34"));
        actorList.add(new Actor(5,"Ahmet Akbaş","Türkiye","Erkek","34"));
        actorList.add(new Actor(6,"Ahmet Akbaş","Türkiye","Erkek","34"));
        actorList.add(new Actor(7,"Ahmet Akbaş","Türkiye","Erkek","34"));
        actorList.add(new Actor(8,"Ahmet Akbaş","Türkiye","Erkek","34"));

        ActorOfAdminAdapter2 adapter=new ActorOfAdminAdapter2(requireContext(),actorList);
        binding.recyclerViewActors.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.recyclerViewActors.setAdapter(adapter);


        return binding.getRoot();
    }
}
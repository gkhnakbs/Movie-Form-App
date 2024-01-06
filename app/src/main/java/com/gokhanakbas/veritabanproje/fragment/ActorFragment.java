package com.gokhanakbas.veritabanproje.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.gokhanakbas.veritabanproje.ActorPage;
import com.gokhanakbas.veritabanproje.R;
import com.gokhanakbas.veritabanproje.adapter.ActorOfAdminAdapter;
import com.gokhanakbas.veritabanproje.adapter.ActorOfAdminAdapter2;
import com.gokhanakbas.veritabanproje.adapter.CommentAdapter;
import com.gokhanakbas.veritabanproje.data.entity.entity.Actor;
import com.gokhanakbas.veritabanproje.data.entity.entity.Comment;
import com.gokhanakbas.veritabanproje.database.DBConnection;
import com.gokhanakbas.veritabanproje.databinding.FragmentActorBinding;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


public class ActorFragment extends Fragment {
    FragmentActorBinding binding;
    ArrayList<Actor> actorList=new ArrayList<>();
    Connection connection;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // inflater.inflate(R.layout.fragment_actor, container, false);
        binding=FragmentActorBinding.inflate(inflater);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        connection= DBConnection.connection;
        binding.floatingActionButtonAddActor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(), ActorPage.class);
                intent.putExtra("user_role","admin");
                startActivity(intent);
            }
        });

        getActors();
        ActorOfAdminAdapter2 adapter=new ActorOfAdminAdapter2(requireContext(),actorList);
        binding.recyclerViewActors.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.recyclerViewActors.setAdapter(adapter);


        return binding.getRoot();
    }

    public void getActors(){

        try {
            String query = "SELECT * from actors";
            PreparedStatement statement = connection.prepareStatement(query);
            int result_actor_id;
            String result_actor_name="";
            String result_actor_age="";
            String result_actor_country="";
            String result_actor_gender="";


            ResultSet resultSet = statement.executeQuery();


            if (resultSet == null) {
                System.out.println("Aktör Çekme Başarısız");
                Toast.makeText(getContext(), "Aktör Çekme Başarısız", Toast.LENGTH_LONG).show();
            } else {
                while (resultSet.next()) {
                    result_actor_id=resultSet.getInt(1);
                    result_actor_name=resultSet.getString(2);
                    result_actor_age=resultSet.getString(3);
                    result_actor_gender=resultSet.getString(4);
                    result_actor_country=resultSet.getString(5);
                    actorList.add(new Actor(result_actor_id,result_actor_name,result_actor_country,result_actor_gender,result_actor_age));
                }
                resultSet.close();
                statement.close();
            }}catch(Exception e){
            System.out.println("Başarısız Film");
            e.printStackTrace();
        }

    }
}
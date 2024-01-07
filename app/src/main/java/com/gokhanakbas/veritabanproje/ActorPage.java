package com.gokhanakbas.veritabanproje;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Toast;

import com.gokhanakbas.veritabanproje.data.entity.entity.Actor;
import com.gokhanakbas.veritabanproje.data.entity.entity.Movie;
import com.gokhanakbas.veritabanproje.database.DBConnection;
import com.gokhanakbas.veritabanproje.databinding.ActivityActorPageBinding;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ActorPage extends AppCompatActivity {

    Connection connection;
    ActivityActorPageBinding binding;
    int actor_id;
    int edit_mode=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityActorPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        connection= DBConnection.connection;

        Intent intent = getIntent();
        if(intent!=null) {
            Actor actor = (Actor) intent.getSerializableExtra("actor_object");
            if(actor!=null) {
                edit_mode=1;
                binding.textInputActorName.setText(actor.getActor_name());
                binding.textInputActorAge.setText(actor.getActor_age());
                binding.textInputActorCountry.setText(actor.getActor_country());
                actor_id = actor.getActor_id();
                if (actor.getActor_gender().equals("Erkek")) {
                    binding.actorGenderSwitch.setChecked(false);
                } else {
                    binding.actorGenderSwitch.setChecked(true);
                }
            }

            if(intent.getStringExtra("user_role").equals("admin")){

            }else{
            binding.saveButtonActor.setVisibility(View.INVISIBLE);
            binding.textInputActorName.setEnabled(false);
            binding.textInputActorAge.setEnabled(false);
            binding.actorGenderSwitch.setEnabled(false);
            binding.textInputActorCountry.setEnabled(false);
            }
        }
        binding.saveButtonActor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addOrUpdateActors();
                Intent intent =new Intent(v.getContext(), AdminMainPage.class);
                startActivity(intent);
                finish();
            }
        });
        binding.cancelButtonActor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(LoginPage.user_role=="Admin"){
                Intent intent =new Intent(v.getContext(), AdminMainPage.class);
                startActivity(intent);
                finish();
                }
                else{
                    Intent intent =new Intent(v.getContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }


    public void addOrUpdateActors(){
        String sql = "Update actors SET actor_name=?,actor_age=?,actor_sex=?,actor_country=? where actor_id="+actor_id;
        if(edit_mode==0){
            sql="Insert into actors(actor_name,actor_age,actor_sex,actor_country) VALUES(?,?,?,?)";
        }
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            String sex="Erkek";
            if(binding.actorGenderSwitch.isChecked()){
                sex="Kadın";
            }
            preparedStatement.setString(1,binding.textInputActorName.getText().toString());
            preparedStatement.setString(2, binding.textInputActorAge.getText().toString());
            preparedStatement.setString(3, sex);
            preparedStatement.setString(4, binding.textInputActorCountry.getText().toString());

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Başarılı");
                Toast.makeText(getApplicationContext(),"Başarıyla Aktör Bilgileri Güncellendi",Toast.LENGTH_SHORT).show();
            } else {
                System.out.println("Başarısız Aktör güncelleme");
                Toast.makeText(getApplicationContext(),"İşlem Gerçekleştirilemedi , Tekrar Deneyiniz",Toast.LENGTH_SHORT).show();
            }
        } catch (SQLException e) {
            System.out.println("Başarısız");
            e.printStackTrace();
        }
    }
}
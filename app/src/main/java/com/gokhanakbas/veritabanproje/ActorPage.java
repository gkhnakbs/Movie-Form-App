package com.gokhanakbas.veritabanproje;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.gokhanakbas.veritabanproje.data.entity.entity.Actor;
import com.gokhanakbas.veritabanproje.data.entity.entity.Movie;
import com.gokhanakbas.veritabanproje.databinding.ActivityActorPageBinding;

public class ActorPage extends AppCompatActivity {

    ActivityActorPageBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityActorPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        if(intent!=null) {
            Actor actor = (Actor) intent.getSerializableExtra("actor_object");
            binding.textInputActorName.setText(actor.getActor_name());
            binding.textInputActorAge.setText(actor.getActor_age());
            binding.textInputActorCountry.setText(actor.getActor_country());
            if(actor.getActor_gender().equals("Erkek")){
                binding.actorGenderSwitch.setChecked(false);
            }
            else {
                binding.actorGenderSwitch.setChecked(true);
            }

            if(intent.getStringExtra("user_role").equals("admin")){

            }else{
            binding.saveButtonActor.setEnabled(false);
            binding.cancelButtonActor.setEnabled(false);
            binding.textInputActorName.setEnabled(false);
            binding.textInputActorAge.setEnabled(false);
            binding.textInputActorCountry.setEnabled(false);
            }
        }
        binding.saveButtonActor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),"Aktör Başarıyla Kaydedildi",Toast.LENGTH_LONG).show();
                Intent intent =new Intent(v.getContext(), AdminMainPage.class);
                startActivity(intent);
                finish();
            }
        });
        binding.cancelButtonActor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(v.getContext(), AdminMainPage.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
package com.gokhanakbas.veritabanproje;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.gokhanakbas.veritabanproje.databinding.ActivityActorPageBinding;

public class ActorPage extends AppCompatActivity {

    ActivityActorPageBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityActorPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


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
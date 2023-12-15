package com.gokhanakbas.veritabanproje;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.gokhanakbas.veritabanproje.databinding.ActivitySignInPageBinding;

public class SignInPage extends AppCompatActivity {

    ActivitySignInPageBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySignInPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),"Kaydoldunuz",Toast.LENGTH_LONG).show();
                Intent intent=new Intent(v.getContext(), LoginPage.class);
                startActivity(intent);
                finish();
            }
        });
        binding.cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), LoginPage.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
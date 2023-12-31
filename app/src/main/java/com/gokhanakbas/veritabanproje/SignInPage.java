package com.gokhanakbas.veritabanproje;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Toast;

import com.gokhanakbas.veritabanproje.database.DBConnection;
import com.gokhanakbas.veritabanproje.databinding.ActivitySignInPageBinding;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;

public class SignInPage extends AppCompatActivity {


    ActivitySignInPageBinding binding;
    Connection connection;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySignInPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        connection=DBConnection.connection;


        binding.signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(binding.textInputFullName.getText().toString().equals("") ||binding.textInputEmail.getText().toString().equals("") ||
                        binding.textInputAge.getText().toString().equals("") ||binding.textInputPassword.getText().toString().equals("")){
                        Toast.makeText(v.getContext(),"Lütfen Boş Alan Bırakmayınız",Toast.LENGTH_LONG).show();
                }else{
                      addUser();
                       Intent intent=new Intent(v.getContext(), LoginPage.class);
                       startActivity(intent);
                       finish();
                   }

        }});

        binding.cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), LoginPage.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void addUser(){
        try {
            String sql = "INSERT INTO users(user_name,user_mail,user_age,user_password,user_role) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, binding.textInputFullName.getText().toString());
            preparedStatement.setString(2, binding.textInputEmail.getText().toString());
            preparedStatement.setString(3, binding.textInputAge.getText().toString());
            preparedStatement.setString(4, binding.textInputPassword.getText().toString());
            preparedStatement.setString(5, "User");

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Başarılı");
                Toast.makeText(getApplicationContext(),"Başarıyla Kaydoldunuz",Toast.LENGTH_LONG).show();

            } else {
                System.out.println("Başarısız");
                Toast.makeText(getApplicationContext(),"İşlem Gerçekleştirilemedi , Tekrar Deneyiniz",Toast.LENGTH_LONG).show();

            }
        } catch (SQLException e) {
            System.out.println("Başarısız");
            e.printStackTrace();
        }

    }
}
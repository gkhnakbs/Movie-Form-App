package com.gokhanakbas.veritabanproje;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Toast;

import com.gokhanakbas.veritabanproje.database.DBConnection;
import com.gokhanakbas.veritabanproje.databinding.ActivityProfileSettingsBinding;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Profile_Settings extends AppCompatActivity {

    ActivityProfileSettingsBinding binding;

    Connection connection;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityProfileSettingsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        connection = DBConnection.connection;

        Intent intent=getIntent();
        if(intent!=null){
            binding.textInputFullName.setText(intent.getStringExtra("user_name"));
            binding.textInputAge.setText(intent.getStringExtra("user_age"));
            binding.textInputEmail.setText(intent.getStringExtra("user_mail"));
            binding.textInputPassword.setText(intent.getStringExtra("user_password"));
        }else{
            Intent intent1=new Intent(this, MainActivity.class);
            startActivity(intent1);
            finish();
        }
        binding.cancelButtonProfileSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        binding.saveChangesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateUserInfo();
                Intent intent=new Intent(v.getContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    
    public void updateUserInfo(){
        String sql = "update users set user_name=?,user_age=?,user_mail=?,user_password=? where user_id="+LoginPage.login_user_id;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, String.valueOf(binding.textInputFullName.getText()));
            preparedStatement.setString(2, String.valueOf(binding.textInputAge.getText()));
            preparedStatement.setString(3, String.valueOf(binding.textInputEmail.getText()));
            preparedStatement.setString(4, String.valueOf(binding.textInputPassword.getText()));

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Başarılı");
                Toast.makeText(this,"Kişi Bilgileri Başarıyla Güncellendi",Toast.LENGTH_SHORT).show();
            } else {
                System.out.println("Başarısız Kişi Bilgileri Güncelleme");
                Toast.makeText(this,"İşlem Gerçekleştirilemedi , Tekrar Deneyiniz",Toast.LENGTH_SHORT).show();
            }
        } catch (SQLException e) {
            System.out.println("Başarısız Kişi Bilgileri Güncelleme");
            e.printStackTrace();
        }
    }
}
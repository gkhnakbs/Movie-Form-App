package com.gokhanakbas.veritabanproje;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Toast;

import com.gokhanakbas.veritabanproje.database.DBConnection;
import com.gokhanakbas.veritabanproje.databinding.ActivityLoginPageBinding;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginPage extends AppCompatActivity {


    String result="";
    ActivityLoginPageBinding binding;

    static int login_user_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityLoginPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        DBConnection connnection1=new DBConnection();
        connnection1.DatabaseConnection();
        Connection connection=DBConnection.connection;


        binding.loginbutton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(binding.textInputEmail.getText().equals("") || binding.textInputPass.getText().equals("")){
                            Toast.makeText(getApplicationContext(),"Lütfen Boş alanları doldurunuz!",Toast.LENGTH_SHORT).show();
                        }else{

                            try {
                                // Örnek bir sorgu
                                String query = "SELECT user_id,user_password FROM users where user_mail='"+binding.textInputEmail.getText().toString()+"'";
                                PreparedStatement statement = connection.prepareStatement(query);

                                // Sorguyu çalıştır
                                ResultSet resultSet = statement.executeQuery();

                                // Sonuçları işle
                                if(resultSet==null){
                                    Toast.makeText(getApplicationContext(),"Hesabınız bulunmamaktadır",Toast.LENGTH_LONG).show();
                                }else{
                                while (resultSet.next()) {
                                    result=resultSet.getString(2).toString();
                                    login_user_id=resultSet.getInt(1);
                                    System.out.println("ŞİFRE:"+result);
                                }
                                resultSet.close();
                                statement.close();
                                if(result.equals(binding.textInputPassword.getEditText().getText().toString())){
                                    Toast.makeText(getApplicationContext(),"Başarıyla Giriş Yaptınız",Toast.LENGTH_LONG).show();
                                    Intent intent=new Intent(v.getContext(), MainActivity.class);
                                    intent.putExtra("user_mail",binding.textInputEmail.getText().toString());
                                    startActivity(intent);
                                    finish();
                                }else{
                                    Toast.makeText(getApplicationContext(),"Başarıyla Giriş yapamadınız",Toast.LENGTH_LONG).show();
                                }
                            }} catch (SQLException e) {
                                System.out.println("Başarısız");
                                e.printStackTrace();
                            }
                        }
                    }
                }
        );
        binding.signinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(), SignInPage.class);
                startActivity(intent);
            }
        });
    }
}
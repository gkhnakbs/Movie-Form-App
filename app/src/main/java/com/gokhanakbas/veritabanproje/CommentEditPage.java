package com.gokhanakbas.veritabanproje;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.RatingBar;
import android.widget.Toast;

import com.gokhanakbas.veritabanproje.data.entity.entity.Comment;
import com.gokhanakbas.veritabanproje.database.DBConnection;
import com.gokhanakbas.veritabanproje.databinding.ActivityCommentEditPageBinding;
import com.gokhanakbas.veritabanproje.fragment.ProfilePageFragment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CommentEditPage extends AppCompatActivity {
    ActivityCommentEditPageBinding binding;
    String user_role;
    float ratingbar_user_score;
    int movie_id;
    int com_id;
    int edit_mode=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityCommentEditPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent=getIntent();

        if(intent!=null){
            Comment comment= (Comment) intent.getSerializableExtra("comment_object");
            movie_id= intent.getIntExtra("movie_id",0);
            if(comment!=null){
            edit_mode=1;
            com_id=comment.getComment_id();
            binding.commentDescriptionUser.setText(comment.getComment_desc());
            binding.ratingBar.setRating(Float.valueOf(comment.getComment_user_score()));
            }
            else{
                binding.deleteComment.setVisibility(View.INVISIBLE);
            }
            user_role=intent.getStringExtra("user_role");
            if(user_role.equals("admin")){
                binding.ratingBar.setEnabled(false);
                binding.commentDescriptionUser.setEnabled(false);
            }
        }
        else{
            binding.deleteComment.setVisibility(View.INVISIBLE);
        }

        binding.saveComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Burada Temel database kodları alır
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
                Connection connection= DBConnection.connection;
                if(edit_mode==1){
                    if(binding.commentDescriptionUser.getText().toString().equals("")||
                            ratingbar_user_score==0){
                        Toast.makeText(view.getContext(),"Boş alanlar var!",Toast.LENGTH_SHORT).show();
                    }
                    else{//Burada UPDATE işlemi yapılacak
                        try {
                            // Örnek bir sorgu
                            String query = "Update comments set com_desc='"+binding.commentDescriptionUser.getText().toString()+
                                    "',com_score="+ratingbar_user_score+" where com_id="+com_id;

                            PreparedStatement statement = connection.prepareStatement(query);
                            // Sorguyu çalıştır
                            int affectedRows = statement.executeUpdate();
                            if (affectedRows > 0) {
                                System.out.println("Başarılı");
                                Toast.makeText(view.getContext(),"Başarıyla Yorum Güncellendi",Toast.LENGTH_LONG).show();
                                Intent intent=new Intent(view.getContext(), MainActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                System.out.println("Başarısız");
                                Toast.makeText(view.getContext(),"İşlem Gerçekleştirilemedi , Tekrar Deneyiniz",Toast.LENGTH_LONG).show();
                            }
                    } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }

                    }
                }else{
                    if(binding.commentDescriptionUser.getText().toString().equals("")||
                        ratingbar_user_score==0){
                    Toast.makeText(view.getContext(),"Boş alanlar var!",Toast.LENGTH_SHORT).show();
                    }
                    else{//burada INSERT işlemi yapılacak yeni bir yorum ekleme
                        try {
                            // Örnek bir sorgu
                            String query = "Insert into comments VALUES(9,"+LoginPage.login_user_id+","+movie_id+",'"
                                    +binding.commentDescriptionUser.getText().toString()+"','"+ratingbar_user_score+"')";
                            PreparedStatement statement = connection.prepareStatement(query);
                            // Sorguyu çalıştır
                            int affectedRows = statement.executeUpdate();
                            if (affectedRows > 0) {
                                System.out.println("Başarılı");
                                Toast.makeText(view.getContext(),"Başarıyla Yorum Kaydedildi",Toast.LENGTH_LONG).show();
                                Intent intent=new Intent(view.getContext(), MainActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                System.out.println("Başarısız");
                                Toast.makeText(view.getContext(),"İşlem Gerçekleştirilemedi , Tekrar Deneyiniz",Toast.LENGTH_LONG).show();
                            }

                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    }

                }



            }
        });
        binding.cancelComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(user_role.equals("admin")){
                    Intent intent=new Intent(view.getContext(),AdminMainPage.class);
                    startActivity(intent);
                    finish();
                }
                else{
                    Intent intent=new Intent(view.getContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
        binding.ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                ratingbar_user_score=v;
            }
        });
        binding.deleteComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(user_role.equals("admin")){
                    //Database den silme işlemi yapılır burada
                    Intent intent=new Intent(view.getContext(),AdminMainPage.class);
                    startActivity(intent);
                    finish();
                    Toast.makeText(view.getContext(),"Yorum Başarıyla Silindi",Toast.LENGTH_SHORT).show();
                }
                else{
                    //Burada kullanıcı yorumunda değişiklik fln yapmışsa database de güncelleme yapılır.
                    Intent intent=new Intent(view.getContext(), MoviePage.class);
                    //burada intentin içine filmin id sini yerleştirip göndereceğiz(hangi filme yorum yapıyorsa).
                    startActivity(intent);
                    finish();
                    Toast.makeText(view.getContext(),"Yorum Başarıyla Silindi",Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}
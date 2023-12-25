package com.gokhanakbas.veritabanproje;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.widget.Toast;

import com.gokhanakbas.veritabanproje.databinding.ActivityCommentEditPageBinding;

public class CommentEditPage extends AppCompatActivity {
    ActivityCommentEditPageBinding binding;
    String user_role;
    float ratingbar_user_score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityCommentEditPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent=getIntent();

        if(intent!=null){
            user_role=intent.getStringExtra("user_role");
            if(user_role.equals("admin")){
                binding.saveComment.setText("Delete");
                binding.updateComment.setVisibility(View.INVISIBLE);
                binding.ratingBar.setEnabled(false);
                binding.commentDescriptionUser.setEnabled(false);
            }
        }

        binding.updateComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Burada kullanıcı yorumunda değişiklik fln yapmışsa database de güncelleme yapılır.
                //örneğin burada rating bar dan alınan değer kullanılarak database güncellenir.
                Intent intent=new Intent(view.getContext(), MoviePage.class);
                //buraya intentin içine filmin id sini yerleştirip göndereceğiz.
                startActivity(intent);
                finish();
                Toast.makeText(view.getContext(),"Yorum Başarıyla Güncellendi",Toast.LENGTH_SHORT).show();

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
                    Intent intent=new Intent(view.getContext(), MoviePage.class);
                    //buraya intentin içine filmin id sini yerleştirip göndereceğiz.
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
        binding.saveComment.setOnClickListener(new View.OnClickListener() {
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
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

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CommentEditPage extends AppCompatActivity {
    ActivityCommentEditPageBinding binding;
    Connection connection;
    String user_rolee;
    float ratingbar_user_score;
    int movie_id;
    int com_id;
    int edit_mode=0;
    String firstComment;
    float firstScore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityCommentEditPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        DBConnection connection1=new DBConnection();
        connection=connection1.DatabaseConnection();

        Intent intent=getIntent();

        if(intent!=null){
            Comment comment= (Comment) intent.getSerializableExtra("comment_object");
            movie_id= intent.getIntExtra("movie_id",0);
            if(comment!=null){
            edit_mode=1;
            com_id=comment.getComment_id();
            firstComment=comment.getComment_desc();
            firstScore=Float.parseFloat(comment.getComment_user_score());
            binding.comUserName.setText(comment.getComment_user_name());
            binding.commentDescriptionUser.setText(comment.getComment_desc());
            binding.ratingBar.setRating(Float.parseFloat(comment.getComment_user_score()));
            }
            else{
                binding.deleteComment.setVisibility(View.INVISIBLE);
            }
            //user_rolee=intent.getStringExtra("user_role");
            if(LoginPage.user_role.equals("Admin")){
                binding.ratingBar.setEnabled(false);
                binding.commentDescriptionUser.setEnabled(false);
                binding.saveComment.setVisibility(View.INVISIBLE);
            }
        }
        else{
            binding.deleteComment.setVisibility(View.INVISIBLE);
        }

        binding.saveComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Burada Temel database kodları alır
                boolean isItDone=addOrUpdateComment();
                if(isItDone){
                    if(LoginPage.user_role.equals("Admin")){
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
            }
        });
        binding.cancelComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(LoginPage.user_role.equals("Admin")){
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
                deleteComment();
                Toast.makeText(view.getContext(),"Yorum Başarıyla Silindi",Toast.LENGTH_SHORT).show();
                if(LoginPage.user_role.equals("Admin")){
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
    }
    public Boolean addOrUpdateComment(){
        //Edit mode a bakacak burada çünkü rol sadece user ise bu fonksiyon çalışacak


        if(edit_mode==0){
            ratingbar_user_score=binding.ratingBar.getRating();
            if(binding.commentDescriptionUser.getText().toString().isEmpty()||ratingbar_user_score==0
                    ||firstScore==ratingbar_user_score){
                Toast.makeText(this,"Boş Alan VEYA Değişiklik yapılmamış",Toast.LENGTH_LONG).show();
            }
            else {
                try {
                    String query = "INSERT INTO comments(com_user_id,com_movie_id,com_description,com_score) VALUES(?,?,?,?)";
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setInt(1, LoginPage.login_user_id);
                    preparedStatement.setInt(2, movie_id);
                    preparedStatement.setString(3, binding.commentDescriptionUser.getText().toString());
                    preparedStatement.setString(4, String.valueOf(ratingbar_user_score));

                    int affectedRows = preparedStatement.executeUpdate();
                    if (affectedRows > 0) {
                        System.out.println("Başarılı");
                        Toast.makeText(this, "Başarıyla Yorum Eklendi", Toast.LENGTH_LONG).show();
                        return true;
                    } else {
                        System.out.println("Başarısız");
                        Toast.makeText(this, "İşlem Gerçekleştirilemedi , Tekrar Deneyiniz", Toast.LENGTH_LONG).show();
                        return false;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        else {
            ratingbar_user_score=binding.ratingBar.getRating();
            if(binding.commentDescriptionUser.getText().toString().isEmpty()||ratingbar_user_score==0
                    ||firstComment.equals(binding.commentDescriptionUser.getText().toString())||firstScore==ratingbar_user_score){
                Toast.makeText(this,"Boş Alan VEYA Değişiklik yapılmamış",Toast.LENGTH_LONG).show();
            }
            else{
                try {
                String query2= "UPDATE comments SET com_description=?,com_score=? where com_id=" + com_id;
                PreparedStatement preparedStatement = connection.prepareStatement(query2);
                preparedStatement.setString(1, binding.commentDescriptionUser.getText().toString());
                preparedStatement.setString(2, String.valueOf(ratingbar_user_score));
                int affectedRows = preparedStatement.executeUpdate();
                if (affectedRows > 0) {
                    System.out.println("Başarılı");
                    Toast.makeText(this,"Başarıyla Yorum Güncellendi",Toast.LENGTH_LONG).show();
                    return true;
                } else {
                    System.out.println("Başarısız");
                    Toast.makeText(this,"İşlem Gerçekleştirilemedi , Tekrar Deneyiniz",Toast.LENGTH_LONG).show();
                    return false;
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        }
        return false;
    }
    public void deleteComment(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        connection= DBConnection.connection;
        try {

            String query = "DELETE FROM comments where com_id="+com_id;
            PreparedStatement statement = connection.prepareStatement(query);

            int affectedRows = statement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Başarılı Yorum Silme");
            } else {
                System.out.println("Başarısız Yorum Silme");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
package com.gokhanakbas.veritabanproje;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.gokhanakbas.veritabanproje.data.entity.entity.Comment;
import com.gokhanakbas.veritabanproje.databinding.ActivityCommentPageBinding;

public class CommentPage extends AppCompatActivity {

    ActivityCommentPageBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityCommentPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent=getIntent();
        if(intent!=null){
            Comment comment=(Comment) intent.getSerializableExtra("comment_object");
            binding.commentDescUser.setText(comment.getComment_desc());
            binding.commentScoreUser.setText(comment.getComment_user_score());
            binding.commentUserName.setText(String.valueOf(comment.getComment_user_name()));
            binding.deleteButtonComment.setVisibility(View.INVISIBLE);
        }

        binding.cancelButtonComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(v.getContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
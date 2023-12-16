package com.gokhanakbas.veritabanproje.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.gokhanakbas.veritabanproje.ActorPage;
import com.gokhanakbas.veritabanproje.data.entity.entity.Actor;
import com.gokhanakbas.veritabanproje.databinding.RecyclerRowActorBinding;
import com.gokhanakbas.veritabanproje.databinding.RecyclerRowActorMoviePageBinding;

import java.util.List;

public class ActorAdapter extends RecyclerView.Adapter<com.gokhanakbas.veritabanproje.adapter.ActorAdapter.ActorViewHolder>{

        private final Context mContext;
        private List<Actor> actor_list;

        public ActorAdapter(Context context, List<Actor> actors) {
            mContext = context;
            actor_list = actors;
        }

        @Override
        public com.gokhanakbas.veritabanproje.adapter.ActorAdapter.ActorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            RecyclerRowActorMoviePageBinding binding = RecyclerRowActorMoviePageBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
            return new com.gokhanakbas.veritabanproje.adapter.ActorAdapter.ActorViewHolder(binding);
        }

        @Override
        public void onBindViewHolder(com.gokhanakbas.veritabanproje.adapter.ActorAdapter.ActorViewHolder holder, int position) {
            Actor actor = actor_list.get(position);
            holder.binding.actorName.setText(actor.getActor_name());
        }

        @Override
        public int getItemCount() {
            return actor_list.size();
        }

        public class ActorViewHolder extends RecyclerView.ViewHolder {
            private final RecyclerRowActorMoviePageBinding binding;

            public ActorViewHolder(RecyclerRowActorMoviePageBinding binding) {
                super(binding.getRoot());
                this.binding = binding;

                binding.getRoot().setOnClickListener(new View.OnClickListener() {
                    int position = getAdapterPosition();
                    @Override
                    public void onClick(View v) {
                        Actor actor=actor_list.get(position);
                        Intent intent=new Intent(mContext, ActorPage.class);
                        intent.putExtra("actor_object",actor);
                        mContext.startActivity(intent);
                    }
                });
            }
        }
    }

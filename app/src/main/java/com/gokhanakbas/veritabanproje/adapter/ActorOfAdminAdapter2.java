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
import com.gokhanakbas.veritabanproje.databinding.RecyclerRowActorForAdminBinding;

import java.util.List;

public class ActorOfAdminAdapter2 extends RecyclerView.Adapter<ActorOfAdminAdapter2.ActorOfAdmin2ViewHolder> {
    
        private final Context mContext;
        private List<Actor> actor_list;

        public ActorOfAdminAdapter2(Context context, List<Actor> actors) {
            mContext = context;
            actor_list = actors;
        }

        @Override
        public ActorOfAdmin2ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            RecyclerRowActorForAdminBinding binding = RecyclerRowActorForAdminBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
            return new ActorOfAdmin2ViewHolder(binding);
        }

        @Override
        public void onBindViewHolder(ActorOfAdmin2ViewHolder holder, int position) {
            Actor actor = actor_list.get(position);
            holder.binding.actorNameForAdmin.setText(actor.getActor_name());
        }

        @Override
        public int getItemCount() {
            return actor_list.size();
        }

        public class ActorOfAdmin2ViewHolder extends RecyclerView.ViewHolder {

            private final RecyclerRowActorForAdminBinding binding;

            public ActorOfAdmin2ViewHolder(RecyclerRowActorForAdminBinding binding) {
                super(binding.getRoot());
                this.binding = binding;

                binding.getRoot().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position =getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION){
                            Actor actor=(Actor) actor_list.get(position);
                            Intent intent=new Intent(v.getContext(), ActorPage.class);
                            intent.putExtra("actor_object",actor);
                            intent.putExtra("user_role","admin");
                            mContext.startActivity(intent);
                        }
                    }
                });
            }
        }

}

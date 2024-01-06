package com.gokhanakbas.veritabanproje.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.gokhanakbas.veritabanproje.MovieCreateAndEdit;
import com.gokhanakbas.veritabanproje.data.entity.entity.Actor;
import com.gokhanakbas.veritabanproje.databinding.RecyclerRowActorBinding;

import java.util.ArrayList;
import java.util.List;

public class ActorOfAdminAdapter extends RecyclerView.Adapter<ActorOfAdminAdapter.ActorOfAdminViewHolder> {

        private final Context mContext;
        private List<Actor> actor_list;
        static String actor_name;

        public ActorOfAdminAdapter(Context context, List<Actor> actors) {
            mContext = context;
            actor_list = actors;
        }

        @Override
        public ActorOfAdminViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            RecyclerRowActorBinding binding = RecyclerRowActorBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
            return new ActorOfAdminViewHolder(binding);

        }

        @Override
        public void onBindViewHolder(ActorOfAdminViewHolder holder, int position) {
            Actor actor = actor_list.get(position);
            holder.binding.actorNameForAdmin.setText(actor.getActor_name());
        }

        @Override
        public int getItemCount() {
            return actor_list.size();
        }

        public class ActorOfAdminViewHolder extends RecyclerView.ViewHolder {

            private final RecyclerRowActorBinding binding;

            public ActorOfAdminViewHolder(RecyclerRowActorBinding binding) {
                super(binding.getRoot());
                this.binding = binding;

                binding.getRoot().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //bu aşamada moviedeki actor listesinden aktörün id sini kaldıracağız
                        actor_list.remove(getAdapterPosition());
                        MovieCreateAndEdit.actor_listID.remove(getAdapterPosition());
                        MovieCreateAndEdit.actor_listName.remove(getAdapterPosition());
                        notifyDataSetChanged();
                    }
                });
            }
        }


}

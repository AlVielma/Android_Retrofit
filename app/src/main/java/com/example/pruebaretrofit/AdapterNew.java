package com.example.pruebaretrofit;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterNew extends RecyclerView.Adapter<AdapterNew.ViewHolder>{
    List<Result> pokemonList;

    public AdapterNew(List<Result> pokemonList) {
        this.pokemonList = pokemonList;
    }

    @NonNull
    @Override
    public AdapterNew.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itempoke, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterNew.ViewHolder holder, int position) {
        Result pokemon = pokemonList.get(position);
        holder.setData(pokemon);
    }

    @Override
    public int getItemCount() {
        return pokemonList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView pokename;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            pokename = itemView.findViewById(R.id.pokename);
        }

        public void setData(Result pokemon) {
            pokename.setText(pokemon.getName());
        }
    }
}

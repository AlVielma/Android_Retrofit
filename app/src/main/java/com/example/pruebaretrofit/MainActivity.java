package com.example.pruebaretrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView rv = findViewById(R.id.recycler);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setHasFixedSize(true);

        // Configuración de Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Crear la interfaz para la llamada a la API
        PokeapiService pokeapiService = retrofit.create(PokeapiService.class);

        // Realizar la llamada para obtener la lista de Pokémon
        pokeapiService.getPokemonList().enqueue(new Callback<Pokeapi>() {
            @Override
            public void onResponse(Call<Pokeapi> call, Response<Pokeapi> response) {
                if (response.isSuccessful()) {
                    // Obtener la lista completa de Pokémon
                    List<Result> pokemonList = response.body().getResults();

                    // Mostrar solo los primeros tres Pokémon
                    List<Result> firstThreePokemon = pokemonList.subList(0, Math.min(3, pokemonList.size()));

                    // Configuración del adaptador
                    AdapterNew adapter = new AdapterNew(firstThreePokemon);
                    rv.setAdapter(adapter);
                } else {
                    // Manejar errores
                    String errorMessage = "Error: " + response.code();
                    Toast.makeText(MainActivity.this, errorMessage, Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<Pokeapi> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error de conexión: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
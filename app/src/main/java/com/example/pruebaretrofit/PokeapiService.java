package com.example.pruebaretrofit;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PokeapiService {
    @GET("pokemon")
    Call<Pokeapi> getPokemonList();
}

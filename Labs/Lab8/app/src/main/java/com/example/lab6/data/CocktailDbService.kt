package com.example.lab6.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CocktailDbService {
    @GET("api/json/v1/1/search.php")
    fun searchCocktails(@Query("s") searchTerm: String): Call<SearchResponse>

    @GET("api/json/v1/1/lookup.php")
    fun cocktailDetails(@Query("i") itemId: String): Call<SearchResponse>
}
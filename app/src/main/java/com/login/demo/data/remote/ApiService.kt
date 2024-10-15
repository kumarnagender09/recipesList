package com.login.demo.data.remote

import com.login.demo.data.models.RecipeResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("recipes/search")
    suspend fun searchRecipes(@Query("q") query: String): RecipeResponse
}
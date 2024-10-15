package com.login.demo.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.login.demo.data.models.Recipe
import com.login.demo.data.remote.RetrofitInstance
import kotlinx.coroutines.launch


class RecipeViewModel : ViewModel() {
    var recipeList by mutableStateOf<List<Recipe>>(emptyList())
    var searchQuery by mutableStateOf("")

    fun searchRecipes(query: String) {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.searchRecipes(query)
                recipeList = response.recipes
            } catch (e: Exception) {
                // Handle error
                recipeList = emptyList()
            }
        }
    }
}


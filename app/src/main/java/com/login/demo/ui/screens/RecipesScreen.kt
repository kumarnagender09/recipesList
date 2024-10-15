package com.login.demo.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.login.demo.data.models.Recipe

@Composable
fun RecipesScreen(viewModel: RecipeViewModel){
    var searchQuery by remember {
        mutableStateOf(TextFieldValue(""))
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Recipes")
        // Search bar
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { value ->
                searchQuery = value
                viewModel.searchRecipes(value.text)
            },
            label = { Text("Search Recipes") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))
        // Recipe list
        RecipeList(viewModel.recipeList)
    }
    DisposableEffect(Unit) {
        viewModel.searchRecipes("")
        onDispose {}
    }
}

@Composable
fun RecipeList(recipes: List<Recipe>) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(recipes.size) { index ->
            val recipe = recipes[index]
            RecipeItem(recipe)
            Divider()
        }
    }
}
@Composable
fun RecipeItem(recipe: Recipe) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)) {
        Text(text = recipe.name, style = MaterialTheme.typography.labelMedium)
        Text(text = "Ingredients: ${recipe.ingredients.joinToString(", ")}", style = MaterialTheme.typography.bodySmall)
    }
}

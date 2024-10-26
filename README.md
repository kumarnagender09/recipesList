# Recipe Search App

This project is a sample Android app built with Jetpack Compose that allows users to search for recipes using a search bar. It uses MVVM architecture and communicates with a remote API via Retrofit.

## Features

- **Recipe Search**: Users can enter a search query in the search bar to find recipes.
- **Recipe Display**: Recipes are displayed in a list format, showing the name and ingredients for each recipe.

## Project Structure

- **`com.login.demo.ui.screens`**: Contains UI components for displaying recipes and the `RecipeViewModel` for managing UI-related data.
- **`com.login.demo.data.remote`**: Contains networking components like `RetrofitInstance` and `ApiService` for making API calls.
- **`com.login.demo.data.models`**: Includes data models that define the structure of recipe data.

## Code Overview

### UI Components (`com.login.demo.ui.screens`)

- **`RecipesScreen`**: The main composable function for the screen, which includes:
  - A search bar for querying recipes.
  - A list of recipes displayed using the `RecipeList` composable.
  - `DisposableEffect` to trigger an initial search on the screen load.
- **`RecipeList`**: Displays a list of recipes using `LazyColumn`.
- **`RecipeItem`**: Displays individual recipe details, including the name and ingredients.

### ViewModel (`RecipeViewModel`)

- **`RecipeViewModel`**: Extends `ViewModel` and contains the logic for fetching and storing recipe data. It exposes a list of recipes and a function `searchRecipes` to handle the search functionality using a coroutine scope.

### Data and Networking (`com.login.demo.data.remote`)

- **`RetrofitInstance`**: A singleton object that configures and provides an instance of Retrofit for API calls.
- **`ApiService`**: Defines the API interface using Retrofit annotations. The `searchRecipes` function allows you to search for recipes by a query string.

## API Integration

The app fetches recipes from the [Dummy JSON API](https://dummyjson.com) (sample API) using Retrofit. 

### Example API Endpoint

- **Search Recipes**: `GET https://dummyjson.com/recipes/search?q={query}`

## Getting Started

### Prerequisites

- Android Studio
- Kotlin

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/recipe-search-app.git
   ```
2. Open the project in Android Studio.

3. Sync Gradle files to download dependencies.

### Usage

1. **Run the App**: Start the app on an emulator or physical device.
2. **Search for Recipes**: Enter a search term in the search bar, and the app will fetch matching recipes from the API.
3. **View Recipe Details**: Each recipe shows its name and ingredients.

## Sample Code

### `RecipesScreen`

```kotlin
@Composable
fun RecipesScreen(viewModel: RecipeViewModel) {
    var searchQuery by remember { mutableStateOf(TextFieldValue("")) }

    Column(
        modifier = Modifier.fillMaxWidth().padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Recipes")
        
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
        RecipeList(viewModel.recipeList)
    }

    DisposableEffect(Unit) {
        viewModel.searchRecipes("")
        onDispose {}
    }
}
```

### Error Handling

If the app fails to fetch recipes (for example, due to network issues), an empty list will be displayed.


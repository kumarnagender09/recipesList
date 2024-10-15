package com.login.demo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.login.demo.ui.screens.RecipeViewModel
import com.login.demo.ui.screens.RecipesScreen
import com.login.demo.ui.theme.DemoTheme

class MainActivity : ComponentActivity() {
    private val viewModel: RecipeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DemoTheme {
               RecipesScreen(viewModel)
            }
        }
    }
}



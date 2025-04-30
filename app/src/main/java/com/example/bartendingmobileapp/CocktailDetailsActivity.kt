package com.example.bartendingmobileapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text


class CocktailDetailsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val cocktailName = intent.getStringExtra("cocktail_name")
        val cocktailType = intent.getStringExtra("cocktail_type")

        val sourceList = when (cocktailType) {
            "alcoholic" -> alcoholicCocktails
            "non_alcoholic" -> nonAlcoholicCocktails
            else -> emptyList()
        }
        val cocktail = sourceList.find { it.name == cocktailName }


        Log.d("CocktailDetails", "Cocktail Name Passed: $cocktailName")
        setContent {
            MaterialTheme {
                if (cocktail != null) {
                    CocktailDetails(cocktail)
                } else {
                    Text("Cocktail not found")
                }
            }
    }
}}
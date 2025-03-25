package com.example.bartendingmobileapp

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.activity.ComponentActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.ui.text.font.FontWeight


class CocktailDetailsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val cocktailName = intent.getStringExtra("cocktail_name")
        Log.d("CocktailDetails", "Cocktail Name Passed: $cocktailName")
        setContent {
            MaterialTheme{
                CocktailDetails(cocktailName)
            }

        }
    }
}

@Composable
fun CocktailDetails(cocktailName: String?) {
    val cocktail = cocktails.find { it.name == cocktailName }
    if (cocktail == null) {
        Text("Can't find this cocktail", modifier = Modifier.padding(16.dp))
        return
    }

    LazyColumn(modifier = Modifier.padding(20.dp)) {
        item {
            Text(text = cocktail.name, style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Ingredients:",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold
            )
        }
        items(cocktail.ingredients.size) { index ->
            Text(text = "- ${cocktail.ingredients[index]}") }
        item {
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Recipe:",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold
            )
            Text(text = cocktail.recipe)
        }
    }
}


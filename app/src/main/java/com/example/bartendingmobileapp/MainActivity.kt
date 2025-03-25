package com.example.bartendingmobileapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


data class Cocktail(val name: String, val ingredients: List<String>, val recipe: String)

val cocktails = listOf(
    Cocktail("Mojito", listOf("60 ml white rum", "juice of 1 lime", "5 mint leaves", "1 tsp granulated sugar", "soda water to taste", "ice"),
        "STEP 1:\n Muddle the lime juice, sugar and mint leaves in a small jug, crushing the mint as you go – you can use the end of a rolling pin for this. Pour into a tall glass and add a handful of ice.\n" +
                "STEP 2:\n Pour over the rum, stirring with a long-handled spoon. Top up with soda water, garnish with mint and serve."+
    "\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na"),
    // Long recipe to test scrolling in LazyColumn
    Cocktail("Margarita", listOf("50 ml tequila", "20 ml triple Sec", "ice", "25 ml lime juice", "salt"),
        "STEP 1:\n Sprinkle a few teaspoons of salt over the surface of a small plate or saucer. Rub one wedge of lime along the rim of a tumbler and then dip it into the salt so that the entire rim is covered.\n" +
            "STEP 2:\n Fill a cocktail shaker with ice, then add the tequila, lime juice and triple sec. Shake until the outside of the shaker feels cold.\n" +
            "STEP 3:\n Strain the mix into the prepared glass over fresh ice. Serve with a wedge of lime."),
    Cocktail("Old Fashioned", listOf("60 ml whiskey", "2 tsp sugar", "1-2 dashes bitters", "splash of water", "soda water (optional)", "orange slice", "maraschino cherry (optional"),
        "STEP 1\n" +
                "Put the sugar, bitters and water in a small tumbler. Mix until the sugar dissolves if using granulated. Fill your glass with ice and stir in the whisky. Add a splash of soda water if you like and mix. Garnish with the orange and cherry."),
    Cocktail("Example1", listOf("ingredient1", "ingredient2", "ingredient3"), "recipe1"),
    Cocktail("Example2", listOf("ingredient1", "ingredient2", "ingredient3"), "recipe2"),
    Cocktail("Example3", listOf("ingredient1", "ingredient2", "ingredient3"), "recipe3"),
    Cocktail("Example4", listOf("ingredient1", "ingredient2", "ingredient3"), "recipe4"),
    Cocktail("Example5", listOf("ingredient1", "ingredient2", "ingredient3"), "recipe5"),
    Cocktail("Example6", listOf("ingredient1", "ingredient2", "ingredient3"), "recipe6"),
    Cocktail("Example7", listOf("ingredient1", "ingredient2", "ingredient3"), "recipe7"),
    Cocktail("Example8", listOf("ingredient1", "ingredient2", "ingredient3"), "recipe8"),
    Cocktail("Example9", listOf("ingredient1", "ingredient2", "ingredient3"), "recipe9"),
    Cocktail("Example10", listOf("ingredient1", "ingredient2", "ingredient3"), "recipe10"),
    Cocktail("Example11", listOf("ingredient1", "ingredient2", "ingredient3"), "recipe11"),
    Cocktail("Example12", listOf("ingredient1", "ingredient2", "ingredient3"), "recipe12"),
    Cocktail("Example13", listOf("ingredient1", "ingredient2", "ingredient3"), "recipe13")
)



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                CocktailList()
            }
        }
    }
}

@Composable
fun CocktailList() {
    val context = LocalContext.current
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(cocktails) { cocktail ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable {
                        val intent = Intent(context, CocktailDetailsActivity::class.java).apply {
                            putExtra("cocktail_name", cocktail.name)
                        }
                        context.startActivity(intent)
                    },
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = cocktail.name, style = MaterialTheme.typography.headlineSmall)
                }
            }
        }
    }
}



@Preview
@Composable
fun PreviewCocktailList() {
    MaterialTheme {
        CocktailList()
    }
}
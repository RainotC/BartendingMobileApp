package com.example.bartendingmobileapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp


data class Cocktail(val name: String, val ingredients: List<String>, val recipe: String)

val cocktails = listOf(
    Cocktail("Mojito", listOf("60 ml white rum", "juice of 1 lime", "5 mint leaves", "1 tsp granulated sugar", "soda water to taste", "ice"),
        "STEP 1:\n Muddle the lime juice, sugar and mint leaves in a small jug, crushing the mint as you go – you can use the end of a rolling pin for this. Pour into a tall glass and add a handful of ice.\n" +
                "STEP 2:\n Pour over the rum, stirring with a long-handled spoon. Top up with soda water, garnish with mint and serve."+
                "\nText to show that scrolling is possible\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na"),
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
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    val configuration = LocalConfiguration.current
    val context = LocalContext.current
    var selectedCocktailList by remember { mutableStateOf<List<Cocktail>>(emptyList()) }

    BackHandler(enabled = selectedCocktailList.isNotEmpty()) {
        selectedCocktailList= selectedCocktailList.dropLast(1)
    }

    if (configuration.screenWidthDp>600) {
        Row(modifier = Modifier.fillMaxSize()) {
            Box(modifier = Modifier.weight(1f)) {
                CocktailList { cocktail ->
                    selectedCocktailList = selectedCocktailList + cocktail }
            }
            Box(modifier = Modifier.weight(2f)) {
                selectedCocktailList.lastOrNull()?.let { CocktailDetails(it.name) }
            }
        }
    } else {
        CocktailList { cocktail ->
            val intent = Intent(context, CocktailDetailsActivity::class.java).apply {
                putExtra("cocktail_name", cocktail.name)
            }
            context.startActivity(intent)
        }
    }
}


@Composable
fun CocktailList(onCocktailSelected: (Cocktail) -> Unit) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(cocktails) { cocktail ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable { onCocktailSelected(cocktail) },
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = cocktail.name, style = MaterialTheme.typography.headlineSmall)
                }
            }
        }
    }
}
package com.example.bartendingmobileapp

import androidx.annotation.DrawableRes

data class Cocktail(val name: String, @DrawableRes val imageResId: Int, val ingredients: List<String>, val recipe: String)

val alcoholicCocktails = listOf(
    Cocktail("Mojito", R.drawable.mohito, listOf("60 ml white rum", "juice of 1 lime", "5 mint leaves", "1 tsp granulated sugar", "soda water to taste", "ice"),
        "STEP 1:\n Muddle the lime juice, sugar and mint leaves in a small jug, crushing the mint as you go – you can use the end of a rolling pin for this. Pour into a tall glass and add a handful of ice.\n" +
                "STEP 2:\n Pour over the rum, stirring with a long-handled spoon. Top up with soda water, garnish with mint and serve."+
                "\nText to show that scrolling is possible\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na"),
    Cocktail("Margarita", R.drawable.margarita, listOf("50 ml tequila", "20 ml triple Sec", "ice", "25 ml lime juice", "salt"),
        "STEP 1:\n Sprinkle a few teaspoons of salt over the surface of a small plate or saucer. Rub one wedge of lime along the rim of a tumbler and then dip it into the salt so that the entire rim is covered.\n" +
                "STEP 2:\n Fill a cocktail shaker with ice, then add the tequila, lime juice and triple sec. Shake until the outside of the shaker feels cold.\n" +
                "STEP 3:\n Strain the mix into the prepared glass over fresh ice. Serve with a wedge of lime."),
    Cocktail("Old Fashioned", R.drawable.old_fashioned, listOf("60 ml whiskey", "2 tsp sugar", "1-2 dashes bitters", "splash of water", "soda water (optional)", "orange slice", "maraschino cherry (optional"),
        "STEP 1\n" +
                "Put the sugar, bitters and water in a small tumbler. Mix until the sugar dissolves if using granulated. Fill your glass with ice and stir in the whisky. Add a splash of soda water if you like and mix. Garnish with the orange and cherry."),
    Cocktail("Example1", R.drawable.pink_drink, listOf("ingredient1", "ingredient2", "ingredient3"), "recipe1"),
    Cocktail("Example2", R.drawable.pink_drink, listOf("ingredient1", "ingredient2", "ingredient3"), "recipe2"),
    Cocktail("Example3", R.drawable.pink_drink, listOf("ingredient1", "ingredient2", "ingredient3"), "recipe3"),
    Cocktail("Example4",  R.drawable.pink_drink,listOf("ingredient1", "ingredient2", "ingredient3"), "recipe4"),
    Cocktail("Example5",  R.drawable.pink_drink,listOf("ingredient1", "ingredient2", "ingredient3"), "recipe5"),
    Cocktail("Example6", R.drawable.pink_drink, listOf("ingredient1", "ingredient2", "ingredient3"), "recipe6"),
    Cocktail("Example7", R.drawable.pink_drink, listOf("ingredient1", "ingredient2", "ingredient3"), "recipe7"),
    Cocktail("Example8", R.drawable.pink_drink, listOf("ingredient1", "ingredient2", "ingredient3"), "recipe8"),
    Cocktail("Example9", R.drawable.pink_drink, listOf("ingredient1", "ingredient2", "ingredient3"), "recipe9"),
    Cocktail("Example10", R.drawable.pink_drink, listOf("ingredient1", "ingredient2", "ingredient3"), "recipe10"),
    Cocktail("Example11",  R.drawable.pink_drink,listOf("ingredient1", "ingredient2", "ingredient3"), "recipe11"),
    Cocktail("Example12", R.drawable.pink_drink, listOf("ingredient1", "ingredient2", "ingredient3"), "recipe12"),
    Cocktail("Example13",  R.drawable.pink_drink,listOf("ingredient1", "ingredient2", "ingredient3"), "recipe13"),
    Cocktail("Example14",  R.drawable.pink_drink,listOf("ingredient1", "ingredient2", "ingredient3"), "recipe14"),
    Cocktail("Example15",  R.drawable.pink_drink, listOf("ingredient1", "ingredient2", "ingredient3"), "recipe15")
)


val nonAlcoholicCocktails = listOf(
    Cocktail("Virgin Mojito", R.drawable.mohito, listOf("60 ml non alcoholic white rum", "juice of 1 lime", "5 mint leaves", "1 tsp granulated sugar", "soda water to taste", "ice"),
        "STEP 1:\n Muddle the lime juice, sugar and mint leaves in a small jug, crushing the mint as you go – you can use the end of a rolling pin for this. Pour into a tall glass and add a handful of ice.\n" +
                "STEP 2:\n Pour over the rum, stirring with a long-handled spoon. Top up with soda water, garnish with mint and serve."+
                "\nText to show that scrolling is possible\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na"),
    Cocktail("Frappe", R.drawable.frappe, listOf("2 double shots cold espresso, or 1/2 cup cold prepared instant espresso or strong brewed coffee", "1/4 cup whole or 2% milk", "2 tablespoons packed light brown sugar", "1/4 teaspoon vanilla extract", "4 ounces ice cubes (about 1 cup)", "Whipped cream, for serving (optional)"),
        "STEP 1:\n Place 2 double shots cold espresso, or 1/2 cup cold prepared instant espresso or strong brewed coffee, 1/4 cup whole or 2% milk, 2 tablespoons packed light brown sugar, 1/4 teaspoon vanilla extract, and 4 ounces (about 1 cup) ice cubes in a blender. Begin on low speed and increasing to high speed, blend until thick, smooth, and creamy, 20 to 40 seconds.\n" +
                "STEP 2:\n Pour into a glass and top with whipped cream if desired.\n" +
                "STEP 3:\n Strain the mix into the prepared glass over fresh ice. Serve with a wedge of lime."),
    Cocktail("Virgin Old Fashioned", R.drawable.old_fashioned, listOf("60 ml non alcoholic whiskey", "2 tsp sugar", "1-2 dashes bitters", "splash of water", "soda water (optional)", "orange slice", "maraschino cherry (optional"),
        "STEP 1\n" +
                "Put the sugar, bitters and water in a small tumbler. Mix until the sugar dissolves if using granulated. Fill your glass with ice and stir in the whisky. Add a splash of soda water if you like and mix. Garnish with the orange and cherry."),
    Cocktail("Example1", R.drawable.pink_drink, listOf("ingredient1", "ingredient2", "ingredient3"), "recipe1"),
    Cocktail("Example2", R.drawable.pink_drink, listOf("ingredient1", "ingredient2", "ingredient3"), "recipe2"),
    Cocktail("Example3", R.drawable.pink_drink, listOf("ingredient1", "ingredient2", "ingredient3"), "recipe3"),
    Cocktail("Example4",  R.drawable.pink_drink,listOf("ingredient1", "ingredient2", "ingredient3"), "recipe4"),
    Cocktail("Example5",  R.drawable.pink_drink,listOf("ingredient1", "ingredient2", "ingredient3"), "recipe5"),
    Cocktail("Example6", R.drawable.pink_drink, listOf("ingredient1", "ingredient2", "ingredient3"), "recipe6"),
    Cocktail("Example7", R.drawable.pink_drink, listOf("ingredient1", "ingredient2", "ingredient3"), "recipe7"))


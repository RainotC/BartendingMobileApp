package com.example.bartendingmobileapp

import android.content.Intent
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlin.collections.plus


@Composable
fun CocktailView(cocktailList: List<Cocktail>, drawerState : DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed), pagerState: com.google.accompanist.pager.PagerState = rememberPagerState()) {
    val context = LocalContext.current
    val configuration = LocalConfiguration.current
    val isTablet = configuration.screenWidthDp > 600 && configuration.screenHeightDp > 480
    var selectedCocktailList by remember { mutableStateOf<List<Cocktail>>(emptyList()) }



    BackHandler(enabled = drawerState.isOpen || selectedCocktailList.isNotEmpty()) {
        if(
            selectedCocktailList.isNotEmpty() ){
                selectedCocktailList = selectedCocktailList.dropLast(1)

        }
    }
    if (isTablet) {
        Row(modifier = Modifier.fillMaxSize()) {
            Box(modifier = Modifier.weight(1f)) {
                CocktailGrid(
                    cocktails = cocktailList,
                    onCocktailSelected = { cocktail ->
                        selectedCocktailList = selectedCocktailList + cocktail
                    }
                )
            }
            Box(modifier = Modifier.weight(1.5f)) {
                selectedCocktailList.lastOrNull()?.let {
                    CocktailDetails(it, pagerState)
                }
            }
        }
    } else {

        CocktailGrid(
            cocktails = cocktailList,
            onCocktailSelected = { cocktail ->
                context.startActivity(Intent(context, CocktailDetailsActivity::class.java).apply {
                    putExtra("cocktail_name", cocktail.name)
                    putExtra("cocktail_type", if (cocktailList == alcoholicCocktails) "alcoholic" else "non_alcoholic")
                    putExtra("current_page", pagerState.currentPage)
                })
            }
        )
    }
}


@Composable
fun CocktailGrid(cocktails: List<Cocktail>,
                 onCocktailSelected: (Cocktail) -> Unit,
                 modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 150.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(cocktails) { cocktail ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable { onCocktailSelected(cocktail) },
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Image(
                        painter = painterResource(id = cocktail.imageResId),
                        contentDescription = cocktail.name,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(120.dp)
                            .clip(MaterialTheme.shapes.medium),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = cocktail.name, style = MaterialTheme.typography.headlineSmall)
                }
            }
        }
    }
}

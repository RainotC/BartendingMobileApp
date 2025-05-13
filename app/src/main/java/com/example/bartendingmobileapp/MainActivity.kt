package com.example.bartendingmobileapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.example.bartendingmobileapp.ui.theme.BartendingMobileAppTheme
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch


enum class AppTab(val title: String) {
    Info("Info"),
    Alcoholic("Alcoholic"),
    NonAlcoholic("Non-Alcoholic")
}


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BartendingMobileAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.primary
                ) {
                    Column(modifier = Modifier.fillMaxSize()) {

                        MainScreen()
                    }
                }
            }
        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val pagerState = rememberPagerState(initialPage = 0)
    val tabs = AppTab.entries
    val coroutineScope = rememberCoroutineScope()
    val configuration = LocalConfiguration.current
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    //var selectedTab by remember { mutableStateOf(AppTab.Info) }
    NavigationDrawer(
        drawerState = drawerState,
        onItemSelected = { item ->
            when (item) {
                "Main menu" -> {
                    coroutineScope.launch {
                        drawerState.close()
                        pagerState.animateScrollToPage(0)
                    }
                }
            }
        }
    ) {
    Scaffold(
        topBar = {
                TopAppBar(
                    title = { Text("Bartending App") },
                    navigationIcon = {
                        IconButton(onClick = {
                            coroutineScope.launch {
                                drawerState.open()
                            }
                        }) {
                            Icon(Icons.Default.Menu, contentDescription = "Open navigation drawer")
                        }
                    }
                )

            TabRow(selectedTabIndex = pagerState.currentPage) {
                AppTab.entries.forEachIndexed { index, tab ->
                    Tab(
                        selected = pagerState.currentPage == index,
                        onClick = {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(index)
                            }
                        },
                        text = { Text(tab.title) }
                    )
                }
            }
        },
        modifier = Modifier
            .fillMaxSize()
            .padding(WindowInsets.systemBars.asPaddingValues())
    ) { innerPadding ->
        HorizontalPager(
            count = tabs.size,
            state = pagerState,
            modifier = Modifier.padding(innerPadding)
        ) { page ->
            when (tabs[page]) {
                AppTab.Info -> InfoScreen()
                AppTab.Alcoholic -> CocktailView(alcoholicCocktails, drawerState)
                AppTab.NonAlcoholic -> CocktailView(nonAlcoholicCocktails, drawerState)

            }
        }
    }
}
}

package com.example.bartendingmobileapp

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import com.example.bartendingmobileapp.ui.theme.BartendingMobileAppTheme
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import kotlin.math.abs
import kotlin.math.sqrt

enum class AppTab(val title: String) {
    Info("Info"),
    Alcoholic("Alcoholic"),
    NonAlcoholic("Non-Alcoholic")
}

class MainActivity : ComponentActivity(){


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
    val scope = rememberCoroutineScope()
    val configuration = LocalConfiguration.current
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)


    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            NavigationDrawer(
                pagerState = pagerState,
                scope = scope,
                onBackClick = {
                    scope.launch {
                        drawerState.close()
                    }
                },
                onNavigationItemSelected = { page ->
                    scope.launch {
                        pagerState.animateScrollToPage(page)
                        pagerState.currentPage == page
                        drawerState.close()
                    }
                }
            )
        },
        gesturesEnabled = true
    ) {
        Scaffold(
            topBar = {
                val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
                NavigationBar(
                    onListClick = {
                        scope.launch {
                            drawerState.open()
                        }
                    },
                    scrollBehavior = scrollBehavior
                )
                TabRow(selectedTabIndex = pagerState.currentPage) {
                    AppTab.entries.forEachIndexed { index, tab ->
                        Tab(
                            selected = pagerState.currentPage == index,
                            onClick = {
                                scope.launch {
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
                    AppTab.Alcoholic -> CocktailView(alcoholicCocktails, drawerState, pagerState)
                    AppTab.NonAlcoholic -> CocktailView(nonAlcoholicCocktails, drawerState, pagerState)

                }
            }
        }
    }
}

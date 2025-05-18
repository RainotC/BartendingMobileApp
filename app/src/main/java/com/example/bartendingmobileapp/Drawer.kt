package com.example.bartendingmobileapp

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationBar(onListClick: () -> Unit, scrollBehavior: TopAppBarScrollBehavior, exampleText: String = "Cocktail Bar") {
    val scrollBehavior =  TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState());

    TopAppBar(
        actions = {
            IconButton(onClick = onListClick) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "List"
                )
            }
        },
        title = {
            Text(
                text = exampleText,
                style = MaterialTheme.typography.headlineSmall
            )
        },
        scrollBehavior = scrollBehavior
    )
}




@Composable
fun NavigationDrawer(
    pagerState: com.google.accompanist.pager.PagerState,
    onBackClick: () -> Unit,
    onNavigationItemSelected: (Int) -> Unit = {},
    scope: CoroutineScope

) {

    ModalDrawerSheet(
        drawerContainerColor = MaterialTheme.colorScheme.primary
    ){
        Row {
            IconButton(
                onClick = onBackClick
            ) {
                Icon(
                    Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back"
                )
            }
            Text(
                "Cocktail Bar",
                modifier = Modifier.padding(16.dp)
            )
        }
        NavigationDrawerItem(
            label = {
                Text(
                    text = "Home"
                )
            },
            selected = false,
            onClick = {
                scope.launch {
                    onBackClick()
                    onNavigationItemSelected(0)
                }
            }
        )
    }
}
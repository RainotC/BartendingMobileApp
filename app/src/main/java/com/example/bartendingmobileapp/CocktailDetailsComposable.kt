package com.example.bartendingmobileapp

import android.R.attr.content
import android.R.id.content
import android.content.Intent
import android.widget.Toast
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CocktailDetails(cocktail: Cocktail) {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    val context = LocalContext.current
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val expandedImageHeight = (screenHeight / 2) - 88.dp
    val isTablet = LocalConfiguration.current.screenWidthDp > 600

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val mainContent = @Composable {
        Scaffold(
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                Box {
                    val height by animateDpAsState(
                        targetValue = 88.dp + (expandedImageHeight * (1f - scrollBehavior.state.collapsedFraction)),
                        animationSpec = tween(300)
                    )

                    Image(
                        painter = painterResource(cocktail.imageResId),
                        contentDescription = cocktail.name,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(height)
                    )
                    TopAppBar(
                        title = { Text(cocktail.name) },
                        navigationIcon = {
                            if (isTablet) {null}
                            else{
                                IconButton(onClick = { scope.launch { drawerState.open() } }) {
                                    Icon(Icons.Default.Menu, contentDescription = "Menu")
                                }
                            }
                        },
                        scrollBehavior = scrollBehavior,
                        modifier = Modifier.fillMaxWidth()
                    )

                }
            },

            floatingActionButton = {
                FloatingActionButton(onClick = {
                    Toast.makeText(
                        context,
                        "Ingredients: ${cocktail.ingredients.joinToString(", ")}",
                        Toast.LENGTH_LONG
                    ).show()
                }) {
                    Icon(Icons.Default.Send, contentDescription = "Send SMS")
                }
            }
        ) { innerPadding ->
            DetailsDisplay(innerPadding, cocktail)
        }
    }
    if (isTablet) {
        mainContent()
    } else {
        NavigationDrawer(
            drawerState = drawerState,
            onItemSelected = { item ->
                when (item) {
                    "Main menu" -> {
                        scope.launch {
                            drawerState.close()
                            context.startActivity(Intent(context, MainActivity::class.java))
                        }
                    }
                }
            },
            content = mainContent
        )
    }
}


@Composable
fun DetailsDisplay(innerPadding: PaddingValues, cocktail: Cocktail){

        val configuration = LocalConfiguration.current
        if (configuration.screenWidthDp > configuration.screenHeightDp) {
            // Horizontal view
            Row(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.tertiary)) {
                LazyColumn(modifier = Modifier.weight(1f).padding(5.dp)) {
                    item {
                        Spacer(modifier = Modifier.height(100.dp))
                        Text(
                            text = cocktail.name,
                            style = MaterialTheme.typography.headlineMedium
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "Ingredients:",
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    items(cocktail.ingredients.size) { index ->
                        Text(text = "- ${cocktail.ingredients[index]}")
                    }
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
                Column(
                    modifier = Modifier
                        .weight(0.5f)
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Timer:",
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(16.dp)
                    )
                    Timer(minutes = 0, seconds = 30)
                }
            }
        } else {
            // Vertical view
            Column(modifier = Modifier.fillMaxSize()) {
                LazyColumn(modifier = Modifier.weight(1f).padding(20.dp)) {
                    item {
                        Text(
                            text = cocktail.name,
                            style = MaterialTheme.typography.headlineMedium
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "Ingredients:",
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    items(cocktail.ingredients.size) { index ->
                        Text(text = "- ${cocktail.ingredients[index]}")
                    }
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
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Timer:",
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(16.dp)
                    )
                    Timer(minutes = 0, seconds = 30)
                }
            }
        }

}

@Composable
fun Timer(minutes: Int, seconds: Int) {
    var isRunning by remember { mutableStateOf(false) }
    var isFinished by remember { mutableStateOf(false) }
    var timeLeftMinutes by remember { mutableStateOf(minutes) }
    var timeLeftSeconds by remember { mutableStateOf(seconds) }
    var inputMinutes by remember { mutableStateOf(minutes) }
    var inputSeconds by remember { mutableStateOf(seconds) }

    Column(modifier = Modifier
        .fillMaxWidth()
        .clip(MaterialTheme.shapes.medium)
        .background(MaterialTheme.colorScheme.background)
        .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        Row {
            BasicTextField(
                value = inputMinutes.toString(),
                onValueChange = { value ->
                    inputMinutes = value.filter { it.isDigit() }.toIntOrNull() ?: 0
                    timeLeftMinutes = inputMinutes
                },
                modifier = Modifier.width(50.dp).padding(end = 8.dp),
                textStyle = MaterialTheme.typography.headlineMedium
            )
            Text(":", style = MaterialTheme.typography.headlineMedium)
            BasicTextField(
                value = inputSeconds.toString(),
                onValueChange = { value ->
                    inputSeconds = value.filter { it.isDigit() }.toIntOrNull()?.coerceAtMost(59) ?: 0
                    timeLeftSeconds = inputSeconds
                },
                modifier = Modifier.width(50.dp).padding(start = 8.dp),
                textStyle = MaterialTheme.typography.headlineMedium
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = String.format("%02d:%02d", timeLeftMinutes, timeLeftSeconds),
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(10.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = {
            isRunning = !isRunning
            isFinished = false
        }) {
            Text(if (isRunning) "Pause" else "Start")
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            if(isFinished){
                "Time's up!"
            } else {
                if (isRunning) {
                    "Timer is running"
                } else {
                    "Timer is paused"
                }
            }
        )
    }

    LaunchedEffect(isRunning) {
        while (isRunning && (timeLeftMinutes > 0 || timeLeftSeconds > 0)) {
            delay(1000)
            if (timeLeftSeconds > 0) {
                timeLeftSeconds--
                if (timeLeftSeconds == 0 && timeLeftMinutes == 0) {
                    isFinished = true
                    isRunning = false
                    timeLeftMinutes = inputMinutes
                    timeLeftSeconds = inputSeconds
                }
            } else {
                timeLeftMinutes--
                timeLeftSeconds = 59

            }
        }
    }
}
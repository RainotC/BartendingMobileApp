package com.example.bartendingmobileapp

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay


@Composable
fun CocktailDetails(cocktail: Cocktail) {
    val context = LocalContext.current
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                Toast.makeText(
                    context,
                    "Ingredients: ${cocktail.ingredients.joinToString(", ")}",
                    Toast.LENGTH_LONG
                ).show()
            }){
                Icon(Icons.Default.Send, contentDescription = "Send SMS")
            }
        }
    ) { innerPadding ->
    Box(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.tertiary)
        .padding(innerPadding)
        .padding(5.dp)
        ) {
        val configuration = LocalConfiguration.current
        if (configuration.screenWidthDp > configuration.screenHeightDp) {
            // Horizontal view
            Row(modifier = Modifier.fillMaxSize()) {
                LazyColumn(modifier = Modifier.weight(1f).padding(5.dp)) {
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
                        Image(
                            painter = painterResource(id = cocktail.imageResId),
                            contentDescription = cocktail.name,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(120.dp)
                                .clip(MaterialTheme.shapes.medium),
                            contentScale = ContentScale.Crop
                        )
                        Text(text = cocktail.name, style = MaterialTheme.typography.headlineMedium)
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
}}



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
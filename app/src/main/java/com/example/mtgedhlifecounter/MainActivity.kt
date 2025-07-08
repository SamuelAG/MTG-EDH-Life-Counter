package com.example.mtgedhlifecounter

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.LocalActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mtgedhlifecounter.ui.theme.MTGEDHLifeCounterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MTGEDHLifeCounterTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LockScreenOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)
                   MainScreen()
                }
            }
        }
    }
}

@Composable
fun LockScreenOrientation(orientation: Int) {
    val activity = LocalActivity.current
    DisposableEffect(orientation) {
        val activity = activity ?: return@DisposableEffect onDispose {}
        val originalOrientation = activity.requestedOrientation
        activity.requestedOrientation = orientation
        onDispose {
            // restore original orientation when view disappears
            activity.requestedOrientation = originalOrientation
        }
    }
}

@Composable
fun PlayerHP(hp: Int, modifier: Modifier = Modifier) {
    var playerhp by remember { mutableStateOf(40) }
    Row (
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        TextButton(
            onClick = { playerhp++
                        if(playerhp == 0) {
                            playerhp = 100
                        }
                      },
            modifier = Modifier.weight(2f)
        ) {
            Icon(
                Icons.Filled.Add,
                null,
                Modifier.background(Color.Transparent).size(80.dp),
                tint = Color.Black
            )
        }
        Text(
            modifier = Modifier.weight(2f),
            text = playerhp.toString(),
            fontSize = 120.sp,
            color = Color.Black,
            textAlign = TextAlign.Center
        )
        TextButton(
            onClick = { playerhp--
                        if(playerhp == 0) {
                            playerhp = 100
                        }
                      },
            modifier = Modifier.weight(2f)
        ) {
            val icon = painterResource(R.drawable.icons8_minus_24)
            Icon(
                icon,
                null,
                Modifier.background(Color.Transparent).size(80.dp),
                tint = Color.Black
            )
        }
    }
}

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    Column (
        modifier
            .fillMaxSize()
            .padding(16.dp)
            .clip(RoundedCornerShape(20.dp)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row (
            Modifier.weight(0.5f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            PlayerHP(40, modifier.weight(0.5f)
                .background(Color.Red).fillMaxSize())
            PlayerHP(32, modifier.weight(0.5f)
                .background(Color.Cyan).fillMaxSize())
        }
        Row (
            Modifier.weight(0.5f)
        ) {
            PlayerHP(8, modifier.weight(0.5f)
                .background(Color.Yellow).fillMaxSize())
            PlayerHP(27, modifier.weight(0.5f)
                .background(Color.Green).fillMaxSize())
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MTGEDHLifeCounterTheme {
        MainScreen()
    }
}
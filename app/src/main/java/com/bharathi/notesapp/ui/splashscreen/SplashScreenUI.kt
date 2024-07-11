package com.bharathi.notesapp.ui.splashscreen

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bharathi.notesapp.R
import com.bharathi.notesapp.ui.theme.splasBgColor
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navigate: () -> Unit) {
    val alpha = remember {
        Animatable(initialValue = 0F)
    }
    LaunchedEffect(key1 = true){
        delay(2000)
        alpha.animateTo(targetValue = 1f, animationSpec = tween(1500))
        navigate()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = splasBgColor), contentAlignment = Alignment.Center
    ) {
        Column {
            Box(modifier = Modifier.alpha(alpha.value), contentAlignment = Alignment.Center) {
                Image(
                    painter = painterResource(id = R.drawable.sticky_note_app_icon),
                    contentDescription = null,
                    alignment = Alignment.Center,
                    modifier = Modifier
                        .width(70.dp)
                        .height(70.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.baseline_edit_24),
                    contentDescription = null,
                    modifier = Modifier
                        .width(40.dp)
                        .height(40.dp)
                        .align(Alignment.TopEnd)
                )
            }
            Text(text = stringResource(R.string.notes_app), modifier = Modifier.alpha(alpha.value))
        }
    }
}

@Preview
@Composable
fun SplashScreenPreview() {
    SplashScreen(){

    }
}
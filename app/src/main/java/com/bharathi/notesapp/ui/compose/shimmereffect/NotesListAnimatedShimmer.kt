package com.bharathi.notesapp.ui.compose.shimmereffect

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.bharathi.notesapp.ui.compose.allnotes.ShimmerNoteScreen


@Composable
internal fun NotesListAnimatedShimmer() {
    val shimmerColor = listOf(
        Color.Gray.copy(alpha = 0.6f),
        Color.Gray.copy(alpha = 0.2f),
        Color.Gray.copy(alpha = 0.6f)
    )
    val transition = rememberInfiniteTransition()
    val translateAnim = transition.animateFloat(
        initialValue = 0f,
        targetValue =  1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000,easing = FastOutSlowInEasing)
        ), label = ""
    )
    val brush = Brush.linearGradient(
        colors = shimmerColor,
        start = Offset.Zero,
        end = Offset(x = translateAnim.value, y = translateAnim.value)
    )

    ShimmerNoteScreen(brush = brush)
}

@Preview(showBackground = true,uiMode = UI_MODE_NIGHT_YES)
@Composable
fun NotesListAnimatedShimmerPreview(){
    NotesListAnimatedShimmer()
}
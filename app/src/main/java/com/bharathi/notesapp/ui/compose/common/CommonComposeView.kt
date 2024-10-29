package com.bharathi.notesapp.ui.compose.common

import androidx.compose.foundation.Image
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter


@Composable
fun ShowIconButtonWithImage(onclick:() -> Unit,imageResources: Painter,imageModifier: Modifier,iconModifier: Modifier,alignMent: Alignment){
    IconButton(onClick = { onclick() }, modifier = iconModifier) {
        Image(
            painter =imageResources,
            contentDescription = null,
            modifier = imageModifier,
            alignment = alignMent
        )
    }
}
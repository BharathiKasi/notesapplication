package com.bharathi.notesapp.ui.compose.allnotes

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bharathi.notesapp.R


@Composable
fun CreateYourFirstNoteScreen(userName:String?){
    Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize()){
        Box(modifier = Modifier, contentAlignment = Alignment.Center) {
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
        Text(buildAnnotatedString {
            append(
                """Hello ${userName.orEmpty()} 
   Create your first note!!!""".trimMargin()
            )
            addStyle(
                style = SpanStyle(fontWeight = FontWeight.Bold),
                start = userName?.let {
                    6
                } ?: 0,
                end = userName?.length?.plus(6) ?: 0
            )
        }, textAlign = TextAlign.Center)
    }
}

@Preview(showBackground = true)
@Composable
fun CreateYourFirstNoteScreenPreview(){
    CreateYourFirstNoteScreen("Bharathi San")
}

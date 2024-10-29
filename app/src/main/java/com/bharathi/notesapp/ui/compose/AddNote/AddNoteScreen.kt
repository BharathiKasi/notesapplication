package com.bharathi.notesapp.ui.compose.AddNote

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bharathi.notesapp.ui.theme.splasBgColor
import com.bharathi.notesapp.R
import com.bharathi.notesapp.domain.model.NoteUi
import com.bharathi.notesapp.ui.compose.common.ShowIconButtonWithImage


@Composable
fun AddNoteScreen(onbackClick: () -> Unit,save:(NoteUi)-> Unit) {
    var noteContent by remember {
        mutableStateOf("")
    }
    BackHandler {
        onbackClick()
    }
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .windowInsetsPadding(WindowInsets.safeDrawing)
            .fillMaxSize()
            .background(color = splasBgColor)
    ) {
        AddNoteHeaderView(onbackClick = {
            onbackClick()
        }, onSaveButtonClick = { title ->
            save(NoteUi(title = title, content = noteContent))
        })
        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp), color = Color.Black
        )
        AddNoteContainerView(updateContent = { content ->
            noteContent = content
            Toast.makeText(context,"Add new content",Toast.LENGTH_SHORT).show()
        })


    }
}

@Composable
fun AddNoteHeaderView(onbackClick: () -> Unit, onSaveButtonClick: (String) -> Unit) {
    var title by remember {
        mutableStateOf("")
    }
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
    ) {
        ShowIconButtonWithImage(
            onclick = {
                Toast.makeText(context, "Back Clicked", Toast.LENGTH_SHORT).show()
                onbackClick()
            },
            imageResources = painterResource(id = R.drawable.arrow_back_24),
            Modifier
                .size(20.dp)
                .align(alignment = Alignment.CenterVertically),
            iconModifier = Modifier.weight(.2f),
            alignMent = Alignment.CenterStart
        )
        TextField(
            textStyle = TextStyle(fontSize = 15.sp),
            value = title,
            onValueChange = { newTitle ->
                title = newTitle
            },
            placeholder = { Text(text = "Enter your title") },
            enabled = true,
            modifier = Modifier
                .height(60.dp)
                .weight(1f),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = splasBgColor,
                unfocusedContainerColor = splasBgColor,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
            singleLine = true,
            maxLines = 1
        )
        ShowIconButtonWithImage(
            onclick = {
                Toast.makeText(context, "Save Button Clicked", Toast.LENGTH_SHORT).show()
                onSaveButtonClick(title)
            },
            imageResources = painterResource(id = R.drawable.baseline_note_save_24),
            Modifier
                .size(30.dp)
                .align(alignment = Alignment.CenterVertically),
            iconModifier = Modifier.weight(.2f),
            alignMent = Alignment.CenterEnd
        )
    }
}

@Composable
fun AddNoteContainerView(updateContent: (String) -> Unit) {
    var content by remember {
        mutableStateOf("")
    }
    TextField(
        value = content,
        onValueChange = {
            updateContent(it)
            content = it
        },
        enabled = true,
        modifier = Modifier.fillMaxSize(),
        placeholder = { Text(text = "Enter your note here!!!") },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = splasBgColor,
            unfocusedContainerColor = splasBgColor,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AddNoteScreenPreview() {
    AddNoteScreen(onbackClick = {}, save = {})

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AddNoteHeaderViewPreview() {
    AddNoteHeaderView(onbackClick = {}, onSaveButtonClick = {})
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AddNoteContainer() {
    AddNoteContainerView() {

    }
}
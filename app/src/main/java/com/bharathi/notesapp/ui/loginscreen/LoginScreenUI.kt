package com.bharathi.notesapp.ui.loginscreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bharathi.notesapp.R
import com.bharathi.notesapp.ui.theme.blackBgColor
import com.bharathi.notesapp.ui.theme.splasBgColor


@Composable
fun LoginScreenUI(onButtonClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = splasBgColor), contentAlignment = Alignment.Center
    ) {
        ShowLoginWithGoogleButton() {
            onButtonClick()
        }
    }
}


@Composable
fun ShowLoginWithGoogleButton(onButtonClick: () -> Unit) {

    Button(
        onClick = onButtonClick,
        shape = RoundedCornerShape(20.dp), contentPadding = PaddingValues(top = 20.dp, bottom = 20.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 15.dp, end = 15.dp),
        colors = ButtonColors(
            containerColor = blackBgColor,
            contentColor = Color.White,
            disabledContainerColor = blackBgColor,
            disabledContentColor = Color.White
        ), border = BorderStroke(2.dp, Color.White),
    ) {
        Image(painter = painterResource(id = R.drawable.sign_in_with_google), contentDescription = null, alignment = Alignment.CenterStart)
        Spacer(modifier = Modifier.width(10.dp))
        Text(text = "Sign In With Google")
    }
}


@Preview
@Composable
fun LoginScreenUIPreview() {
    LoginScreenUI(){

    }
}
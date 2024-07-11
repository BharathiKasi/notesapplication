package com.bharathi.notesapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.bharathi.notesapp.ui.splashscreen.SplashScreen
import com.bharathi.notesapp.ui.theme.NotesAppTheme

@SuppressLint("CustomSplashScreen")
class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        //entry point of an activity and it's replace this setcontentView(R.layout.)
        setContent {
            // it is custom theme composable function
            NotesAppTheme(){
                SplashScreen(){
                    if(false.not()){
                        startActivity(Intent(this@SplashActivity,LoginActivity::class.java))
                    }
                }

            }
        }

    }
}
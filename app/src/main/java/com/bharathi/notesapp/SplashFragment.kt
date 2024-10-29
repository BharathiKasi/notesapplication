package com.bharathi.notesapp

import android.content.Intent
import android.nfc.Tag
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.ComposeView
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import androidx.fragment.app.Fragment
import androidx.navigation.compose.rememberNavController
import androidx.navigation.findNavController
import com.bharathi.notesapp.data.PreferenceRepo
import com.bharathi.notesapp.ui.loginscreen.LoginScreenUI
import com.bharathi.notesapp.ui.splashscreen.SplashScreen
import com.bharathi.notesapp.ui.theme.NotesAppTheme
import com.bharathi.notesapp.ui.theme.setMaterialContentView
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.security.MessageDigest
import java.util.UUID

class SplashFragment : Fragment() {
    lateinit var dataStorePreference: PreferenceRepo
    private val TAG = "SplashFragment"
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setMaterialContentView {
                dataStorePreference = PreferenceRepo.getInstance(requireContext())
                    // it is custom theme composable function
                    NotesAppTheme() {
                        SplashScreen(navigateMainPage = {
                            findNavController().navigate(SplashFragmentDirections.actionSplashFragmentAllNotesFragment())
                        }, navigateLoginScreen = {
                            findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToLoginFragment())
                        },dataStorePreference)
                    }

                }
        }
    }

    }
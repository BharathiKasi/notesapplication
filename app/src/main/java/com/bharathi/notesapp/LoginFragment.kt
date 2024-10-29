package com.bharathi.notesapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.bharathi.notesapp.data.PreferenceRepo
import com.bharathi.notesapp.ui.loginscreen.LoginScreenUI
import com.bharathi.notesapp.ui.theme.NotesAppTheme
import com.bharathi.notesapp.ui.theme.setMaterialContentView
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.security.MessageDigest
import java.util.UUID

class LoginFragment : Fragment() {
    val TAG = "LoginActivity"
    lateinit var dataStorePreference: PreferenceRepo

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return ComposeView(requireContext()).apply {
            setMaterialContentView {
                dataStorePreference = PreferenceRepo.getInstance(LocalContext.current)
                NotesAppTheme {
                    val context = LocalContext.current
                    val coroutineScope = rememberCoroutineScope()
                    //It is used to apply the basic material design structure to our app.
                    Scaffold(modifier = Modifier.fillMaxSize()) { _ ->
                        LoginScreenUI() {
                            try {

                                val rawNonce = UUID.randomUUID().toString()
                                val bytes = rawNonce.toByteArray()
                                val md = MessageDigest.getInstance("SHA-256")
                                val digest = md.digest(bytes)
                                val nonce = digest.fold("") { str, it -> str + "%02x".format(it) }
                                val credentialManager = CredentialManager.create(context)
                                val googleIdOption = GetGoogleIdOption.Builder()
                                    .setFilterByAuthorizedAccounts(false)
                                    .setServerClientId("483751318948-dd22ug9f2r4b8oc0802ka3ta3ao0qg21.apps.googleusercontent.com")
                                    .setNonce(nonce)
                                    .build()

                                val request = GetCredentialRequest.Builder()
                                    .addCredentialOption(googleIdOption)
                                    .build()

                                coroutineScope.launch {
                                    val result = credentialManager.getCredential(context, request)
                                    val credential = result.credential

                                    val googleIdTokenCredential =
                                        GoogleIdTokenCredential.createFrom(credential.data)
                                    val token = googleIdTokenCredential.idToken
                                    Log.d(TAG, "onCreate: ${token}")
                                    withContext(Dispatchers.IO) {
                                        dataStorePreference.saveEmail(googleIdTokenCredential.id)
                                        dataStorePreference.updateIsLoggedIn(true)
                                        dataStorePreference.setUserName(googleIdTokenCredential.displayName.orEmpty())
                                    }
                                    Toast.makeText(context, "your Signed in", Toast.LENGTH_SHORT)
                                        .show()
                                    findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToAllNotesFragment())
                                }
                            } catch (e: Exception) {
                                Toast.makeText(context, "${e.message}", Toast.LENGTH_SHORT).show()
                                e.printStackTrace()
                            }
                        }
                    }
                }
            }

        }
    }
}

package com.bharathi.notesapp.ui.compose.allnotes.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.findNavController
import com.bharathi.notesapp.data.PreferenceRepo
import com.bharathi.notesapp.ui.compose.allnotes.CreateYourFirstNoteScreen
import com.bharathi.notesapp.ui.compose.shimmereffect.NotesListAnimatedShimmer
import com.bharathi.notesapp.ui.theme.setMaterialContentView
import com.bharathi.notesapp.ui.theme.splasBgColor
import kotlinx.coroutines.delay


class AllNotesFragment : Fragment() {

    lateinit var  preference:PreferenceRepo
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return ComposeView(requireContext()).apply {

            preference = PreferenceRepo.getInstance(requireContext())
            setMaterialContentView {
                    var isLoading by remember {
                        mutableStateOf(true)
                    }
                val userName = preference.userName.collectAsStateWithLifecycle(initialValue = "").value
                    LaunchedEffect(true) {
                        delay(5000)
                        isLoading = false
                    }
                    Box(modifier = Modifier.background(splasBgColor)) {
                        if (isLoading) {
                            LazyColumn {
                                items(count = 10) {
                                    if (isLoading) {
                                        NotesListAnimatedShimmer()
                                    }
                                }
                            }
                        } else {
                            CreateYourFirstNoteScreen(userName)
                        }

                        if (isLoading.not()) {
                            FloatingActionButton(onClick = {
                                findNavController().navigate(AllNotesFragmentDirections.actionAllNotesFragmentToAddNoteFragment())
                            },
                                modifier = Modifier
                                    .wrapContentSize()
                                    .padding(end = 16.dp, bottom = 16.dp)
                                    .align(androidx.compose.ui.Alignment.BottomEnd),
                                shape = CircleShape,
                                containerColor = Black,
                                contentColor = Color.White
                            ) {
                                Icon(Icons.Filled.Add, "")
                            }
                        }
                    }

            }

        }
    }

}
package com.bharathi.notesapp.ui.compose.AddNote.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowInsets
import androidx.compose.ui.platform.ComposeView
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.bharathi.notesapp.ui.compose.AddNote.AddNoteScreen
import com.bharathi.notesapp.ui.theme.setMaterialContentView

class AddNoteFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setMaterialContentView {
                AddNoteScreen(
                    onbackClick = {
                        findNavController().popBackStack()
                    },
                    save = {
                        Log.d("onCreateView: ", "${it.title}")
                        Log.d("onCreateView: ", "${it.content}")
                    }
                )
            }
        }
    }
}
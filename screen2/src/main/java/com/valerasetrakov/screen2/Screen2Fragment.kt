package com.valerasetrakov.screen2

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment

class Screen2Fragment: Fragment(R.layout.fragment_screen_2) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<TextView>(R.id.title).apply {
            val title = requireArguments().getString(TITLE_KEY).orEmpty()
            text = title
        }
    }

    companion object {
        const val TITLE_KEY = "TITLE_KEY"
    }
}
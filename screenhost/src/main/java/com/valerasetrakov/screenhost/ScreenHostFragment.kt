package com.valerasetrakov.screenhost

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView

class ScreenHostFragment(
    private val fragmentManagerInitializer: FragmentManager.() -> Unit,
    private val navigation: Navigation
): Fragment(R.layout.fragment_screen_host) {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState).also {
            childFragmentManager.fragmentManagerInitializer()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigation.navigate(R.id.screen_1, childFragmentManager, "Заголовок из ScreenHostFragment 1")
        view.findViewById<BottomNavigationView>(R.id.bottom_navigation).apply {
            setOnItemSelectedListener { menuItem ->
                when(menuItem.itemId) {
                    R.id.screen_1 -> {
                        navigation.navigate(R.id.screen_1, childFragmentManager, "Заголовок из ScreenHostFragment 1")
                        true
                    }
                    R.id.screen_2 -> {
                        navigation.navigate(R.id.screen_2, childFragmentManager, "Заголовок из ScreenHostFragment 2")
                        true
                    }
                    else -> false
                }
            }
        }
    }

    fun interface Navigation {
        fun navigate(id: Int, fragmentManager: FragmentManager, title: String)
    }
}
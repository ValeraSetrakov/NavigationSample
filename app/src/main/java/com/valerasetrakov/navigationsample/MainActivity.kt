package com.valerasetrakov.navigationsample

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.valerasetrakov.screen1.Screen1Fragment
import com.valerasetrakov.screen2.Screen2Fragment
import com.valerasetrakov.screenhost.ScreenHostFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        supportFragmentManager.fragmentFactory = object : FragmentFactory() {
            override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
                return when (className) {
                    ScreenHostFragment::class.java.name -> ScreenHostFragment(
                        fragmentManagerInitializer = {
                            fragmentFactory = object : FragmentFactory() {
                                override fun instantiate(
                                    classLoader: ClassLoader,
                                    className: String
                                ): Fragment {
                                    return when (className) {
                                        Screen1Fragment::class.java.name -> {
                                            Screen1Fragment()
                                        }

                                        Screen2Fragment::class.java.name -> {
                                            Screen2Fragment()
                                        }

                                        else -> super.instantiate(classLoader, className)
                                    }
                                }
                            }
                        }
                    ) { fragmentId, fragmentManager, title ->
                        when (fragmentId) {
                            com.valerasetrakov.screenhost.R.id.screen_1 -> {
                                fragmentManager.beginTransaction().apply {
                                    replace(
                                        com.valerasetrakov.screenhost.R.id.fragment_container,
                                        Screen1Fragment::class.java,
                                        bundleOf(Screen1Fragment.TITLE_KEY to title)
                                    )
                                    commit()
                                }
                            }

                            com.valerasetrakov.screenhost.R.id.screen_2 -> {
                                fragmentManager.beginTransaction().apply {
                                    replace(
                                        com.valerasetrakov.screenhost.R.id.fragment_container,
                                        Screen2Fragment::class.java,
                                        bundleOf(Screen2Fragment.TITLE_KEY to title)
                                    )
                                    commit()
                                }
                            }
                        }
                    }

                    else -> super.instantiate(classLoader, className)
                }
            }
        }
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().apply {
                add(R.id.fragment_container, ScreenHostFragment::class.java, null)
                commit()
            }
        }
    }
}
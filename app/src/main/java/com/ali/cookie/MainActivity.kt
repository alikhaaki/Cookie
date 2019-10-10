package com.ali.cookie

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import com.ali.cookie.service.AssistantLoadingFresco
import com.ali.cookie.service.ImageLoadingServices
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private var currentNavController: LiveData<NavController>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            setupBottomNavigationBar()
        } // Else, need to wait for onRestoreInstanceState


    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        // Now that BottomNavigationBar has restored its instance state
        // and its selectedItemId, we can proceed with setting up the
        // BottomNavigationBar with Navigation
        setupBottomNavigationBar()
    }

    /**
     * Called on first creation and when restoring state.
     */
    private fun setupBottomNavigationBar() {
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        val navGraphIds = listOf(R.navigation.nav_home, R.navigation.nav_search, R.navigation.nav_bookmark)

        // Setup the bottom navigation view with a list of navigation graphs
        val controller = bottomNavigationView.setupWithNavController(
                navGraphIds = navGraphIds,
                fragmentManager = supportFragmentManager,
                containerId = R.id.fragment_container,
                intent = intent
        )


        currentNavController = controller
    }

    override fun onSupportNavigateUp(): Boolean {
        return currentNavController?.value?.navigateUp() ?: false
    }
}

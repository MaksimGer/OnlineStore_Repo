package com.example.my_onlinestore.views

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.my_onlinestore.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var mNavigationController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        mNavigationController = navHostFragment.navController
        setUpBottomNav(mNavigationController)

        val toolbar: Toolbar = findViewById(R.id.toolbar_actionbar)
        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.homeFragment,
            R.id.categoriesFragment,
            R.id.productFavoriteFragment,
            R.id.personalAreaFragment,
            R.id.cartFragment))
        toolbar.setupWithNavController(mNavigationController, appBarConfiguration)
    }

    private fun setUpBottomNav(navController: NavController) {
        val bottomNav: BottomNavigationView = findViewById(R.id.bottom_nav_view)
        bottomNav.setupWithNavController(navController)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        NavigationUI.onNavDestinationSelected(item, mNavigationController)
        return super.onOptionsItemSelected(item)
    }
}
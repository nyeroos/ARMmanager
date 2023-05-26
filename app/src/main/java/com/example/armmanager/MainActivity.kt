package com.example.armmanager

import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import com.example.armmanager.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity


class MainActivity : DaggerAppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    private val showBottomMenuFragments = setOf(R.id.item_1, R.id.item_2)
    private val listener =
        NavController.OnDestinationChangedListener { _, destination, _ ->
            run {
                if (destination.id == R.id.item_1 || destination.id == R.id.item_2)
                    binding.bottomNavigation.visibility = View.VISIBLE
                else
                    binding.bottomNavigation.visibility = View.GONE
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMainActivityMenu.toolbar)


        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_main_activity_menu) as NavHostFragment
        navController = navHostFragment.navController

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.item_1,
                R.id.item_2,
                R.id.nav_anal,
                R.id.nav_products,
                R.id.nav_account,
                R.id.nav_exit
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        binding.bottomNavigation.setupWithNavController(navController)
        val bottomNavigationView = binding.bottomNavigation

        bottomNavigationView.setOnItemSelectedListener { item ->
            val currentFragmentId = navController.currentDestination?.id
            if (currentFragmentId in showBottomMenuFragments) {
                // Показать нижнее меню
                bottomNavigationView.visibility = View.VISIBLE
            } else {
                // Скрыть нижнее меню
                bottomNavigationView.visibility = View.GONE
            }
            NavigationUI.onNavDestinationSelected(item, navController)
        }

    }

    override fun onResume() {
        super.onResume()
        navController.addOnDestinationChangedListener(listener)
    }

    override fun onPause() {
        super.onPause()
        navController.removeOnDestinationChangedListener(listener)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main_activity_menu, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main_activity_menu)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}
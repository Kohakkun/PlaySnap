package com.example.playsnapui

import SharedData.deepLinkid
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.playsnapui.databinding.ActivityHomeBinding
import com.example.playsnapui.ui.home.HomeFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        deepLinkid = ""
        val gameId = intent.getStringExtra("gameId") ?: ""
        Log.d("From HomeAct", "$gameId ????")
        deepLinkid = gameId
        Log.d("From HomeAct", "$deepLinkid ????")


        // Get NavController
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        binding.bottomNavigationView.setupWithNavController(navController)

        // Hide Bottom Navigation Bar in specific fragments
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.snapFragment, R.id.swipeGalleryFragment, R.id.scrollGalleryFragment, R.id.filterFragment, R.id.ObjectFragment,
                R.id.recommendGameFragment, R.id.tutorialFragment, R.id.historyFragment, R.id.reportFragment, R.id.reportSuccessFragment,
                R.id.editProfileFragment, R.id.accountSettingFragment, R.id.editPasswordFragment, R.id.feedbackFragment, R.id.helpFragmentReqSuccess,
                R.id.DeleteAccountFragment -> {
                    binding.bottomNavigationView.visibility = View.GONE
                }
                else -> {
                    binding.bottomNavigationView.visibility = View.VISIBLE
                }
            }
        }



        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    navController.navigate(R.id.homeFragment)
                    true
                }
                R.id.nav_profile -> {
                    navController.navigate(R.id.profileFragment)
                    true
                }
                R.id.nav_bookmark -> {
                    navController.navigate(R.id.bookmarkFragment)
                    true
                }
                R.id.nav_like -> {
                    navController.navigate(R.id.likeFragment)
                    true
                }
                else -> false
            }
        }


    }



}

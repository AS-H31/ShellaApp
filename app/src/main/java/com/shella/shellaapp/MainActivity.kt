package com.shella.shellaapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationBarView
import com.shella.shellaapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var bottomNav : NavigationBarView
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadFragment(HomeFragment())

        bottomNav = findViewById(R.id.bottomNav)
        bottomNav.menu.findItem(R.id.home).isChecked = true

        bottomNav.setOnItemReselectedListener {
            when(it.itemId){
                R.id.home ->{
                    loadFragment(HomeFragment())
                    return@setOnItemReselectedListener
                }
                R.id.acc_fragment ->{
                    loadFragment(AccelerometerFragment())
                    return@setOnItemReselectedListener
                }
                R.id.gps_fragment ->{
                    loadFragment(GpsFragment())
                    //loadActivity(AbdoLocationActivity())
                    return@setOnItemReselectedListener
                }

            }
        }

    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    private fun loadActivity(activity: Activity) {
        val intent = Intent(this, activity::class.java)
        startActivity(intent)
    }

}
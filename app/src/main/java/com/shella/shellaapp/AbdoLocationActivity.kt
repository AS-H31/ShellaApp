package com.shella.shellaapp

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class AbdoLocationActivity : AppCompatActivity() {



    private lateinit var latitude: TextView
    private lateinit var longitude: TextView
    private var locationManager: LocationManager? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager //LM

        setContentView(R.layout.activity_abdo_location)

        latitude = findViewById(R.id.latitude)
        longitude = findViewById(R.id.longitude)
        val button = findViewById<Button>(R.id.button)

        button.setOnClickListener{
            getLocation()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun getLocation(){
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED ){
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),100)
            return
        }

        val locationListener = LocationListener { location ->
            println("location: $location")
        }
        locationManager?.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0f, locationListener)
        println("lastKnownLocation: ${locationManager?.getLastKnownLocation(LocationManager.GPS_PROVIDER)}")
        val location = locationManager?.getLastKnownLocation(LocationManager.GPS_PROVIDER)
        latitude.text = "Latitude: "+location?.latitude.toString()
        longitude.text = "Longitude: "+location?.longitude.toString()

    }
}
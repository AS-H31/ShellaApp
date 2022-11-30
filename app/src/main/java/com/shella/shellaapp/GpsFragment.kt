package com.shella.shellaapp

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


// TODO: Rename parameter arguments, choose names that match
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [GpsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GpsFragment : Fragment() {
    var sharedPreferences: SharedPreferences = requireActivity().getSharedPreferences("sharedPrefs", MODE_PRIVATE)
    var editor: SharedPreferences.Editor = sharedPreferences.edit()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Initialize map fragment
        val supportMapFragment =
            childFragmentManager.findFragmentById(R.id.google_map) as? SupportMapFragment


        //zoom in to the location
        supportMapFragment?.getMapAsync {
            it.animateCamera(CameraUpdateFactory.newLatLngZoom(LatLng(31.2001, 29.9187), 10f))
            it.addMarker(MarkerOptions().position(LatLng(31.2001, 29.9187)).title("Marker in Egypt"))
        }
        // Async map
        /*
        supportMapFragment!!.getMapAsync { googleMap ->
            // When map is loaded
            googleMap.setOnMapClickListener { latLng -> // When clicked on map
                // Initialize marker options
                val markerOptions = MarkerOptions()
                // Set position of marker
                markerOptions.position(latLng)
                // Set title of marker
                markerOptions.title(latLng.latitude.toString() + " : " + latLng.longitude)
                // Remove all marker
                googleMap.clear()
                // Animating to zoom the marker
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10f))
                // Add marker on map
                googleMap.addMarker(markerOptions)
            }
        }*/
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_gps, container, false)
    }

    private fun saveDataLocally(location: Location) {
        val timestamp = System.currentTimeMillis()
        editor.putStringSet(timestamp.toString(), setOf(location.latitude.toString(), location.longitude.toString()))
        editor.commit()
    }
}
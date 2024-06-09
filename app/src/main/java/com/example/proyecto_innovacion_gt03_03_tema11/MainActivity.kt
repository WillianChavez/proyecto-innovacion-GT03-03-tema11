package com.example.proyecto_innovacion_gt03_03_tema11

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.replace
import com.example.proyecto_innovacion_gt03_03_tema11.databinding.ActivityMainBinding
import com.google.android.gms.common.api.Status
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapFragment
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(),OnMapReadyCallback  {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var fragmentautocomplete: AutocompleteSupportFragment
    private var googleMap: GoogleMap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        // Initialize Places
        Places.initialize(applicationContext, getString(R.string.google_api_key))

        // Set up Autocomplete Fragment
        setupAutocompleteFragment()

        binding.appBarMain.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null)
                .setAnchorView(R.id.fab).show()
        }

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)

        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        // Set up Map Fragment
        setupMapFragment()
    }


    private fun setupAutocompleteFragment() {
        fragmentautocomplete = AutocompleteSupportFragment.newInstance()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.auto_complete_search_fragment, fragmentautocomplete)
            .commit()

        fragmentautocomplete.setPlaceFields(listOf(Place.Field.ID, Place.Field.ADDRESS, Place.Field.LAT_LNG))
        fragmentautocomplete.setOnPlaceSelectedListener(object : PlaceSelectionListener {
            override fun onError(status: Status) {
                Log.e("PlaceSelection", "An error occurred: $status")
                runOnUiThread {
                    Toast.makeText(this@MainActivity, "Error: ${status.statusMessage}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onPlaceSelected(place: Place) {
                place.latLng?.let {
                    zoomOnMap(it)
                }
            }
        })
    }
    private fun setupMapFragment() {
        val fragment = supportFragmentManager.findFragmentById(R.id.map) as? SupportMapFragment
            ?: SupportMapFragment.newInstance().also {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.map, it)
                    .commit()
            }
        fragment.getMapAsync(this)
    }

    private fun zoomOnMap(latLng: LatLng) {
        googleMap?.let {
            val newLatLngZoom = CameraUpdateFactory.newLatLngZoom(latLng, 12f)
            it.animateCamera(newLatLngZoom)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }


    override fun onMapReady(googleMap: GoogleMap) {
        this.googleMap = googleMap
        /*
        val position=LatLng(-34.0, 151.0)
        googleMap.addMarker(
            MarkerOptions().position(position).title("Position")
        )
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(position))
        */

    }
}
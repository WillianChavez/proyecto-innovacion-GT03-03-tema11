package com.example.proyecto_innovacion_gt03_03_tema11.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.proyecto_innovacion_gt03_03_tema11.R

import com.example.proyecto_innovacion_gt03_03_tema11.databinding.FragmentHomeBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class HomeFragment : Fragment(), OnMapReadyCallback {

    private var _binding: FragmentHomeBinding? = null

    private lateinit var mMap: GoogleMap

    private val homeViewModel : HomeViewModel by viewModels()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {

        val root: View = inflater.inflate(R.layout.fragment_home,container,false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.id_map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        val salvador = LatLng(.703093009418746, -89.21658495448729)

        mMap = googleMap
        mMap.addMarker(
            MarkerOptions().position(salvador)
                .title("Marker")
        )
        mMap.moveCamera(CameraUpdateFactory.newLatLng(salvador))

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
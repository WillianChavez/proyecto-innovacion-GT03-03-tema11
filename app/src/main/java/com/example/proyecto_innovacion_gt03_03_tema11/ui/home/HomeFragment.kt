package com.example.proyecto_innovacion_gt03_03_tema11.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Display.Mode
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.transition.Visibility
import com.example.proyecto_innovacion_gt03_03_tema11.R
import com.example.proyecto_innovacion_gt03_03_tema11.databinding.FragmentHomeBinding
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback

class HomeFragment :Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        binding.searchContainer.setBackgroundColor(resources.getColor(android.R.color.transparent))
        binding.list.visibility=View.INVISIBLE

        return root
    }

    @SuppressLint("ResourceType")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.editSearchPlace.setOnClickListener(View.OnClickListener {
            binding.searchContainer.setBackgroundColor(resources.getColor(android.R.color.white))
            binding.btnBack.setImageResource(R.drawable.back)
            binding.list.visibility=View.VISIBLE
        })


        binding.btnBack.setOnClickListener(View.OnClickListener {
            binding.searchContainer.setBackgroundColor(resources.getColor(android.R.color.transparent))
            binding.list.visibility=View.INVISIBLE
            binding.btnBack.setImageResource(R.drawable.search)
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}
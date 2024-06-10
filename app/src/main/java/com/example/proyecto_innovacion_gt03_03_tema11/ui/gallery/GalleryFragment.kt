package com.example.proyecto_innovacion_gt03_03_tema11.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto_innovacion_gt03_03_tema11.DatabaseHelper
import com.example.proyecto_innovacion_gt03_03_tema11.R
import com.example.proyecto_innovacion_gt03_03_tema11.databinding.FragmentGalleryBinding
import es.dmoral.toasty.Toasty

class GalleryFragment : Fragment() {
    private lateinit var dbHelper: DatabaseHelper
    private lateinit var getName: ArrayList<String>
    private lateinit var getRating: ArrayList<String>
    private lateinit var getType: ArrayList<String>
    private var _binding: FragmentGalleryBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        val galleryViewModel =
                ViewModelProvider(this).get(GalleryViewModel::class.java)

        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textGallery
        galleryViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dbHelper = DatabaseHelper(requireContext())
        getName = ArrayList()
        getRating = ArrayList()
        getType = ArrayList()

        setData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

//    override fun onCreate(savedInstanceState: Bundle?) {
//        Toast.makeText(requireContext(), "Toast para probar", Toast.LENGTH_SHORT).show()
//        super.onCreate(savedInstanceState)
//        dbHelper = DatabaseHelper(requireContext())
//        getName = ArrayList()
//        getRating = ArrayList()
//        getType = ArrayList()
//        setData()
//    }

    private fun setData() {
        val nameList = dbHelper.showPlace()
        if (nameList.size == 0) {
            return Toasty.info(requireContext(), "Aun no tienes lugares favoritos", Toasty.LENGTH_SHORT).show()
        } else {
            for (place in nameList) {
                getName.add(place.name)
                getRating.add(place.rating)
                getType.add(place.type)
            }
        }

        val recyclerView: RecyclerView = requireView().findViewById(R.id.rv_gallery)
        val adapter = GalleryAdapter(getName, getRating, getType)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }
}
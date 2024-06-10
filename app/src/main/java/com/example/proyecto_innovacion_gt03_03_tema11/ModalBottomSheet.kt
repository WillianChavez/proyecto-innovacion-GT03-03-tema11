package com.example.proyecto_innovacion_gt03_03_tema11

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.libraries.places.api.model.Place

class ModalBottomSheet : BottomSheetDialogFragment() {

    private var place: Place? = null
    private var titlePlace: TextView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.modal_bottom_sheet_content, container, false)
        this.titlePlace = v?.findViewById(R.id.titlePlaceModal)
        return v
    }

    companion object {
        const val TAG = "ModalBottomSheet"
    }

    fun show(supportFragmentManager: FragmentManager) {
        this.show(supportFragmentManager, TAG)
    }

    fun showDetails(place: Place) {

        this.place = place
        Log.d("ModalBottomSheet", "Place: ${titlePlace}")

        this.titlePlace?.text = place.address

        Log.d("ModalBottomSheet", "Place: ${place.address}")
    }
}
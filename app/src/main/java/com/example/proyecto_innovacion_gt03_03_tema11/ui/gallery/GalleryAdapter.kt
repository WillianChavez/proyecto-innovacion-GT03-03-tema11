package com.example.proyecto_innovacion_gt03_03_tema11.ui.gallery

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto_innovacion_gt03_03_tema11.R

class GalleryAdapter(
    showNameList: ArrayList<String>,
    showRatingList: ArrayList<String>,
    showTypeList: ArrayList<String>,
): RecyclerView.Adapter<GalleryAdapter.ViewHolder>() {
    private lateinit var ShowNameList: ArrayList<String>
    private lateinit var ShowRatingList: ArrayList<String>
    private lateinit var ShowTypeList: ArrayList<String>

    init {
        this.ShowNameList = showNameList
        this.ShowRatingList = showRatingList
        this.ShowTypeList = showTypeList
    }

    public class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var itemName: TextView = itemView.findViewById(R.id.tvTitle)
        var itemRating: TextView = itemView.findViewById(R.id.tvRating)
        var itemType: TextView = itemView.findViewById(R.id.tvType)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.fragment_gallery, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemName.text = ShowNameList[position]
        holder.itemRating.text = ShowRatingList[position]
        holder.itemType.text = ShowTypeList[position]
    }

    override fun getItemCount(): Int {
        return ShowNameList.size
    }
}
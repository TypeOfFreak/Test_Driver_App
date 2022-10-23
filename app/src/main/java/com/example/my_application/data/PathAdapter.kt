package com.example.my_application.data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.my_application.R

class PathAdapter(val pathList: Array<String>) :
    RecyclerView.Adapter<PathAdapter.PathViewHolder>() {

    // Describes an item view and its place within the RecyclerView
    class PathViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val pointTextView: TextView = itemView.findViewById(R.id.textPoint)

        fun bind(word: String) {
             pointTextView.text = word
        }
    }

    // Returns a new ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PathViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.text_row_item, parent, false)

        return PathViewHolder(view)
    }

    // Returns size of data list
    override fun getItemCount(): Int {
        return pathList.size
    }

    // Displays data at a certain position
    override fun onBindViewHolder(holder: PathViewHolder, position: Int) {
        holder.bind(pathList[position])
    }
}
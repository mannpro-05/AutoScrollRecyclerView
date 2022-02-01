package com.example.autoscrollrecyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.display_textview.view.*

class RvAdapter(private val data: ArrayList<String>) :
    RecyclerView.Adapter<RvAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val displayTextView = view.tv_display
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(
            R.layout.display_textview,
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentText = data[position]
        holder.displayTextView.text = currentText
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
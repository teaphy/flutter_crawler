package com.example.native_flutter_communication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class WordAdapter : RecyclerView.Adapter<WordAdapter.ViewHolder>(){

    private val listData = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflator = LayoutInflater.from(parent.context)
        val view = inflator.inflate(R.layout.item_word, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.wordText.text = listData[position]
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val wordText = view.findViewById<TextView>(R.id.word_text)
    }

    fun addData(word: String) {
        val oldSize = listData.size;
        listData.add(word)
        notifyItemInserted(oldSize)
    }
}
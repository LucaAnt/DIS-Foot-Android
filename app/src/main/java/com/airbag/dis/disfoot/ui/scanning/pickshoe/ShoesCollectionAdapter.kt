package com.airbag.dis.disfoot.ui.scanning.pickshoe

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.*
import com.airbag.dis.disfoot.R
import com.airbag.dis.disfoot.model.Shoe

class ShoesCollectionAdapter(var shoesCollection : Map<String, List<Shoe>>) :
    Adapter<sectionViewHolder>() {

    var listener : IShoeSelected? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): sectionViewHolder {
        return sectionViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.cell_shoes_section, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return this.shoesCollection.size
    }

    override fun onBindViewHolder(
        holder: sectionViewHolder,
        position: Int
    ) {
        val key = shoesCollection.keys.toTypedArray()[position]
        holder.bindSection(key,shoesCollection.getValue(key),listener)
    }

    fun setOnShoeSelectedListener(listener : IShoeSelected) {
        this.listener = listener
    }
}



class sectionViewHolder(itemView: View) : ViewHolder(itemView) {
    lateinit var shoesRecycler : RecyclerView
    lateinit var shoesAdapter : ShoesListAdapter
    fun bindSection(
        sectionName: String,
        shoes: List<Shoe>,
        listener: IShoeSelected?
    )
    {
        itemView.findViewById<TextView>(R.id.cellCategotyTextView).setText(sectionName.capitalize())

        shoesRecycler = itemView.findViewById(R.id.cellShoeCardsRecycler)
        shoesRecycler.layoutManager = LinearLayoutManager(itemView.context, HORIZONTAL,false)

        shoesAdapter =
            ShoesListAdapter(shoes).also { if (listener !=null) it.setOnShoeSelectedListener(listener) }
        shoesRecycler.adapter = shoesAdapter
    }

}
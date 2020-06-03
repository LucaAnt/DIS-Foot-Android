package com.airbag.dis.disfoot.ui.main.other

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.*
import com.airbag.dis.disfoot.R
import com.airbag.dis.disfoot.model.OtherShoe
import com.airbag.dis.disfoot.model.Shoe

class AdapterOtherShoesCollection(var shoesCollection : Map<String, List<OtherShoe>>) :
    Adapter<sectionOtherShoesViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): sectionOtherShoesViewHolder {
        return sectionOtherShoesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.cell_shoes_section, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return this.shoesCollection.size
    }

    override fun onBindViewHolder(
        holder: sectionOtherShoesViewHolder,
        position: Int
    ) {
        val key = shoesCollection.keys.toTypedArray()[position]
        holder.bindSection(key,shoesCollection.getValue(key))
    }

}



class sectionOtherShoesViewHolder(itemView: View) : ViewHolder(itemView) {
    lateinit var shoesRecycler : RecyclerView
    fun bindSection(
        sectionName: String,
        shoes: List<OtherShoe>
    )
    {
        itemView.findViewById<TextView>(R.id.cellCategotyTextView).setText(sectionName.capitalize())

        shoesRecycler = itemView.findViewById(R.id.cellShoeCardsRecycler)
        shoesRecycler.layoutManager = LinearLayoutManager(itemView.context, HORIZONTAL,false)
        shoesRecycler.adapter = AdapterOtherShoesList(shoes)
    }

}
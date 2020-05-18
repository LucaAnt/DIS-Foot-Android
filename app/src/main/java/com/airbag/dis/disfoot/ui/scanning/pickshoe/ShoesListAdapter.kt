package com.airbag.dis.disfoot.ui.scanning.pickshoe

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.airbag.dis.disfoot.R
import com.airbag.dis.disfoot.model.Shoe
import com.bumptech.glide.Glide
import com.google.android.material.chip.Chip

class ShoesListAdapter (var shoes : List<Shoe>) :
    RecyclerView.Adapter<ShoeListViewHolder>(){

    var listener : IShoeSelected? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoeListViewHolder {
        return ShoeListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.cell_shoe_card, parent, false)
        )
    }

    override fun getItemCount(): Int {
       return shoes.size
    }

    override fun onBindViewHolder(holder: ShoeListViewHolder, position: Int) {
        holder.bindShoe(shoes.get(position),listener)
    }

    fun setOnShoeSelectedListener(listener: IShoeSelected) {
        this.listener = listener
    }

}

class ShoeListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    lateinit var shoe: Shoe
    fun bindShoe(
        shoe: Shoe,
        listener: IShoeSelected?
    )
    {
        this.shoe = shoe
        Glide.with(itemView.context).load(shoe.imageUri).into(itemView.findViewById(R.id.cardImage));
        itemView.findViewById<TextView>(R.id.cardShoeName).setText(shoe.name)
        itemView.findViewById<TextView>(R.id.cardType).setText(shoe.description)
        itemView.findViewById<Chip>(R.id.cardChip).setText(shoe.last.toString())
        itemView.findViewById<TextView>(R.id.cardMode).setText(shoe.lastName)
        if (listener!=null)
            itemView.setOnClickListener { listener.onShoeSelected(shoe.uniqueCode) }

    }
}

package com.airbag.dis.disfoot.ui.main.other

import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.airbag.dis.disfoot.R
import com.airbag.dis.disfoot.model.OtherShoe
import com.bumptech.glide.Glide
import com.google.android.material.chip.Chip

class AdapterOtherShoesList (var shoes : List<OtherShoe>) :
    RecyclerView.Adapter<OtherShoeListViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OtherShoeListViewHolder {
        return OtherShoeListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.cell_shoe_card, parent, false)
        )
    }

    override fun getItemCount(): Int {
       return shoes.size
    }

    override fun onBindViewHolder(holder: OtherShoeListViewHolder, position: Int) {
        holder.bindShoe(shoes.get(position))
    }


}

class OtherShoeListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    lateinit var otherShoe: OtherShoe
    fun bindShoe(
        otherShoe: OtherShoe
    )
    {
        this.otherShoe = otherShoe
        Glide.with(itemView.context).load(otherShoe.shoe.imageUri).into(itemView.findViewById(R.id.cellCardSmallmage));
        itemView.findViewById<TextView>(R.id.cellCardSmallShoeName).setText(otherShoe.shoe.name)
        itemView.findViewById<TextView>(R.id.cellCardSmallType).setText(otherShoe.shoe.description)
        itemView.findViewById<Chip>(R.id.cellCardSmallChip).setText(otherShoe.shoe.last.toString())
        itemView.findViewById<TextView>(R.id.cellCardSmallMode).setText(otherShoe.fit)
        itemView.findViewById<TextView>(R.id.cellCardSmallNumber).also { it.visibility = VISIBLE }.setText(otherShoe.size)

    }
}

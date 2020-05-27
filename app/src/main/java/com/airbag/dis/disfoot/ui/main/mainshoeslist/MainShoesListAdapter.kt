package com.airbag.dis.disfoot.ui.main.mainshoeslist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.*
import com.airbag.dis.disfoot.R
import com.airbag.dis.disfoot.model.SelectedShoe
import com.airbag.dis.disfoot.ui.scanning.pickshoe.IShoeSelected
import com.bumptech.glide.Glide
import com.google.android.material.chip.Chip


class MainShoesListAdapter(var shoesList : List<SelectedShoe>) : Adapter<ViewHolderBigCard>()
{
    var listener : IShoeSelected? = null
    fun setData( shoesList : List<SelectedShoe>)
    {
        this.shoesList = shoesList
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderBigCard {
        return ViewHolderBigCard(LayoutInflater.from(parent.context).inflate(R.layout.cell_shoe_card_big,parent,false))
    }

    override fun getItemCount(): Int {
        return  shoesList.count()
    }

    override fun onBindViewHolder(holderBigCard: ViewHolderBigCard, position: Int) {
        holderBigCard.bindShoe(shoesList[position])
        listener?.let {
            holderBigCard.setOnSelectedShoesListener(it)
        }
    }

}
class ViewHolderBigCard(itemView: View) : ViewHolder(itemView) {

    lateinit var shoeItem: SelectedShoe
    fun bindShoe(
        shoeItem: SelectedShoe
    )
    {
        this.shoeItem = shoeItem
        Glide.with(itemView.context).load(this.shoeItem.selectedShoe.imageUri).into(itemView.findViewById(R.id.cardImage));
        itemView.findViewById<TextView>(R.id.cardShoeName).setText(this.shoeItem.selectedShoe.name)
        itemView.findViewById<TextView>(R.id.cardType).setText(this.shoeItem.selectedShoe.description)
        itemView.findViewById<Chip>(R.id.cardChip).setText(this.shoeItem.selectedShoe.last.toString())
        itemView.findViewById<TextView>(R.id.cardMode).setText(this.shoeItem.selectedShoe.lastName)
        itemView.findViewById<TextView>(R.id.cardSize).setText(this.shoeItem.size)

        if (this.shoeItem.scanName!="" && this.shoeItem.scanName!=null)
        {
            itemView.findViewById<TextView>(R.id.cardScanName).visibility = View.VISIBLE
            itemView.findViewById<TextView>(R.id.cardScanName).setText(this.shoeItem.scanName)
        }
        else
            itemView.findViewById<TextView>(R.id.cardScanName).visibility = View.GONE
    }

    fun setOnSelectedShoesListener(listener : IShoeSelected)
    {
        if (listener!=null)
            itemView.setOnClickListener { listener.onShoeSelected(this.shoeItem.id) }
    }

}

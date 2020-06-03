package com.airbag.dis.disfoot.ui.main.other

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import com.airbag.dis.disfoot.R
import com.airbag.dis.disfoot.model.SelectedShoe
import com.airbag.dis.disfoot.ui.ViewModelCommon
import com.bumptech.glide.Glide
import com.google.android.material.chip.Chip
import kotlinx.android.synthetic.main.fragment_other_shoes.*
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO


class FragmentOtherShoes : Fragment() {

    private val commonViewModel: ViewModelCommon by activityViewModels()
    val args: FragmentOtherShoesArgs by navArgs()
    private lateinit var shoeItem: SelectedShoe

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_other_shoes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = args.selectedShoeId

        Toast.makeText(requireContext(),"$id",Toast.LENGTH_LONG).show()

        otherMeasuresButtonClose.setOnClickListener { findNavController().popBackStack() }
        otherMeasuresButtonDelete.setOnClickListener {
            CoroutineScope(IO).launch {
                commonViewModel.deleteShoeScan(shoeItem)
            }
            findNavController().popBackStack()
        }

        //Main card Setup
        commonViewModel.scans.value?.find { it.id == id }?.let {
            shoeItem = it
            setCard(shoeItem)
        }


        //Recycler Setup
        CoroutineScope(Dispatchers.Default).launch{
            commonViewModel.getOtherModelsScan(id.toLong())
        }

        otherMeasuresRecycler.layoutManager = LinearLayoutManager(requireContext(),VERTICAL,false)
        commonViewModel.otherScans.observe(viewLifecycleOwner, Observer {
            it.forEach { collection -> collection.value.forEach { shoe -> println(shoe) } }
            otherMeasuresRecycler.adapter = AdapterOtherShoesCollection(it).also { it.notifyDataSetChanged() }
        })
    }

    private fun setCard(shoeItem: SelectedShoe)
    {
        this.view?.let {
            Glide.with(it.context).load(this.shoeItem.selectedShoe.imageUri).into(it.findViewById(
                R.id.cellCardSmallmage
            ));
            it.findViewById<TextView>(R.id.cellCardSmallShoeName).setText(this.shoeItem.selectedShoe.name)
            it.findViewById<TextView>(R.id.cellCardSmallType).setText(this.shoeItem.selectedShoe.description)
            it.findViewById<Chip>(R.id.cellCardSmallChip).setText(this.shoeItem.selectedShoe.last.toString())
            it.findViewById<TextView>(R.id.cellCardSmallMode).setText(this.shoeItem.selectedShoe.lastName)
            it.findViewById<TextView>(R.id.cardSize).setText(this.shoeItem.size)

            if (this.shoeItem.scanName!="" && this.shoeItem.scanName!=null)
            {
                it.findViewById<TextView>(R.id.cardScanName).visibility = View.VISIBLE
                it.findViewById<TextView>(R.id.cardScanName).setText(this.shoeItem.scanName)
            }
            else
                it.findViewById<TextView>(R.id.cardScanName).visibility = View.GONE
        }


    }
}

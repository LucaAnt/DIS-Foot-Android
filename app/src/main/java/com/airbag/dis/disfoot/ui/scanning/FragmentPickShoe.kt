package com.airbag.dis.disfoot.ui.scanning

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import com.airbag.dis.disfoot.R
import com.airbag.dis.disfoot.ui.ViewModelCommon
import kotlinx.android.synthetic.main.fragment_pick_shoe.*


class FragmentPickShoe : Fragment() {

    private val commonViewModel: ViewModelCommon by activityViewModels()
    val adapter : ShoesCollectionAdapter = ShoesCollectionAdapter(mapOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pick_shoe, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentPickRecycler.layoutManager = LinearLayoutManager(context,VERTICAL,false)
        fragmentPickRecycler.adapter = adapter
        commonViewModel.shoesModels.observe(this.viewLifecycleOwner, Observer {

            adapter.shoesCollection = it
            adapter.notifyDataSetChanged()
            //fragmentPickRecycler.adapter = ShoesCollectionAdapter(it).also { it.notifyDataSetChanged() }
        })

        fragmentPickBtnClose.setOnClickListener { findNavController().popBackStack() }
    }

}

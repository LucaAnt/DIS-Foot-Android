package com.airbag.dis.disfoot.ui.main.mainshoeslist

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import com.airbag.dis.disfoot.R
import com.airbag.dis.disfoot.SHARED_PREFERENCES_KEY
import com.airbag.dis.disfoot.SHARED_PREFERENCES_KEY_REGISTERED
import com.airbag.dis.disfoot.ui.ViewModelCommon
import com.airbag.dis.disfoot.ui.scanning.pickshoe.IShoeSelected
import kotlinx.android.synthetic.main.fragment_main.*


class FragmentMainShoesListSelection : Fragment() {

    private val commonViewModel: ViewModelCommon by activityViewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentMainBtnInfo.setOnClickListener {
            findNavController().navigate(
                FragmentMainShoesListSelectionDirections.toInfo()
            )
        }
        fragmentMainBtnTutorial.setOnClickListener {
            findNavController().navigate(
                FragmentMainShoesListSelectionDirections.toFragmentTutorial()
            )
        }
        fragmentMainBtnMeasurement.setOnClickListener {
            val registered: Boolean = (activity?.getSharedPreferences(
                SHARED_PREFERENCES_KEY,
                Context.MODE_PRIVATE
            )?.getBoolean(
                SHARED_PREFERENCES_KEY_REGISTERED, false
            )) ?: false

            //todo: dev test
            findNavController().navigate(FragmentMainShoesListSelectionDirections.toFragmentPickShoe())
//            when{
//                registered == true -> findNavController().navigate(FragmentMainShoesListSelectionDirections.toFragmentPickShoe())
//                registered == false -> findNavController().navigate(FragmentMainShoesListSelectionDirections.toFragmentRegister())
//            }

        }

        mainRecyclerShoes.adapter = MainShoesListAdapter(listOf())
        mainRecyclerShoes.layoutManager = LinearLayoutManager(requireContext(), VERTICAL, false)

        commonViewModel.scans.observe(viewLifecycleOwner, Observer {
            if (it.size > 0)
                (mainRecyclerShoes.adapter as? MainShoesListAdapter)?.let { adapter ->
                   adapter.setShoeSelectedListener(object  : IShoeSelected{
                       override fun onShoeSelected(id: String) {
                           TODO("Not yet implemented")
                       }

                       override fun onShoeSelected(id: Int) {
                           findNavController().navigate(FragmentMainShoesListSelectionDirections.toOtherShoes(id))
                       }
                   })
                    adapter.setData(it)
                    adapter.notifyDataSetChanged()
                }.also {
                    mainEmptySection.visibility = GONE
                    mainRecyclerShoes.visibility = VISIBLE
                }
            else {
                mainEmptySection.visibility = VISIBLE
                mainRecyclerShoes.visibility = GONE
            }

        })
    }
}

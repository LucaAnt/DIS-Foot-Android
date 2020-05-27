package com.airbag.dis.disfoot.ui.main

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.airbag.dis.disfoot.R
import com.airbag.dis.disfoot.SHARED_PREFERENCES_KEY
import com.airbag.dis.disfoot.SHARED_PREFERENCES_KEY_REGISTERED
import com.airbag.dis.disfoot.ui.ViewModelCommon
import kotlinx.android.synthetic.main.fragment_main_shoes_list_selection.*


class FragmentMainShoesListSelection : Fragment() {

    private val commonViewModel: ViewModelCommon by activityViewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_shoes_list_selection, container, false)
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

        commonViewModel.scans.observe(viewLifecycleOwner, Observer {
            for (scan in it)
                Log.d("SELECTED SHOE",scan.toString())
        })
    }
}

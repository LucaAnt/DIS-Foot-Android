package com.airbag.dis.disfoot.ui.boarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.airbag.dis.disfoot.R


class FragmentBoarding : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_boarding, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //ToDo: ToRemove Dev Code
        this.view?.setOnClickListener {
            findNavController().navigate(R.id.toMainShoesSelection)
            print("Boarding page")
        }
    }

    private fun btnNextPressed() {
//        val sharedPreferencesEditor = activity?.getSharedPreferences(SHARED_PREFERENCES_KEY, Context.MODE_PRIVATE)?.edit()
//        sharedPreferencesEditor?.putBoolean(SHARED_PREFERENCES_KEY_BOARDED,true)
//        sharedPreferencesEditor?.commit()
    }


}

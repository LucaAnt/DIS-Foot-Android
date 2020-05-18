package com.airbag.dis.disfoot.ui.scanning

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.airbag.dis.disfoot.R

/**
 * A simple [Fragment] subclass.
 */
class FragmentScanInstructions : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_scan_instructions, container, false)
    }

}

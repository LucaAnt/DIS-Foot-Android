package com.airbag.dis.disfoot.ui.scanning

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.airbag.dis.disfoot.R
import kotlinx.android.synthetic.main.fragment_scan_instructions.*

class FragmentScanInstructions : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_scan_instructions, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentInstructionBtnNext.setOnClickListener { findNavController().navigate(FragmentScanInstructionsDirections.toFragmentPaperSexNameSelection()) }
    }

}

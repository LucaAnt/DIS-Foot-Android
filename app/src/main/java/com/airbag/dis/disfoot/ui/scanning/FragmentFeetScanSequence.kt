package com.airbag.dis.disfoot.ui.scanning

import  android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.airbag.dis.disfoot.MainNavDirections
import com.airbag.dis.disfoot.R
import com.airbag.dis.disfoot.ui.ViewModelCommon
import com.airbag.dis.disfoot.ui.ViewModelCommon.Side.MAIN
import kotlinx.android.synthetic.main.fragment_feet_scan_sequence.*


class FragmentFeetScanSequence : Fragment() {

    private val commonViewModel: ViewModelCommon by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_feet_scan_sequence, container, false)
    }

    @ExperimentalStdlibApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentScanSeqNextBtn.setOnClickListener {

            if (!commonViewModel.scanFinished)
                commonViewModel.nextStep(
                    requireContext(),
                    null
                )
            else
                findNavController().navigate(FragmentFeetScanSequenceDirections.toScanResult())
        }

        fragmentScanSeqBackBtn.setOnClickListener {
            if (commonViewModel.observedScanSteps.value?.count()!! >1)
                commonViewModel.prevStep()
            else
                findNavController().popBackStack()
        }

        fragmentScanSeqExitBtn.setOnClickListener { findNavController().navigate(MainNavDirections.toMain()) }


        if (commonViewModel.observedScanSteps.value?.isEmpty()!!)
            commonViewModel.nextStep(requireContext(), null)

        commonViewModel.observedScanSteps.observe(viewLifecycleOwner, Observer {
            val scanStep = it.last()
            if (scanStep != null) {
                fragmentScanSeqTitle.setText(scanStep.title)
                fragmentScanSeqText.setText(scanStep.text)
                fragmentScanSeqGuideImage.setImageResource(scanStep.guideImage)

                when (scanStep.side) {
                    MAIN -> {
                        fragmentScanSeqFootImage.visibility = GONE
                        fragmentScanSeqCounter.visibility = GONE
                        fragmentScanSeqNextBtn.setText(R.string.fragmentScanButtonMain)
                    }
                    else -> {
                        fragmentScanSeqNextBtn.setText(getString(R.string.fragmentScanButton,scanStep.side.ordinal))
                        if (scanStep.counterLabel != null) {
                            fragmentScanSeqFootImage.visibility = VISIBLE
                            fragmentScanSeqFootImage.setImageResource(scanStep.icoFeetImage)
                            fragmentScanSeqCounter.visibility = VISIBLE
                            fragmentScanSeqCounter.setText(scanStep.counterLabel)
                        }
                    }
                }

            }

        })
    }

}

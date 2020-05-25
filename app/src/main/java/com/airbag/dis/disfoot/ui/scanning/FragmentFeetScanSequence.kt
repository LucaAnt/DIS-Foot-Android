package com.airbag.dis.disfoot.ui.scanning

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentScanSeqNextBtn.setOnClickListener {
            commonViewModel.nextStep(
                requireContext(),
                null
            )
        }

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
                    }
                    else -> {
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

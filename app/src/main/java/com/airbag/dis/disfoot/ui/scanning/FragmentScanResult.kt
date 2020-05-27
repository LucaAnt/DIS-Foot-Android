package com.airbag.dis.disfoot.ui.scanning

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.airbag.dis.disfoot.MainNavDirections
import com.airbag.dis.disfoot.R
import com.airbag.dis.disfoot.model.*
import com.airbag.dis.disfoot.ui.ViewModelCommon
import kotlinx.android.synthetic.main.fragment_scan_result.*
import java.util.*


class FragmentScanResult : Fragment() {
    private val commonViewModel: ViewModelCommon by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_scan_result, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //resetView()
        commonViewModel.getScanResult()
        commonViewModel.currentShoeScanResult.observe(viewLifecycleOwner, Observer {
            if (it!=null)
            {
                resultTitle.setText(R.string.resultFinishedTitle)
                resultSubtitle.setText(R.string.resultFinishedSubtitle)
                resultMeasure.setText(it.size)
                resultMeasure.visibility = VISIBLE
                resultBtnFinish.visibility = VISIBLE
                resultAnimation.cancelAnimation()
                resultAnimation.setAnimation("gotResult.json")
                resultAnimation.repeatCount = 0
                resultAnimation.playAnimation()

            }
        })

        resultBtnFinish.setOnClickListener {
            commonViewModel.resetScanSequence()
            findNavController().navigate(MainNavDirections.toMain())
        }

    }


    private fun resetView() {
        resultTitle.setText(R.string.resultProcessingTitle)
        resultSubtitle.setText(R.string.resultProcessingSubtitle)
        resultMeasure.visibility = INVISIBLE
        resultBtnFinish.visibility = INVISIBLE
        resultAnimation.cancelAnimation()
        resultAnimation.setAnimation("waitForResult.json")
        resultAnimation.loop(true)
        resultAnimation.playAnimation()
    }
}

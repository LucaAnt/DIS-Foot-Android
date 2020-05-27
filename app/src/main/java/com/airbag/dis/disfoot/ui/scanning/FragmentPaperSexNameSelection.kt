package com.airbag.dis.disfoot.ui.scanning

import android.content.DialogInterface
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.core.view.forEach
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.airbag.dis.disfoot.R
import com.airbag.dis.disfoot.ui.ViewModelCommon
import kotlinx.android.synthetic.main.fragment_paper_sex_name_selection.*


class FragmentPaperSexNameSelection : Fragment(), View.OnClickListener {

    private val commonViewModel: ViewModelCommon by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_paper_sex_name_selection, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentSelectionA4View.setOnClickListener(this)
        fragmentSelectionLetterView.setOnClickListener(this)
        fragmentSelectionMaleView.setOnClickListener(this)
        fragmentSelectionFemaleView.setOnClickListener(this)
        fragmentSelectionScanNameInput.setOnClickListener { popscanNameInputDialog() }
        fragmentSelectionBtnNext.setOnClickListener {
            findNavController().navigate(FragmentPaperSexNameSelectionDirections.toScanningSequence())}

        commonViewModel.selectedPaper.observe(viewLifecycleOwner, Observer {
            resetAllviewsToDeselectedFor(Section.PAPER)
            when (it)
            {
                "A4" -> setSectionOtionSelected(R.id.fragmentSelectionA4View)
                "Letter" -> setSectionOtionSelected(R.id.fragmentSelectionLetterView)
            }

        })

        commonViewModel.selectedSex.observe(viewLifecycleOwner, Observer {
            resetAllviewsToDeselectedFor(Section.SEX)
            when (it)
            {
                "M" -> setSectionOtionSelected(R.id.fragmentSelectionMaleView)
                "F" -> setSectionOtionSelected(R.id.fragmentSelectionFemaleView)
            }

        })

        commonViewModel.selectedScanName.observe(viewLifecycleOwner, Observer {
            fragmentSelectionScanNameInput.setText(commonViewModel.selectedScanName.value)
        })
    }

    override fun onClick(v: View?) {
        if (v!=null)
            when (v.id)
            {
                R.id.fragmentSelectionA4View -> commonViewModel.selectedPaper.value = "A4"
                R.id.fragmentSelectionLetterView -> commonViewModel.selectedPaper.value = "Letter"
                R.id.fragmentSelectionMaleView -> commonViewModel.selectedSex.value = "M"
                R.id.fragmentSelectionFemaleView ->  commonViewModel.selectedSex.value = "F"
            }
//        resetAllviewsToDeselected()
//        if(v!=null)
//            (v as LinearLayout).forEach {
//                it.alpha = 1F
//                if (it is ImageView)
//                    (it as ImageView)?.setColorFilter(ContextCompat.getColor(requireContext(), R.color.SelectedColor), android.graphics.PorterDuff.Mode.SRC_IN)
//                else if (it is TextView)
//                    (it as TextView)?.setTextColor(resources.getColor(R.color.SelectedColor))
//            }

    }

    fun resetAllviewsToDeselectedFor(section : Section)
    {
        var viewGroups = listOf<LinearLayout>()

        when(section)
        {
            Section.PAPER-> viewGroups = listOf(fragmentSelectionA4View,fragmentSelectionLetterView)
            Section.SEX-> viewGroups = listOf(fragmentSelectionMaleView,fragmentSelectionFemaleView)
        }
       viewGroups.forEach {
            it.forEach {
                it.alpha = 0.2F
                if (it is ImageView)
                    (it as ImageView)?.setColorFilter(R.color.White)
                else if (it is TextView)
                    (it as TextView)?.setTextColor(resources.getColor(R.color.White))
            }
        }
    }

    fun setSectionOtionSelected(id : Int)
    {
        val optionView = view?.findViewById<LinearLayout>(id)

        if (optionView != null)
            optionView.forEach {
                    it.alpha = 1F
                    if (it is ImageView)
                        (it as ImageView)?.setColorFilter(ContextCompat.getColor(requireContext(), R.color.SelectedColor), android.graphics.PorterDuff.Mode.SRC_IN)
                    else if (it is TextView)
                        (it as TextView)?.setTextColor(resources.getColor(R.color.SelectedColor))
                }
    }

    fun popscanNameInputDialog()
    {
        val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        builder.setTitle(getString(R.string.fragmentSelectionDialogText))

        val input = EditText(requireContext())

        input.inputType = InputType.TYPE_CLASS_TEXT
        builder.setView(input)
        builder.setPositiveButton("OK", DialogInterface.OnClickListener { dialog, _ -> commonViewModel.selectedScanName.value = input.text.toString() })
        builder.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, _ -> dialog.cancel() })

        builder.show()
    }

    enum class Section{
        PAPER,SEX
    }
}

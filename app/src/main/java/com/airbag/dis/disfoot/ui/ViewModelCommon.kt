package com.airbag.dis.disfoot.ui

import android.app.Application
import android.content.Context
import androidx.lifecycle.*
import com.airbag.dis.disfoot.R
import com.airbag.dis.disfoot.api.disApiManager
import com.airbag.dis.disfoot.model.Shoe
import com.airbag.dis.disfoot.ui.ViewModelCommon.Feet.*
import com.airbag.dis.disfoot.ui.ViewModelCommon.Side.*

class ViewModelCommon : ViewModel() {

    //val shoesModels = MutableLiveData<Map<String, List<Shoe>>>().also { loadShoes() }

    val shoesModels : LiveData<Map<String, List<Shoe>>> = liveData {
        val data = disApiManager().dis.getShoesModels() // suspending
        emit(data)
    }

    //    private fun loadShoes()
//    {
//        viewModelScope.launch {
//            val result = disService.dis.getShoesModels()
//            shoesModels.postValue(result)
//        }
//    }

    var selectedShoeId : MutableLiveData<String> = MutableLiveData()

    var selectedSex : MutableLiveData<String> = MutableLiveData("M")

    var selectedPaper : MutableLiveData<String> = MutableLiveData("A4")

    var selectedName  : MutableLiveData<String> = MutableLiveData("")


    private var scanSteps  = mutableListOf<ScanStep>()

    var observedScanSteps : MutableLiveData<MutableList<ScanStep>> = MutableLiveData(mutableListOf())

    public fun nextStep(context : Context,measeure : Int?)
    {
            when (scanSteps.count()) {
                0->scanSteps.add(ScanStep(context,RIGHT,MAIN))
                1->scanSteps.add(ScanStep(context,RIGHT,INNER))
                2->scanSteps.add(ScanStep(context,RIGHT,TOP))
                3->scanSteps.add(ScanStep(context,RIGHT,OUTER))
                4->scanSteps.add(ScanStep(context,LEFT,MAIN))
                5->scanSteps.add(ScanStep(context,LEFT,INNER))
                6->scanSteps.add(ScanStep(context,LEFT,TOP))
                7->scanSteps.add(ScanStep(context,LEFT,OUTER))
            }

        observedScanSteps.value = scanSteps
    }

    @ExperimentalStdlibApi
    public fun prevStep()
    {
        scanSteps.removeLast()
        observedScanSteps.value = scanSteps
    }

    enum class Feet{
        RIGHT,LEFT
    }

    enum class Side {
        MAIN,INNER,TOP,OUTER
    }

    class ScanStep(var context : Context, val feet : Feet, val side : Side)
    {
        val title : String
        get() {
            when(feet)
            {
                RIGHT ->
                                when (side)
                                {
                                    MAIN -> return context.getString(R.string.fragmentScanRightMainTitle)
                                    INNER -> return context.getString(R.string.fragmentScan1RightInnerTitle)
                                    TOP ->return context.getString(R.string.fragmentScan2RightTopTitle)
                                    OUTER -> return context.getString(R.string.fragmentScan3RightOuterTitle)
                                }
                LEFT ->
                                when (side)
                                {
                                    MAIN -> return context.getString(R.string.fragmentScanLeftMainTitle)
                                    INNER -> return context.getString(R.string.fragmentScan4LeftInnerTitle)
                                    TOP ->return context.getString(R.string.fragmentScan5LeftTopTitle)
                                    OUTER -> return context.getString(R.string.fragmentScan6LeftOuterTitle)
                                }
            }
            return ""
        }

        val text : String
            get() {
                when(feet)
                {
                    RIGHT ->
                        when (side)
                        {
                            MAIN -> return context.getString(R.string.fragmentScanRightMainText)
                            INNER -> return context.getString(R.string.fragmentScan1RightInnerText)
                            TOP ->return context.getString(R.string.fragmentScan2RightTopText)
                            OUTER -> return context.getString(R.string.fragmentScan3RightOuterText)
                        }
                    LEFT ->
                        when (side)
                        {
                            MAIN -> return context.getString(R.string.fragmentScanLefttMainText)
                            INNER -> return context.getString(R.string.fragmentScan4LefttInnerText)
                            TOP ->return context.getString(R.string.fragmentScan5LeftTopText)
                            OUTER -> return context.getString(R.string.fragmentScan6LeftOuterText)
                        }
                }
                return ""
            }

        val guideImage : Int
            get() {
                when(feet)
                {
                    RIGHT ->
                        when (side)
                        {
                            MAIN -> return R.drawable.right_main
                            INNER -> return R.drawable.right_01_inner
                            TOP ->return R.drawable.right_02_top
                            OUTER -> return R.drawable.right_03_outer
                        }
                    LEFT ->
                        when (side)
                        {
                            MAIN -> return R.drawable.left_main
                            INNER -> return R.drawable.left_04_inner
                            TOP ->return R.drawable.left_05_top
                            OUTER -> return R.drawable.left_06_outer
                        }
                }
                return 0
            }

        val icoFeetImage : Int
        get() {
            when(feet)
            {
                RIGHT -> return R.drawable.ic_right_green_foot
                LEFT -> return R.drawable.ic_left_yellow_foot
            }
        }

        val counterLabel : String?
            get() {
                when(side)
                {
                    MAIN -> return null
                    INNER,TOP,OUTER-> {
                        val str = context.getString(R.string.fragmentScanCounter,side.ordinal,OUTER.ordinal)
                        return str
                    }
                }

                return null
            }


    }



}
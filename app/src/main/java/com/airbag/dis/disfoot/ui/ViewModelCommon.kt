package com.airbag.dis.disfoot.ui

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.*
import com.airbag.dis.disfoot.R
import com.airbag.dis.disfoot.api.disApiManager
import com.airbag.dis.disfoot.model.*
import com.airbag.dis.disfoot.room.DisRoomDatabase
import com.airbag.dis.disfoot.ui.ViewModelCommon.Feet.*
import com.airbag.dis.disfoot.ui.ViewModelCommon.Side.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*

class ViewModelCommon(application: Application) : AndroidViewModel(application) {

    val scans: LiveData<List<SelectedShoe>> =
        DisRoomDatabase.getDatabase(getApplication()).daoSelectedShoe().getAllSelectedShoes()

    val otherScans : MutableLiveData<Map<String,List<OtherShoe>>> = MutableLiveData(mapOf())

//    fun insertScan(selectedShoe: SelectedShoe){
//        viewModelScope.launch(Dispatchers.IO) {
//            DisRoomDatabase.getDatabase(getApplication()).daoSelectedShoe().insert(selectedShoe)
//        }
//    }

    //val shoesModels = MutableLiveData<Map<String, List<Shoe>>>().also { loadShoes() }
    //    private fun loadShoes()
//    {
//        viewModelScope.launch {
//            val result = disService.dis.getShoesModels()
//            shoesModels.postValue(result)
//        }
//    }

    val shoesModels: LiveData<Map<String, List<Shoe>>> = liveData {
        val data = disApiManager().dis.getShoesModels() // suspending
        emit(data)
    }

    fun getShoeById(shoeId: String): Shoe? {
        val shoesDic = shoesModels.value
        if (shoesDic != null)
            for (sCollection in shoesDic) {
                for (s in sCollection.value) {
                    if (shoeId == s.uniqueCode)
                        return s
                }
            }

        return null
    }

    fun getShoeCollection(shoeId: String): String? {
        val shoesDic = shoesModels.value
        if (shoesDic != null)
            for (sCollection in shoesDic) {
                for (s in sCollection.value) {
                    if (shoeId == s.uniqueCode)
                        return sCollection.key
                }
            }

        return null
    }

    var currentShoeScanResult: MutableLiveData<ShoeSizeResponse> =
        MutableLiveData<ShoeSizeResponse>()

    val selectedShoeId: MutableLiveData<String> = MutableLiveData()

    val selectedSex: MutableLiveData<String> = MutableLiveData("M")

    val selectedPaper: MutableLiveData<String> = MutableLiveData("A4")

    val selectedScanName: MutableLiveData<String> = MutableLiveData("")


    private var scanSteps = mutableListOf<ScanStep>()

    val observedScanSteps: MutableLiveData<MutableList<ScanStep>> = MutableLiveData(mutableListOf())

    var scanFinished = false
    fun nextStep(context: Context, measeure: Int?) {
        when (scanSteps.count()) {
            0 -> scanSteps.add(ScanStep(context, RIGHT, MAIN))
            1 -> scanSteps.add(ScanStep(context, RIGHT, INNER))
            2 -> scanSteps.add(ScanStep(context, RIGHT, TOP))
            3 -> scanSteps.add(ScanStep(context, RIGHT, OUTER))
            4 -> scanSteps.add(ScanStep(context, LEFT, MAIN))
            5 -> scanSteps.add(ScanStep(context, LEFT, INNER))
            6 -> scanSteps.add(ScanStep(context, LEFT, TOP))
            7 -> scanSteps.add(ScanStep(context, LEFT, OUTER)).also { scanFinished = true }
        }

        observedScanSteps.value = scanSteps
    }

    @ExperimentalStdlibApi
    fun prevStep() {
        scanSteps.removeLast()
        observedScanSteps.value = scanSteps
    }

    fun resetScanSequence() {
        scanSteps = mutableListOf()
        observedScanSteps.value = scanSteps
        currentShoeScanResult = MutableLiveData<ShoeSizeResponse>()
        scanFinished = false
        selectedScanName.value = null
        selectedSex.value = "M"
        selectedPaper.value = "A4"
        selectedShoeId.value = null
    }

    fun getScanResult() {
        viewModelScope.launch(Dispatchers.IO) {
            delay(5000)

            //TODO: Request composition here <--
            val request = MeasureRequest(
                selectedShoeId.value!!,
                Date().toString(),
                "test@airbag.it",
                "Luca", selectedSex.value!!,
                selectedScanName.value ?: "",
                ShoeSizeMeasure.getDummyScanRight(),
                ShoeSizeMeasure.getDummyScanLeft()
            )

            val result = disApiManager().dis.getShoeSizeResponse(request)

            selectedShoeId.value?.let { shoeIdString->
                result[shoeIdString]?.let {
                    val insertedSelectedShoeId = addShoeToLocal(it)
                    insertedSelectedShoeId?.let {
                        if (it != -1L) {
                            addOtherShoesToLocal(it, result)
                        }
                    }
                    currentShoeScanResult.postValue(it)
                }
            }
        }
    }

    private suspend fun addShoeToLocal(shoeMeasure: ShoeSizeResponse): Long {

        selectedShoeId.value?.let { it ->
            getShoeById(it)?.let {
                return DisRoomDatabase.getDatabase(getApplication()).daoSelectedShoe().insert(
                    SelectedShoe(
                        selectedShoe = it,
                        scanName = selectedScanName.value ?: "",
                        scanTime = Date().toString(),
                        size = shoeMeasure.size,
                        fit = shoeMeasure.fittingLevel
                    ))
            }
        }
        return -1
    }

    private suspend fun addOtherShoesToLocal(
        scannedShoeId: Long,
        otherMeasures: Map<String, ShoeSizeResponse>
    ) {
        val otherShoesToSave = mutableListOf<OtherShoe>()
        shoesModels.value?.let { it ->
            it.forEach { collection ->
                collection.value.forEach { currentShoe->
                    otherMeasures[currentShoe.uniqueCode]?.let {
                        otherShoesToSave.add(OtherShoe(scannedShoeId,currentShoe,collection.key,it.size,it.fittingLevel))
                    }
                }
            }
        }
        DisRoomDatabase.getDatabase(getApplication()).daoOtherShoes().insertAll(otherShoesToSave).forEach { Log.d("ADDED Other",it.toString()) }
    }

    suspend fun deleteShoeScan(shoeItem: SelectedShoe) {
        DisRoomDatabase.getDatabase(getApplication()).daoSelectedShoe().delete(shoeItem)
        DisRoomDatabase.getDatabase(getApplication()).daoOtherShoes().deleteAll(shoeItem.id.toLong())
    }

    suspend fun getOtherModelsScan(id : Long){
        val otherShoes: List<OtherShoe> = DisRoomDatabase.getDatabase(getApplication()).daoOtherShoes().getAllOtherShoesForShoeScan(id)
        val mappedOtherShoes : MutableMap<String, MutableList<OtherShoe>> = mutableMapOf()

        otherShoes.forEach { currentShoe ->
            mappedOtherShoes[currentShoe.category]?.let {
                it.add(currentShoe)
            } ?: kotlin.run {
                mappedOtherShoes.put(currentShoe.category, mutableListOf(currentShoe))
            }
        }
        otherScans.postValue(mappedOtherShoes)
    }



    enum class Feet {
        RIGHT, LEFT
    }

    enum class Side {
        MAIN, INNER, TOP, OUTER
    }

    class ScanStep(var context: Context, val feet: Feet, val side: Side) {
        val title: CharSequence
            get() {
                when (feet) {
                    RIGHT ->
                        when (side) {
                            MAIN -> return context.getText(R.string.fragmentScanRightMainTitle) //context.getText(R.string.fragmentScanRightMainTitle)
                            INNER -> return context.getText(R.string.fragmentScan1RightInnerTitle)
                            TOP -> return context.getText(R.string.fragmentScan2RightTopTitle)
                            OUTER -> return context.getText(R.string.fragmentScan3RightOuterTitle)
                        }
                    LEFT ->
                        when (side) {
                            MAIN -> return context.getText(R.string.fragmentScanLeftMainTitle)
                            INNER -> return context.getText(R.string.fragmentScan4LeftInnerTitle)
                            TOP -> return context.getText(R.string.fragmentScan5LeftTopTitle)
                            OUTER -> return context.getText(R.string.fragmentScan6LeftOuterTitle)
                        }
                }
                return ""
            }

        val text: CharSequence
            get() {
                when (feet) {
                    RIGHT ->
                        when (side) {
                            MAIN -> return context.getText(R.string.fragmentScanRightMainText)
                            INNER -> return context.getText(R.string.fragmentScan1RightInnerText)
                            TOP -> return context.getText(R.string.fragmentScan2RightTopText)
                            OUTER -> return context.getText(R.string.fragmentScan3RightOuterText)
                        }
                    LEFT ->
                        when (side) {
                            MAIN -> return context.getText(R.string.fragmentScanLefttMainText)
                            INNER -> return context.getText(R.string.fragmentScan4LefttInnerText)
                            TOP -> return context.getText(R.string.fragmentScan5LeftTopText)
                            OUTER -> return context.getText(R.string.fragmentScan6LeftOuterText)
                        }
                }
                return ""
            }

        val guideImage: Int
            get() {
                when (feet) {
                    RIGHT ->
                        when (side) {
                            MAIN -> return R.drawable.right_main
                            INNER -> return R.drawable.right_01_inner
                            TOP -> return R.drawable.right_02_top
                            OUTER -> return R.drawable.right_03_outer
                        }
                    LEFT ->
                        when (side) {
                            MAIN -> return R.drawable.left_main
                            INNER -> return R.drawable.left_04_inner
                            TOP -> return R.drawable.left_05_top
                            OUTER -> return R.drawable.left_06_outer
                        }
                }
                return 0
            }

        val icoFeetImage: Int
            get() {
                when (feet) {
                    RIGHT -> return R.drawable.ic_right_green_foot
                    LEFT -> return R.drawable.ic_left_yellow_foot
                }
            }

        val counterLabel: String?
            get() {
                when (side) {
                    MAIN -> return null
                    INNER, TOP, OUTER -> {
                        val str = context.getString(
                            R.string.fragmentScanCounter,
                            side.ordinal,
                            OUTER.ordinal
                        )
                        return str
                    }
                }

                return null
            }


    }


}
package com.airbag.dis.disfoot.ui

import androidx.lifecycle.*
import com.airbag.dis.disfoot.api.disApiManager
import com.airbag.dis.disfoot.model.Shoe

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





}
package com.example.covid_sms_gr.data.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

open class BaseViewModel : ViewModel() {

    val error = SingleLiveEvent<String>()
    val load = MutableLiveData<Boolean>()


    fun launch(shouldLoad: Boolean = false, function: suspend () -> Unit) {
        viewModelScope.launch {
            load.value = shouldLoad
            try {
                function.invoke()
            } catch (e: Exception) {
                error.value = "Κατι πήγε στραβά"
            }
            load.value = false
        }
    }

}
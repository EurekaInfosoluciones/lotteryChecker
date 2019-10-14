package com.eurekainfosoluciones.lottery.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.eurekainfosoluciones.lottery.viewmodels.states.*

class ScannerViewModel : ViewModel() {

    val scanningState: LiveData<ScanningState>
        get() = _scanningState

    private val _scanningState = MutableLiveData<ScanningState>().apply { value = Scanning }

    suspend fun recognized(displayValue: String) {
        _scanningState.postValue(Validating) // stop camera change the ui to a progress
        //TODO use case to validate
        if (displayValue.matches("A=\\d{34};P=\\d;S=.*;W=\\d;[.\\d=\\d+:?]+;T=\\d{5}-\\d".toRegex())) {
            _scanningState.postValue(Supported)
        } else {
            _scanningState.postValue(Unsupported)
            //In the future possibility to upload a file so we can support it
        }
        //use case to check price
        //TOBE done... it has security, so not really worth doing this unless I add a server in the middle
    }

}

package com.eurekainfosoluciones.lottery.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.eurekainfosoluciones.lottery.android.RequestPermission
import com.eurekainfosoluciones.lottery.viewmodels.states.PermissionState

class OnBoardingCameraViewModel(private val requestPermission: RequestPermission) : ViewModel() {

    val permissionState: LiveData<PermissionState>
        get() = _permissionState

    private val _permissionState = MutableLiveData<PermissionState>()

    fun requestPermission() {
        requestPermission.request()
    }

    fun checkPermission(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestPermission.verifyResult(requestCode, permissions, grantResults)) {
            _permissionState.postValue(PermissionState.GRANTED)
        } else {
            _permissionState.postValue(PermissionState.DECLINED)
        }
    }

}

package com.eurekainfosoluciones.lottery.viewmodels

import androidx.lifecycle.ViewModel
import com.eurekainfosoluciones.lottery.android.RequestPermission

class OnBoardingCameraViewModel(private val requestPermission: RequestPermission) : ViewModel() {

    fun requestPermission() {
        requestPermission.request()
    }

    fun checkPermission(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        requestPermission.verifyResult(requestCode, permissions, grantResults)
    }

}

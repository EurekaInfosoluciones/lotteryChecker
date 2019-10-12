package com.eurekainfosoluciones.lottery.injectors

import android.Manifest
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.eurekainfosoluciones.lottery.android.FragmentRequestPermission
import com.eurekainfosoluciones.lottery.viewmodels.OnBoardingCameraViewModel
import com.eurekainfosoluciones.lottery.viewmodels.ScannerViewModel

@Suppress("UNCHECKED_CAST")
object ViewModelInjectors {

    fun onboardingCameraViewModelFactory(fragment: Fragment) = object : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            OnBoardingCameraViewModel(
                FragmentRequestPermission(
                    fragment,
                    Manifest.permission.CAMERA
                )
            ) as T
    }

    fun scannerViewModelFactory() = object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T =
            ScannerViewModel() as T
    }

}
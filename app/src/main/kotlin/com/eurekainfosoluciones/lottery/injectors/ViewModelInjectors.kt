package com.eurekainfosoluciones.lottery.injectors

import android.Manifest
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.eurekainfosoluciones.lottery.android.FragmentRequestPermission
import com.eurekainfosoluciones.lottery.viewmodels.OnBoardingCameraViewModel

object ViewModelInjectors {

    fun onboardingCameraViewModelFactory(fragment: Fragment) = object : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            OnBoardingCameraViewModel(
                FragmentRequestPermission(
                    fragment,
                    Manifest.permission.CAMERA
                )
            ) as T
    }


}
package com.eurekainfosoluciones.lottery.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.eurekainfosoluciones.lottery.R
import com.eurekainfosoluciones.lottery.injectors.ViewModelInjectors
import com.eurekainfosoluciones.lottery.viewmodels.OnBoardingCameraViewModel
import com.eurekainfosoluciones.lottery.viewmodels.states.PermissionState

class OnBoardingCameraFragment : Fragment() {

    private val onboardingCameraViewModel: OnBoardingCameraViewModel by viewModels {
        ViewModelInjectors.onboardingCameraViewModelFactory(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_camera, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.findViewById<View>(R.id.cameraCta)
            .setOnClickListener { onboardingCameraViewModel.requestPermission() }
        onboardingCameraViewModel.permissionState.observe(
            this,
            Observer { permissionState ->
                when (permissionState) {
                    PermissionState.GRANTED -> findNavController().navigate(R.id.action_onBoardingCameraFragment_to_scannerFragment)
                    PermissionState.DECLINED -> Log.v("DMV", "declined")
                    //change ui so we show the app permission screen
                    else -> Log.v("DMV", "Other status")
                }
            }
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        onboardingCameraViewModel.checkPermission(requestCode, permissions, grantResults)
    }

}

package com.eurekainfosoluciones.lottery.fragments.lifecyclesobservers

import android.util.Log
import android.view.SurfaceHolder
import android.view.SurfaceView
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewModelScope
import com.eurekainfosoluciones.lottery.viewmodels.ScannerViewModel
import com.google.android.gms.vision.CameraSource
import com.google.android.gms.vision.Detector
import com.google.android.gms.vision.FocusingProcessor
import com.google.android.gms.vision.Tracker
import com.google.android.gms.vision.barcode.Barcode
import com.google.android.gms.vision.barcode.BarcodeDetector
import kotlinx.coroutines.launch


class CameraLifecycleObserver(
    private val surfaceView: SurfaceView,
    private val viewModel: ScannerViewModel
) : DefaultLifecycleObserver,
    SurfaceHolder.Callback {

    private val barcodeDetector: BarcodeDetector by lazy {
        BarcodeDetector.Builder(surfaceView.context)
            .setBarcodeFormats(Barcode.QR_CODE) // Once is probably using Barcode.CODABAR
            .build()
    }
    private val cameraSource: CameraSource by lazy {
        CameraSource.Builder(surfaceView.context, barcodeDetector)
            .setFacing(CameraSource.CAMERA_FACING_BACK)
            .setAutoFocusEnabled(true)
            .build()
    }

    override fun onCreate(owner: LifecycleOwner) {
        surfaceView.holder.addCallback(this)
    }

    override fun surfaceChanged(holder: SurfaceHolder?, p1: Int, p2: Int, p3: Int) = Unit

    override fun surfaceCreated(holder: SurfaceHolder) {
        barcodeDetector.setProcessor(
            BarcodeFocusingProcessor(barcodeDetector, BarcodeTracker())
        )
        cameraSource.start(holder)
    }

    override fun surfaceDestroyed(surfaceHolder: SurfaceHolder) {
        cameraSource.release()
    }

    private class BarcodeFocusingProcessor(
        barcodeDetector: Detector<Barcode>,
        barcodeTracker: Tracker<Barcode>
    ) :
        FocusingProcessor<Barcode>(barcodeDetector, barcodeTracker) {

        override fun selectFocus(detections: Detector.Detections<Barcode>) =
            detections.detectedItems?.keyAt(0) ?: -1
    }

    private inner class BarcodeTracker : Tracker<Barcode>() {
        override fun onNewItem(id: Int, barcode: Barcode) {
            Log.v("DMV", "Barcode is ${barcode.displayValue} + ${this}")
            //maybe listener to the fragment instead?
            viewModel.viewModelScope.launch {
                viewModel.recognized(barcode.displayValue)
            }
//            VibratorManagerInjector.vibratorManager(surfaceView.context).vibrateForSuccess()
//            cameraSource.stop()
        }
    }
}

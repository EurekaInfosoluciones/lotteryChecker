package com.eurekainfosoluciones.lottery.fragments

import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import com.eurekainfosoluciones.lottery.R
import com.google.android.gms.vision.CameraSource
import com.google.android.gms.vision.Detector
import com.google.android.gms.vision.FocusingProcessor
import com.google.android.gms.vision.Tracker
import com.google.android.gms.vision.barcode.Barcode
import com.google.android.gms.vision.barcode.BarcodeDetector
import kotlinx.android.synthetic.main.fragment_scanner.*
import com.eurekainfosoluciones.lottery.injectors.VibratorManagerInjector.vibratorManager


class ScannerFragment : Fragment() {

    private var surfaceHolderCreated = false
    private val barcodeDetector: BarcodeDetector by lazy {
        BarcodeDetector.Builder(requireContext())
            .setBarcodeFormats(Barcode.QR_CODE)
            .build()
    }

    private val cameraSource: CameraSource by lazy {
        val displayMetrics = DisplayMetrics()
        requireActivity().windowManager.defaultDisplay.getMetrics(displayMetrics)
        val width = displayMetrics.widthPixels
        val height = displayMetrics.heightPixels

        CameraSource.Builder(requireContext(), barcodeDetector)
            .setFacing(CameraSource.CAMERA_FACING_BACK)
            .setRequestedPreviewSize(width, height)
            .setAutoFocusEnabled(true)
            .build()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_scanner, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.findViewById<SurfaceView>(R.id.barcodeSurfaceView).holder.addCallback(object :
            SurfaceHolder.Callback {

            override fun surfaceChanged(p0: SurfaceHolder?, p1: Int, p2: Int, p3: Int) = Unit

            override fun surfaceCreated(p0: SurfaceHolder?) {
                surfaceHolderCreated = true
                barcodeDetector.setProcessor(
                    BarcodeFocusingProcessor(
                        barcodeDetector,
                        BarcodeTracker()
                    )
                )
                startCamera()
            }

            override fun surfaceDestroyed(p0: SurfaceHolder?) {
                surfaceHolderCreated = false
            }

        })//TODO remove this callback
    }

    private fun startCamera() {
        if (surfaceHolderCreated) {
            cameraSource.start(barcodeSurfaceView.holder)
        }
    }

    private class BarcodeFocusingProcessor(
        barcodeDetector: Detector<Barcode>,
        barcodeTracker: Tracker<Barcode>
    ) :
        FocusingProcessor<Barcode>(barcodeDetector, barcodeTracker) {

        override fun selectFocus(detections: Detector.Detections<Barcode>): Int {
            detections.detectedItems?.let {
                return if (it.size() != 0) it.keyAt(0) else -1
            }
            return -1
        }
    }

    private inner class BarcodeTracker : Tracker<Barcode>() {
        override fun onNewItem(id: Int, barcode: Barcode) {
            Log.v("DMV", "Barcode is ${barcode.displayValue}")
            vibratorManager(requireContext()).vibrateForSuccess()
        }
    }
}
package com.eurekainfosoluciones.lottery.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.eurekainfosoluciones.lottery.R
import com.eurekainfosoluciones.lottery.fragments.lifecyclesobservers.CameraLifecycleObserver
import com.eurekainfosoluciones.lottery.injectors.ViewModelInjectors.scannerViewModelFactory
import com.eurekainfosoluciones.lottery.viewmodels.ScannerViewModel


class ScannerFragment : Fragment() {

    private val viewModel: ScannerViewModel by viewModels {
        scannerViewModelFactory()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_scanner, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        lifecycle.addObserver(CameraLifecycleObserver(view.findViewById(R.id.barcodeSurfaceView), viewModelStore))
    }
}
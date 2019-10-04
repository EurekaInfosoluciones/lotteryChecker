package com.eurekainfosoluciones.lottery.android

import android.content.pm.PackageManager
import androidx.fragment.app.Fragment

class FragmentRequestPermission(
    private val fragment: Fragment,
    private val permission: String
) :
    RequestPermission {
    override fun request() {
        fragment.requestPermissions(arrayOf(permission), PERMISSION_REQUEST_CODE)
    }

    override fun verifyResult(
        requestCode: Int,
        permissions: Array<out String>,
        results: IntArray
    ): Boolean {
        return requestCode == PERMISSION_REQUEST_CODE && results.isNotEmpty()
                && results[0] == PackageManager.PERMISSION_GRANTED
    }

    private companion object {
        private const val PERMISSION_REQUEST_CODE = 123
    }
}
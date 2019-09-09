package com.eurekainfosoluciones.lottery.android

import androidx.fragment.app.Fragment

class FragmentRequestPermission(
    private val fragment: Fragment,
    private val permission: Array<String>
) :
    RequestPermission {
    override fun request() {
        fragment.requestPermissions(permission, PERMISSION_REQUEST_CODE)
    }

    override fun verifyResult(
        requestCode: Int,
        permissions: Array<out String>,
        results: IntArray
    ): Boolean {
        return false
    }

    private companion object {
        private const val PERMISSION_REQUEST_CODE = 123
    }
}
package com.eurekainfosoluciones.lottery.android

import android.content.pm.PackageManager
import androidx.fragment.app.Fragment
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FragmentRequestPermissionTest {

    private val fragmentRequestPermission = FragmentRequestPermission(Fragment(), "Blah")

    @Test
    fun returnsTrueIfPermissionIsGranted() {
        assertThat(
            fragmentRequestPermission.verifyResult(
                123,
                arrayOf("Blah"),
                intArrayOf(PackageManager.PERMISSION_GRANTED)
            )
        ).isTrue()
    }

    @Test
    fun returnsFalseIfPermissionIsDenied() {
        assertThat(
            fragmentRequestPermission.verifyResult(
                123,
                arrayOf("Blah"),
                intArrayOf(PackageManager.PERMISSION_DENIED)
            )
        ).isFalse()
    }

    @Test
    fun returnsFalseIfPermissionIsGranted_differentRequestCode() {
        assertThat(
            fragmentRequestPermission.verifyResult(
                1242,
                arrayOf("Blah"),
                intArrayOf(PackageManager.PERMISSION_DENIED)
            )
        ).isFalse()
    }
}
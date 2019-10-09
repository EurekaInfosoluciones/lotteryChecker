package com.eurekainfosoluciones.lottery.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.eurekainfosoluciones.lottery.android.RequestPermission
import com.eurekainfosoluciones.lottery.viewmodels.states.PermissionState
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

class OnBoardingCameraViewModelTest {

    @Rule @JvmField val mockery: MockitoRule = MockitoJUnit.rule()
    @get:Rule val rule = InstantTaskExecutorRule()

    @Mock private lateinit var requestPermission: RequestPermission

    private lateinit var onBoardingCameraViewModel: OnBoardingCameraViewModel

    @Before fun setUp() {
        onBoardingCameraViewModel = OnBoardingCameraViewModel(requestPermission)
    }

    @Test fun requestsPermissionUsingCollaborator() {
        onBoardingCameraViewModel.requestPermission()

        verify(requestPermission).request()
    }

    @Test fun updatesPermissionState_permissionGranted() {
        val permissions = arrayOf(A_PERMISSION)
        val results = intArrayOf(A_RESULT)
        onBoardingCameraViewModel.permissionState.observeForever { }

        whenever(
            requestPermission.verifyResult(
                A_REQUEST_CODE,
                permissions,
                results
            )
        ).thenAnswer { true }

        onBoardingCameraViewModel.checkPermission(A_REQUEST_CODE, permissions, results)

        assertThat(onBoardingCameraViewModel.permissionState.value).isEqualTo(PermissionState.GRANTED)
    }

    @Test fun updatesPermissionState_permissionDeclined() {
        val permissions = arrayOf(A_PERMISSION)
        val results = intArrayOf(A_RESULT)
        onBoardingCameraViewModel.permissionState.observeForever { }

        whenever(
            requestPermission.verifyResult(
                A_REQUEST_CODE,
                permissions,
                results
            )
        ).thenAnswer { false }

        onBoardingCameraViewModel.checkPermission(A_REQUEST_CODE, permissions, results)

        assertThat(onBoardingCameraViewModel.permissionState.value).isEqualTo(PermissionState.DECLINED)
    }

    private companion object {
        private const val A_REQUEST_CODE = 1
        private const val A_PERMISSION = "give me this powerful permission"
        private const val A_RESULT = 12
    }
}
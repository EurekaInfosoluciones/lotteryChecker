package com.eurekainfosoluciones.lottery.viewmodels

import com.eurekainfosoluciones.lottery.android.RequestPermission
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

class OnBoardingCameraViewModelTest {

    @Rule @JvmField
    val mockery: MockitoRule = MockitoJUnit.rule()

    @Mock
    private lateinit var requestPermission: RequestPermission

    private lateinit var onBoardingCameraViewModel: OnBoardingCameraViewModel

    @Before fun setUp() {
        onBoardingCameraViewModel = OnBoardingCameraViewModel(requestPermission)
    }

    @Test fun requestsPermissionUsingCollaborator() {
        onBoardingCameraViewModel.requestPermission()

        verify(requestPermission).request()
    }

    @Test fun delegatesPermissionCheckToTheCollaborator() {
        val permissions = arrayOf(A_PERMISSION)
        val results = intArrayOf(A_RESULT)

        onBoardingCameraViewModel.checkPermission(A_REQUEST_CODE, permissions, results)

        verify(requestPermission).verifyResult(A_REQUEST_CODE, permissions, results)
    }

    private companion object {
        private const val A_REQUEST_CODE = 1
        private const val A_PERMISSION = "give me this powerful permission"
        private const val A_RESULT = 12
    }
}
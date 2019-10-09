package com.eurekainfosoluciones.lottery.android

import android.os.Build
import android.os.VibrationEffect
import android.os.VibrationEffect.createOneShot
import android.os.Vibrator
import androidx.annotation.RequiresApi

private const val SUCCESS_DURATION = 500L

interface VibratorManager {

    fun vibrateForSuccess()
}

@Suppress("DEPRECATION")
class LegacyVibratorManager(private val vibrator: Vibrator?) : VibratorManager {

    override fun vibrateForSuccess() {
        vibrator?.vibrate(SUCCESS_DURATION)
    }
}

@RequiresApi(Build.VERSION_CODES.O)
class OreoAndAboveVibratorManager(private val vibrator: Vibrator?) : VibratorManager {

    override fun vibrateForSuccess() {
        vibrator?.vibrate(
            createOneShot(SUCCESS_DURATION, VibrationEffect.DEFAULT_AMPLITUDE)
        )
    }
}
package com.eurekainfosoluciones.lottery.injectors

import android.content.Context
import android.os.Build
import android.os.Vibrator
import com.eurekainfosoluciones.lottery.android.LegacyVibratorManager
import com.eurekainfosoluciones.lottery.android.OreoAndAboveVibratorManager
import com.eurekainfosoluciones.lottery.android.VibratorManager

object VibratorManagerInjector {

    fun vibratorManager(context: Context): VibratorManager {
        val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator?

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            OreoAndAboveVibratorManager(vibrator)
        } else {
            LegacyVibratorManager(vibrator)
        }
    }

}
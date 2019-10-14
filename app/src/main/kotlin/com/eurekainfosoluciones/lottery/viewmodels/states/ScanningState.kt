package com.eurekainfosoluciones.lottery.viewmodels.states

sealed class ScanningState
object Scanning : ScanningState()
object Validating : ScanningState()
object Supported : ScanningState()
object Unsupported : ScanningState()
package com.eurekainfosoluciones.lottery.android

interface RequestPermission {
    fun request()
    fun verifyResult(requestCode: Int, permissions: Array<out String>, results: IntArray): Boolean
}

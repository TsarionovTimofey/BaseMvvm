package com.mvvm

fun interface UiLabelsLogger<in UiLabel> {
    fun log(uiLabel: UiLabel)
}
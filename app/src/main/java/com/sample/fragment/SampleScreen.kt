package com.sample.fragment

interface SampleScreen {
    class Model(
        val isLoading: Boolean,
        val sampleText: String
    )
    sealed class Event {
        data object OnLabelClick : Event()
    }

    sealed class UiLabel {
        data class ShowMessage(val value: String) : UiLabel()
    }
}
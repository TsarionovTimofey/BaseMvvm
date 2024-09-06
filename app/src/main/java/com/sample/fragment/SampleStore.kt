package com.sample.fragment

interface SampleStore {
    data class State(
        val isLoading: Boolean = false,
        val sampleText: String = "Sample text"
    )

    sealed class Result {
        data class SampleTextUpdated(val value: String) : Result()
    }
}
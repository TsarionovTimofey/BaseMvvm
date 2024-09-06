package com.sample.fragment

import com.mvvm.Reducer
import com.sample.fragment.SampleStore.Result
import com.sample.fragment.SampleStore.State

class SampleReducer : Reducer<State, Result> {
    override fun State.reduce(result: Result): State {
        return when (result) {
            is Result.SampleTextUpdated -> copy(sampleText = result.value)
        }
    }
}
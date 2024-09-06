package com.sample.fragment

import com.mvvm.ScreenMapper
import com.sample.fragment.SampleScreen.Model
import com.sample.fragment.SampleStore.State

class SampleScreenMapper : ScreenMapper<State, Model> {
    override fun map(state: State): Model {
        return Model(
            isLoading = state.isLoading,
            sampleText = state.sampleText
        )
    }
}
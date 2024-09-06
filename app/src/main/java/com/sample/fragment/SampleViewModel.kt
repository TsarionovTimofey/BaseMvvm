package com.sample.fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.mvvm.BaseViewModel
import com.mvvm.Reducer
import com.sample.fragment.SampleScreen.Event
import com.sample.fragment.SampleScreen.Model
import com.sample.fragment.SampleScreen.UiLabel
import com.sample.fragment.SampleStore.Result
import com.sample.fragment.SampleStore.State
import com.mvvm.ScreenMapper
import kotlin.reflect.KClass

class SampleViewModel(
    override val uiMapper: ScreenMapper<State, Model>,
    override val reducer: Reducer<State, Result>
) : BaseViewModel<State, Result, Model, Event, UiLabel>() {
    override fun initialState() = State()

    override suspend fun handleEvent(event: Event) {
        when (event) {
            Event.OnLabelClick -> processOnLabelClick()
        }
    }

    private fun processOnLabelClick() {
        dispatch(Result.SampleTextUpdated("Updated Screen text"))
        publish(UiLabel.ShowMessage("Screen text updated"))
    }

    companion object {
        @Suppress("UNCHECKED_CAST")
        val factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: KClass<T>, extras: CreationExtras): T {
                return SampleViewModel(
                    uiMapper = SampleScreenMapper(),
                    reducer = SampleReducer()
                ) as T
            }
        }
    }
}
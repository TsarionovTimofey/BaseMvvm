package com.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.commonui.SingleLiveEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber

abstract class BaseViewModel<State, Result, Model, Event, UiLabel> : ViewModel() {
    abstract val uiMapper: ScreenMapper<State, Model>
    abstract val reducer: Reducer<State, Result>
    open val eventLogger: EventLogger<Event>? = EventLogger {
        Timber.tag("Event").d(it.toString())
    }
    open val uiLabelsLogger: UiLabelsLogger<UiLabel>? = UiLabelsLogger {
        Timber.tag("UiLabel").d(it.toString())
    }

    abstract fun initialState(): State
    abstract suspend fun handleEvent(event: Event)

    private val _state = MutableStateFlow(initialState())
    private val _uiLabels = SingleLiveEvent<UiLabel>()

    protected val state = _state.value
    val uiState get() = _state.map(uiMapper::map)
    val uiLabels: LiveData<UiLabel> = _uiLabels

    protected fun publish(uiLabel: UiLabel) {
        uiLabelsLogger?.log(uiLabel)
        _uiLabels.value = uiLabel
    }

    protected fun dispatch(result: Result) {
        reducer.run {
            _state.update {
                it.reduce(result)
            }
        }
    }

    fun onEvent(event: Event) {
        eventLogger?.log(event)
        viewModelScope.launch {
            handleEvent(event)
        }
    }
}
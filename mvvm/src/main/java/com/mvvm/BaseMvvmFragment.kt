package com.mvvm

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.commonui.launchAndRepeatWithViewLifecycle
import com.commonui.subscribe

abstract class BaseMvvmFragment<Model, Event, UiLabel>(
    @LayoutRes contentLayoutId: Int
) : Fragment(contentLayoutId) {
    abstract val viewModel: BaseViewModel<*, *, Model, Event, UiLabel>
    abstract fun render(model: Model)
    abstract fun handleUiLabel(uiLabel: UiLabel)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        launchAndRepeatWithViewLifecycle { viewModel.uiState.collect(::render) }
        subscribe(viewModel.uiLabels, ::handleUiLabel)
    }
}
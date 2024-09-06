package com.mvvm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.commonui.launchAndRepeatWithViewLifecycle
import com.commonui.subscribe
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

abstract class BaseMvvmActivity<Model, Event, UiLabel> : AppCompatActivity() {
    abstract val viewModel: BaseViewModel<*, *, Model, Event, UiLabel>
    abstract fun render(model: Model)
    abstract fun handleUiLabel(uiLabel: UiLabel)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        launchAndRepeatWithViewLifecycle { viewModel.uiState.collect(::render) }
        subscribe(viewModel.uiLabels, ::handleUiLabel)
    }
}
package com.sample.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.commonui.viewBinding
import com.mvvm.BaseMvvmFragment
import com.sample.R
import com.sample.fragment.SampleScreen.Event
import com.sample.fragment.SampleScreen.Model
import com.sample.fragment.SampleScreen.UiLabel
import com.sample.databinding.FragmentSampleBinding

class SampleFragment : BaseMvvmFragment<Model, Event, UiLabel>(R.layout.fragment_sample) {
    private val binding by viewBinding(FragmentSampleBinding::bind)

    override val viewModel: SampleViewModel by viewModels { SampleViewModel.factory }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvLabel.setOnClickListener {
            viewModel.onEvent(Event.OnLabelClick)
        }
    }

    override fun handleUiLabel(uiLabel: UiLabel) {
        when (uiLabel) {
            is UiLabel.ShowMessage -> showToast(uiLabel.value)
        }
    }

    override fun render(model: Model) {
        binding.tvLabel.text = model.sampleText
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}
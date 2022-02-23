package com.withpet.withpet_android.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.button.MaterialButtonToggleGroup
import com.withpet.withpet_android.R
import com.withpet.withpet_android.databinding.FragmentServiceRegisterBinding

class ServiceRegisterFragment : Fragment(R.layout.fragment_service_register) {

    private lateinit var binding: FragmentServiceRegisterBinding
    private lateinit var toggleList: List<MaterialButtonToggleGroup>
    private var checkedServiceId = R.id.petServiceWalkButton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_service_register,
            container,
            false
        )

        toggleList = listOf(
            binding.petServiceWalkToggleButton,
            binding.petServiceCareToggleButton,
            binding.petServiceExpToggleButton
        )

        binding.serviceRegisterStepView.goStep(1)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setButtonListener()
    }

    private fun setButtonListener() {
        binding.petServiceWalkToggleButton.addOnButtonCheckedListener { button, checkedId, isChecked ->
            toggleServiceButton(button, binding.serviceRegisterWalkTextView, checkedId, isChecked)
        }

        binding.petServiceCareToggleButton.addOnButtonCheckedListener { button, checkedId, isChecked ->
            toggleServiceButton(button, binding.serviceRegisterCareTextView, checkedId, isChecked)
        }

        binding.petServiceExpToggleButton.addOnButtonCheckedListener { button, checkedId, isChecked ->
            toggleServiceButton(button, binding.serviceRegisterExpTextView, checkedId, isChecked)
        }

        binding.serviceRegisterPrevButton.setOnClickListener {
            findNavController().navigate(R.id.action_serviceRegisterFragment_to_petRegisterFragment)
        }
        binding.serviceRegisterNextButton.setOnClickListener {
            findNavController().navigate(R.id.action_serviceRegisterFragment_to_dateRegisterFragment)
        }
    }

    private fun toggleServiceButton(
        button: MaterialButtonToggleGroup,
        textView: TextView,
        id: Int,
        isChecked: Boolean
    ) {
        if (id == button[0].id) {
            if (isChecked) {
                checkedServiceId = id

                toggleList.filter { entry -> entry != button }
                    .forEach { entry -> entry.clearChecked() }

                textView.setTextColor(
                    ContextCompat.getColor(
                        requireActivity(),
                        R.color.very_peri
                    )
                )
            } else {
                textView.setTextColor(
                    ContextCompat.getColor(
                        requireActivity(),
                        R.color.default_text_color
                    )
                )
            }
        }
    }
}
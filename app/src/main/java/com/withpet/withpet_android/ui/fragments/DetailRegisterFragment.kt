package com.withpet.withpet_android.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.withpet.withpet_android.R
import com.withpet.withpet_android.databinding.FragmentDateRegisterBinding
import com.withpet.withpet_android.databinding.FragmentDetailRegisterBinding

class DetailRegisterFragment : Fragment(R.layout.fragment_detail_register) {

    private lateinit var binding: FragmentDetailRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_detail_register,
            container,
            false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setButtonListener()

        binding.detailRegisterStepView.goStep(4)
    }

    private fun setButtonListener() {
        binding.detailRegisterPrevButton.setOnClickListener {
            findNavController().navigate(R.id.action_detailRegisterFragment_to_photoRegisterFragment)
        }
        binding.detailRegisterNextButton.setOnClickListener {
            findNavController().navigate(R.id.action_detailRegisterFragment_to_confirmFragment)
        }
    }
}
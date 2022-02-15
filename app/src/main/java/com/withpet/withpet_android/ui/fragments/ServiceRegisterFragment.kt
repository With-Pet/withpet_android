package com.withpet.withpet_android.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.withpet.withpet_android.R
import com.withpet.withpet_android.databinding.FragmentServiceRegisterBinding

class ServiceRegisterFragment : Fragment(R.layout.fragment_service_register) {

    private lateinit var binding: FragmentServiceRegisterBinding

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

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setButtonListener()
    }

    private fun setButtonListener() {
        binding.serviceRegisterPrevButton.setOnClickListener {
            findNavController().navigate(R.id.action_serviceRegisterFragment_to_petRegisterFragment)
        }
        binding.serviceRegisterNextButton.setOnClickListener {
            findNavController().navigate(R.id.action_serviceRegisterFragment_to_dateRegisterFragment)
        }
    }
}
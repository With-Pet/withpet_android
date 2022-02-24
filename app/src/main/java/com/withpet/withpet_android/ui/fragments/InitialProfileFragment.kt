package com.withpet.withpet_android.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.withpet.withpet_android.R
import com.withpet.withpet_android.databinding.FragmentInitialProfileBinding
import com.withpet.withpet_android.ui.activities.MainActivity

class InitialProfileFragment : Fragment(R.layout.fragment_initial_profile) {

    private lateinit var binding: FragmentInitialProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_initial_profile,
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
        binding.initialProfilePrevButton.setOnClickListener {
            findNavController().navigate(
                R.id.action_initialProfileFragment_to_emailFragment,
                Bundle().apply { putString("email", arguments?.getString("email")) })
        }
        binding.initialProfileStartButton.setOnClickListener {
            startActivity(Intent(activity, MainActivity::class.java))
            activity?.finish()
        }
    }
}
package com.withpet.withpet_android.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.withpet.withpet_android.R
import com.withpet.withpet_android.databinding.FragmentEmailBinding

class EmailFragment : Fragment(R.layout.fragment_email) {

    companion object {
        const val TAG = "EMAIL_FRAGMENT"
    }

    private lateinit var binding: FragmentEmailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_email,
            container,
            false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.emailEdittext.setText(arguments?.getString("email"))
        setButtonListener()
    }

    private fun setButtonListener() {
        binding.emailNextButton.setOnClickListener {
            findNavController().navigate(
                R.id.action_emailFragment_to_initialProfileFragment,
                Bundle().apply { putString("email", arguments?.getString("email")) })
        }
    }
}
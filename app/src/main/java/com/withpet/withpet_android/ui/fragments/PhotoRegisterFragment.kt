package com.withpet.withpet_android.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.withpet.withpet_android.R
import com.withpet.withpet_android.adapter.RegisterImageListAdapter
import com.withpet.withpet_android.databinding.FragmentPhotoRegisterBinding

class PhotoRegisterFragment : Fragment(R.layout.fragment_photo_register) {

    private lateinit var binding: FragmentPhotoRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_photo_register,
            container,
            false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setButtonListener()
        setRecyclerView()

        binding.photoRegisterStepView.goStep(3)
    }

    private fun setButtonListener() {
        binding.photoRegisterPrevButton.setOnClickListener {
            findNavController().navigate(R.id.action_photoRegisterFragment_to_dateRegisterFragment)
        }
        binding.photoRegisterNextButton.setOnClickListener {
            findNavController().navigate(R.id.action_photoRegisterFragment_to_detailRegisterFragment)
        }
    }

    private fun setRecyclerView() {
        binding.photoRegisterRecyclerView.adapter = RegisterImageListAdapter()
        binding.photoRegisterRecyclerView.layoutManager = GridLayoutManager(activity, 3)
    }
}
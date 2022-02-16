package com.withpet.withpet_android.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.withpet.withpet_android.R
import com.withpet.withpet_android.adapter.PetListAdapter
import com.withpet.withpet_android.databinding.FragmentPetRegisterBinding
import com.withpet.withpet_android.ui.activities.MyPetActivity

class PetRegisterFragment : Fragment(R.layout.fragment_pet_register) {

    private lateinit var binding: FragmentPetRegisterBinding
    private var isExistPet = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_pet_register,
            container,
            false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (isExistPet) {
            binding.petRegisterNoPet.visibility = View.GONE
            setRecyclerView()
        } else {
            binding.petRegisterRecyclerView.visibility = View.GONE
            setButtonListener()
        }
    }

    private fun setButtonListener() {
        binding.petRegisterNewPetButton.setOnClickListener {
            startActivity(Intent(activity, MyPetActivity::class.java))
            activity?.finish()
        }
    }

    private fun setRecyclerView() {
        binding.petRegisterRecyclerView.adapter = PetListAdapter {
            findNavController().navigate(R.id.action_petRegisterFragment_to_serviceRegisterFragment)
        }
        binding.petRegisterRecyclerView.layoutManager = GridLayoutManager(activity, 2)
    }
}
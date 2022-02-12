package com.withpet.withpet_android.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.withpet.withpet_android.R
import com.withpet.withpet_android.databinding.AdapterViewPetListBinding

class PetListAdapter(
    private val itemClickListener: () -> Unit
) : RecyclerView.Adapter<PetListAdapter.PetListViewHolder>() {

    inner class PetListViewHolder(
        binding: AdapterViewPetListBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetListViewHolder {
        val binding = DataBindingUtil.inflate<AdapterViewPetListBinding>(
            LayoutInflater.from(parent.context),
            R.layout.adapter_view_pet_list,
            parent,
            false
        )

        binding.root.setOnClickListener { itemClickListener() }

        return PetListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PetListViewHolder, position: Int) {
        // TODO("Not yet implemented")
    }

    override fun getItemCount() = 7
}
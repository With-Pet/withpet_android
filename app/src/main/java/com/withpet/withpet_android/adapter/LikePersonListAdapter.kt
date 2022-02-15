package com.withpet.withpet_android.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.withpet.withpet_android.R
import com.withpet.withpet_android.databinding.AdapterViewLikePersonListBinding

class LikePersonListAdapter(
    private val itemClickListener: () -> Unit
) : RecyclerView.Adapter<LikePersonListAdapter.LikePersonViewHolder>() {

    inner class LikePersonViewHolder(
        binding: AdapterViewLikePersonListBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LikePersonViewHolder {
        val binding = DataBindingUtil.inflate<AdapterViewLikePersonListBinding>(
            LayoutInflater.from(parent.context),
            R.layout.adapter_view_like_person_list,
            parent,
            false
        )

        binding.root.setOnClickListener { itemClickListener() }

        return LikePersonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LikePersonViewHolder, position: Int) {
        // TODO("Not yet implemented")
    }

    override fun getItemCount() = 7
}
package com.withpet.withpet_android.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.withpet.withpet_android.R
import com.withpet.withpet_android.databinding.AdapterViewConfirmImageListBinding

class ConfirmImageListAdapter(
) : RecyclerView.Adapter<ConfirmImageListAdapter.ConfirmImageViewHolder>() {

    inner class ConfirmImageViewHolder(
        binding: AdapterViewConfirmImageListBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConfirmImageViewHolder {
        val binding = DataBindingUtil.inflate<AdapterViewConfirmImageListBinding>(
            LayoutInflater.from(parent.context),
            R.layout.adapter_view_confirm_image_list,
            parent,
            false
        )

        return ConfirmImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ConfirmImageViewHolder, position: Int) {
        // TODO("Not yet implemented")
    }

    override fun getItemCount() = 8
}
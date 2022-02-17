package com.withpet.withpet_android.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.withpet.withpet_android.R
import com.withpet.withpet_android.databinding.AdapterViewRegisterImageListBinding

class RegisterImageListAdapter(
) : RecyclerView.Adapter<RegisterImageListAdapter.RegisterImageViewHolder>() {

    inner class RegisterImageViewHolder(
        binding: AdapterViewRegisterImageListBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RegisterImageViewHolder {
        val binding = DataBindingUtil.inflate<AdapterViewRegisterImageListBinding>(
            LayoutInflater.from(parent.context),
            R.layout.adapter_view_register_image_list,
            parent,
            false
        )

        return RegisterImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RegisterImageViewHolder, position: Int) {
        // TODO("Not yet implemented")
    }

    override fun getItemCount() = 8
}
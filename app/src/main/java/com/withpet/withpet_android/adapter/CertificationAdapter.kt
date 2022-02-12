package com.withpet.withpet_android.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.withpet.withpet_android.R
import com.withpet.withpet_android.databinding.AdapterViewCertificationBinding

class CertificationAdapter : RecyclerView.Adapter<CertificationAdapter.CertificationViewHolder>() {

    inner class CertificationViewHolder(
        binding: AdapterViewCertificationBinding
    ) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CertificationViewHolder {
        val binding = DataBindingUtil.inflate<AdapterViewCertificationBinding>(
            LayoutInflater.from(parent.context),
            R.layout.adapter_view_certification,
            parent,
            false
        )
        return CertificationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CertificationViewHolder, position: Int) {
        // TODO("Not yet implemented")
    }

    override fun getItemCount(): Int = 3


}
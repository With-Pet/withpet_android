package com.withpet.withpet_android.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.withpet.withpet_android.R
import com.withpet.withpet_android.databinding.AdapterReservationListBinding

class ReservationListAdapter(
    private val profileButtonClickListener: () -> Unit
) : RecyclerView.Adapter<ReservationListAdapter.ReservationListViewHolder>() {

    inner class ReservationListViewHolder(
        binding: AdapterReservationListBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReservationListViewHolder {
        val binding = DataBindingUtil.inflate<AdapterReservationListBinding>(
            LayoutInflater.from(parent.context),
            R.layout.adapter_reservation_list,
            parent,
            false
        )

        binding.reservationProfileButton.setOnClickListener {
            profileButtonClickListener()
        }
        return ReservationListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReservationListViewHolder, position: Int) {
        // TODO("Not yet implemented")
    }

    override fun getItemCount() = 8
}
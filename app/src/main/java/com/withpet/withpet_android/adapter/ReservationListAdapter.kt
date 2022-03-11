package com.withpet.withpet_android.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.withpet.withpet_android.R
import com.withpet.withpet_android.databinding.AdapterReservationListBinding

class ReservationListAdapter :
    RecyclerView.Adapter<ReservationListAdapter.ReservationListViewHolder>() {

    interface ReservationClickListener {
        fun itemClickListener()
        fun profileButtonClickListener()
        fun chatButtonClickListener()
    }

    inner class ReservationListViewHolder(
        private val binding: AdapterReservationListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun btnClickListener(listener: ReservationClickListener?) {
            binding.reservationProfileButton.setOnClickListener { listener?.profileButtonClickListener() }
            binding.reservationChatButton.setOnClickListener { listener?.chatButtonClickListener() }
        }
    }

    private var listener: ReservationClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReservationListViewHolder {
        val binding = DataBindingUtil.inflate<AdapterReservationListBinding>(
            LayoutInflater.from(parent.context),
            R.layout.adapter_reservation_list,
            parent,
            false
        )

        return ReservationListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReservationListViewHolder, position: Int) {
        holder.itemView.setOnClickListener { listener?.itemClickListener() }
        holder.btnClickListener(listener)
    }

    override fun getItemCount() = 8

    fun setClickListener(listener: ReservationClickListener) {
        this.listener = listener
    }
}
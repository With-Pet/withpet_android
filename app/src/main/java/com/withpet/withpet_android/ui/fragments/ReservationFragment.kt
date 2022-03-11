package com.withpet.withpet_android.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.withpet.withpet_android.R
import com.withpet.withpet_android.adapter.ReservationAdapter
import com.withpet.withpet_android.databinding.FragmentReservationBinding

class ReservationFragment : Fragment(R.layout.fragment_reservation) {

    private lateinit var binding: FragmentReservationBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_reservation,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setTab()
    }

    private fun setTab() {
        binding.reservationViewPager.adapter = ReservationAdapter(requireActivity())

        TabLayoutMediator(
            binding.reservationTabLayout,
            binding.reservationViewPager
        ) { tab, position ->
            when (position) {
                0 -> tab.text = getString(R.string.give_service)
                1 -> tab.text = getString(R.string.take_service)
            }
        }.attach()
    }
}
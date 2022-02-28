package com.withpet.withpet_android.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.withpet.withpet_android.R
import com.withpet.withpet_android.adapter.PostListAdapter
import com.withpet.withpet_android.databinding.FragmentLeaveListBinding

class LeaveListFragment : Fragment(R.layout.fragment_leave_list) {

    private lateinit var binding: FragmentLeaveListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate<FragmentLeaveListBinding>(
            inflater,
            R.layout.fragment_leave_list,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setRecyclerView()
    }

    private fun setRecyclerView() {
        binding.leaveListRecyclerView.adapter = PostListAdapter {

        }
        binding.leaveListRecyclerView.layoutManager = LinearLayoutManager(activity)
    }
}
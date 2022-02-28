package com.withpet.withpet_android.ui.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.badge.BadgeUtils
import com.withpet.withpet_android.R
import com.withpet.withpet_android.adapter.PostListAdapter
import com.withpet.withpet_android.databinding.FragmentLeaveListBinding
import com.withpet.withpet_android.ui.activities.LocationSearchActivity
import com.withpet.withpet_android.ui.activities.PostSearchActivity

class LeaveListFragment : Fragment(R.layout.fragment_leave_list) {

    private lateinit var binding: FragmentLeaveListBinding
    private lateinit var notificationBadge: BadgeDrawable
    private var num = 1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_leave_list,
            container,
            false
        )
        notificationBadge = BadgeDrawable.create(requireContext()).also { badge ->
            badge.backgroundColor = ContextCompat.getColor(requireContext(), R.color.paradise_pink)
            badge.horizontalOffset = resources.getDimensionPixelOffset(R.dimen.badge_offset)
            badge.verticalOffset = resources.getDimensionPixelOffset(R.dimen.badge_offset)
            badge.maxCharacterCount = 3
            badge.number = num++
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setBadge()
        setToolbar()
        setRecyclerView()
    }

    @SuppressLint("UnsafeOptInUsageError")
    private fun setBadge() {
        BadgeUtils.attachBadgeDrawable(
            notificationBadge,
            binding.leaveListToolbar as Toolbar,
            R.id.leaveListNotificationMenu
        )
    }

    private fun setToolbar() {
        binding.leaveListTownTitle.setOnClickListener {
            startActivity(Intent(activity, LocationSearchActivity::class.java))
        }
        binding.leaveListToolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.leaveListNotificationMenu -> {
                    // TODO: Add an activity for the notification list
                    notificationBadge.number = num++
                    true
                }
                R.id.leaveListFilterMenu -> {
                    true
                }
                R.id.leaveListSearchMenu -> {
                    startActivity(Intent(activity, PostSearchActivity::class.java))
                    true
                }
                else -> false
            }
        }
    }

    private fun setRecyclerView() {
        binding.leaveListRecyclerView.adapter = PostListAdapter {

        }
        binding.leaveListRecyclerView.layoutManager = LinearLayoutManager(activity)
    }
}
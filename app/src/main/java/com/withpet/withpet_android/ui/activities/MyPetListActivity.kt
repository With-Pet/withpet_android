package com.withpet.withpet_android.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.withpet.withpet_android.R
import com.withpet.withpet_android.adapter.PetListAdapter
import com.withpet.withpet_android.databinding.ActivityMyPetListBinding

class MyPetListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMyPetListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_my_pet_list)

        setToolbar()
        setRecyclerView()
    }

    private fun setToolbar() {
        binding.myPetListToolbar.setNavigationOnClickListener { finish() }
        binding.myPetListToolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.profileAddMenu -> {
                    startActivity(Intent(this, MyPetActivity::class.java))
                    true
                }
                else -> false
            }
        }
    }

    private fun setRecyclerView() {
        binding.myPetListRecyclerView.adapter = PetListAdapter {
            startActivity(Intent(this, MyPetActivity::class.java))
        }
        binding.myPetListRecyclerView.layoutManager = GridLayoutManager(this, 2)
    }
}
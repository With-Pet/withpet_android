package com.withpet.withpet_android.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import com.withpet.withpet_android.R
import com.withpet.withpet_android.databinding.ActivityMyPetBinding

class MyPetActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMyPetBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_my_pet)

        setToolbar()
        setDropdownMenu()
        setBtnClickListener()
    }

    private fun setToolbar() {
        binding.myPetToolbar.setNavigationOnClickListener { clickHomeMenu() }
        binding.myPetToolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.profileSaveMenu -> {
                    clickSaveMenu()
                    true
                }
                else -> false
            }
        }
    }

    private fun clickHomeMenu() {
        // TODO: check changes and then confirm whether changes save
        finish()
    }

    private fun clickSaveMenu() {
        // TODO: check changes and then save changes
        finish()
    }

    private fun setDropdownMenu() {
        val petCategoryArr = resources.getStringArray(R.array.pet_category_arr)
        val petGenderArr = resources.getStringArray(R.array.pet_gender_arr)
        val petSurgeryArr = resources.getStringArray(R.array.pet_surgery_arr)

        binding.myPetCategoryTextView.apply {
            setOnFocusChangeListener { v, hasFocus -> hideKeyboard(v, hasFocus) }
            setAdapter(
                ArrayAdapter(
                    this@MyPetActivity,
                    R.layout.adapter_view_dropdown,
                    petCategoryArr
                )
            )
            setText(petCategoryArr[0], false)
        }

        binding.myPetGenderTextView.apply {
            setOnFocusChangeListener { v, hasFocus -> hideKeyboard(v, hasFocus) }
            setAdapter(
                ArrayAdapter(
                    this@MyPetActivity,
                    R.layout.adapter_view_dropdown,
                    petGenderArr
                )
            )
            setText(petGenderArr[0], false)
        }

        binding.myPetSurgeryTextView.apply {
            setOnFocusChangeListener { v, hasFocus -> hideKeyboard(v, hasFocus) }
            setAdapter(
                ArrayAdapter(
                    this@MyPetActivity,
                    R.layout.adapter_view_dropdown,
                    petSurgeryArr
                )
            )
            setText(petSurgeryArr[0], false)
        }
    }

    private fun hideKeyboard(v: View, hasFocus: Boolean) {
        if (hasFocus) {
            (getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager)
                .hideSoftInputFromWindow(
                    v.windowToken,
                    InputMethodManager.HIDE_NOT_ALWAYS
                )
        }
    }

    private fun setBtnClickListener() {
        binding.myPetImageEditButton.setOnClickListener {

        }
    }
}
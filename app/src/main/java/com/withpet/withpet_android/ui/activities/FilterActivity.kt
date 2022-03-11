package com.withpet.withpet_android.ui.activities

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.withpet.withpet_android.R
import com.withpet.withpet_android.databinding.ActivityFilterBinding
import com.withpet.withpet_android.databinding.DialogDateTimePickerBinding
import com.withpet.withpet_android.enums.PetCategoryEnum
import com.withpet.withpet_android.enums.ServiceEnum
import com.withpet.withpet_android.others.Utils
import java.text.NumberFormat
import java.util.*

class FilterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFilterBinding
    private val format = NumberFormat.getCurrencyInstance().apply {
        maximumFractionDigits = 0
        currency = Currency.getInstance("KRW")
    }
    private var serviceFilter = ServiceEnum.ENTIRE
    private var petCategoryFilter = PetCategoryEnum.ENTIRE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_filter)

        setToolbar()
        setToggleListener()
        setSlider()
        setBtnClickListener()
        initializeUI()
    }

    private fun setDefaultUI() {
        binding.filterServiceGroup.check(R.id.filterServiceEntireButton)
        binding.filterPetGroup.check(R.id.filterPetEntireButton)
        binding.filterStartDateButton.text =
            Utils.getDateTimeString(Calendar.getInstance().time)
        binding.filterEndDateButton.text = Utils.getDateTimeString(
            Date(Calendar.getInstance().time.time + (1000 * 60 * 60 * 24))
        )
        binding.filterSlider.values = listOf(0f, 1000000f)
        setFeeRangeText(binding.filterSlider.values)
    }

    private fun setToolbar() {
        binding.filterToolbar.setNavigationOnClickListener { finish() }
    }

    private fun setToggleListener() {
        binding.filterServiceGroup.addOnButtonCheckedListener { _, checkedId, isChecked ->
            if (isChecked) {
                serviceFilter = when (checkedId) {
                    R.id.filterServiceWalkButton -> ServiceEnum.WALK
                    R.id.filterServiceCareButton -> ServiceEnum.CARE
                    R.id.filterServiceExpButton -> ServiceEnum.EXP
                    else -> ServiceEnum.ENTIRE
                }
            }
        }
        binding.filterPetGroup.addOnButtonCheckedListener { _, checkedId, isChecked ->
            if (isChecked) {
                petCategoryFilter = when (checkedId) {
                    R.id.filterPetDogButton -> PetCategoryEnum.DOG
                    R.id.filterPetCatButton -> PetCategoryEnum.CAT
                    R.id.filterPetEtcButton -> PetCategoryEnum.ETC
                    else -> PetCategoryEnum.ENTIRE
                }
            }
        }
    }

    private fun setSlider() {
        binding.filterSlider.setLabelFormatter { value -> format.format(value.toDouble()) }
        binding.filterSlider.valueFrom = 0f
        binding.filterSlider.valueTo = 1000000f
        binding.filterSlider.stepSize = 5000f
        binding.filterSlider.addOnChangeListener { slider, _, _ ->
            setFeeRangeText(slider.values)
        }
    }

    private fun setFeeRangeText(values: List<Float>) {
        val fromFee = if (values[0] == 0f) {
            getString(R.string.free)
        } else {
            format.format(values[0].toDouble())
        }
        val toFee = format.format(values[1].toDouble())
        binding.filterFeeTextView.text = getString(R.string.fee_range, fromFee, toFee)
    }

    private fun setBtnClickListener() {
        binding.filterStartDateButton.setOnClickListener { showDateTimePickerDialog(true) }
        binding.filterEndDateButton.setOnClickListener { showDateTimePickerDialog(false) }
        binding.filterResetButton.setOnClickListener { setDefaultUI() }
        binding.filterApplyButton.setOnClickListener { finish() }
    }

    private fun showDateTimePickerDialog(isStartButton: Boolean) {
        val dateTimePickerBinding = DataBindingUtil.inflate<DialogDateTimePickerBinding>(
            LayoutInflater.from(this),
            R.layout.dialog_date_time_picker,
            null,
            false
        )
        MaterialAlertDialogBuilder(this)
            .setView(dateTimePickerBinding.root)
            .setNegativeButton(R.string.cancel, null)
            .setPositiveButton(R.string.decide) { _, _ ->
                if (isStartButton) {
                    binding.filterStartDateButton.text = getDateAndTime(dateTimePickerBinding)
                } else {
                    binding.filterEndDateButton.text = getDateAndTime(dateTimePickerBinding)
                }
            }.show()
    }

    private fun getDateAndTime(pickerBinding: DialogDateTimePickerBinding): String {
        val year = pickerBinding.datePicker.year
        val month = pickerBinding.datePicker.month
        val day = pickerBinding.datePicker.dayOfMonth
        var hour = 0
        var minute = 0
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            hour = pickerBinding.timePicker.hour
            minute = pickerBinding.timePicker.minute
        } else {
            hour = pickerBinding.timePicker.currentHour
            minute = pickerBinding.timePicker.currentMinute
        }
        return Utils.timeToString(year, month, day, hour, minute)
    }


    private fun initializeUI() {
        binding.filterServiceGroup.check(R.id.filterServiceEntireButton)
        binding.filterPetGroup.check(R.id.filterPetEntireButton)
        binding.filterStartDateButton.text =
            Utils.getDateTimeString(Calendar.getInstance().time)
        binding.filterEndDateButton.text = Utils.getDateTimeString(
            Date(Calendar.getInstance().time.time + (1000 * 60 * 60 * 24))
        )
        binding.filterSlider.values = listOf(0f, 1000000f)
        setFeeRangeText(binding.filterSlider.values)
    }
}